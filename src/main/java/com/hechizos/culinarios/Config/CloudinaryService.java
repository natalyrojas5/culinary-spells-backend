package com.hechizos.culinarios.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@Service
public class CloudinaryService {

    Cloudinary cloudinary;

    @Value("${cloudinary.cloudName}")
    private String cloudName;
    @Value("${cloudinary.apiKey}")
    private String apiKey;

    @Value("${cloudinary.apiSecret}")
    private String apiSecret;

    @PostConstruct
    public void init() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    @SuppressWarnings("rawtypes")
    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map<String, Object> options = ObjectUtils.asMap(
                "transformation", new Transformation().named("assetlogos"));
        Map result = cloudinary.uploader().upload(file, options);
        file.delete();
        return result;
    }

    @SuppressWarnings("rawtypes")
    public List<Map> uploadMultiple(List<MultipartFile> multipartFiles) throws IOException {
        List<Map> uploadResults = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            Map result = upload(file);
            uploadResults.add(result);
        }
        return uploadResults;
    }

    @SuppressWarnings("rawtypes")
    public Map delete(String id) throws IOException {
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }
}
