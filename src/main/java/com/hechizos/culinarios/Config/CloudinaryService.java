package com.hechizos.culinarios.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

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

    private Map<String, String> valuesMap = new HashMap<>();

    public CloudinaryService() {
        valuesMap.put("cloud_name", "djprqhosn");
        valuesMap.put("api_key", "969389158697739");
        valuesMap.put("api_secret", "z_V9aTNxeBQpNg5otjC9OIRUiak");
        cloudinary = new Cloudinary(valuesMap);
    }

    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }

    public List<Map> uploadMultiple(List<MultipartFile> multipartFiles) throws IOException {
        List<Map> uploadResults = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            Map result = upload(file);
            uploadResults.add(result);
        }
        return uploadResults;
    }

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
