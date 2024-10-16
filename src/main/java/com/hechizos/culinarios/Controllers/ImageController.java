package com.hechizos.culinarios.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import com.hechizos.culinarios.Config.CloudinaryService;
import com.hechizos.culinarios.Dto.ImageDto;
import com.hechizos.culinarios.Exception.GenericResponseRecord;
import com.hechizos.culinarios.Models.Images;
import com.hechizos.culinarios.Models.Recipe;
import com.hechizos.culinarios.Services.ImagesService;
import com.hechizos.culinarios.Services.RecipeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImagesService imagesService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    private final RecipeService recipeService;
    private final CloudinaryService cloudinaryService;

    @PostMapping(value = "/{idRecipe}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericResponseRecord<ImageDto>> createImage(@PathVariable Long idRecipe,
            @RequestPart("image") MultipartFile images) throws Exception {
        @SuppressWarnings("rawtypes")
        Map uploadImage = cloudinaryService.upload(images);
        Images img = new Images();
        img.setIdCloudinary(uploadImage.get("public_id").toString());
        img.setLink(uploadImage.get("url").toString());
        Recipe recipe = recipeService.readById(idRecipe);
        img.setRecipe(recipe);
        imagesService.save(img);
        ImageDto imageDto = convertToDto(img);
        List<ImageDto> imageDtos = new ArrayList<>();
        imageDtos.add(imageDto);
        return ResponseEntity.ok(new GenericResponseRecord<>(HttpStatus.CREATED.value(), "success", imageDtos));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericResponseRecord<ImageDto>> updateImage(@RequestPart("image") MultipartFile images,
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) throws Exception {
        Images img = imagesService.readById(id);
        @SuppressWarnings("rawtypes")
        Map uploadImage = cloudinaryService.upload(images);
        cloudinaryService.delete(img.getIdCloudinary());
        img.setIdCloudinary(uploadImage.get("public_id").toString());
        img.setLink(uploadImage.get("url").toString());
        imagesService.save(img);
        ImageDto imageDto = convertToDto(img);
        List<ImageDto> imageDtos = new ArrayList<>();
        imageDtos.add(imageDto);
        return ResponseEntity.ok(new GenericResponseRecord<>(HttpStatus.CREATED.value(), "success", imageDtos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseRecord<ImageDto>> deleteImage(@PathVariable Long id) throws Exception {
        Images img = imagesService.readById(id);
        cloudinaryService.delete(img.getIdCloudinary());
        imagesService.delete(id);
        ImageDto imageDto = convertToDto(img);
        List<ImageDto> imageDtos = new ArrayList<>();
        imageDtos.add(imageDto);
        return ResponseEntity.ok(new GenericResponseRecord<>(HttpStatus.OK.value(), "success", imageDtos));
    }

    private ImageDto convertToDto(Images images) {
        return modelMapper.map(images, ImageDto.class);
    }

}
