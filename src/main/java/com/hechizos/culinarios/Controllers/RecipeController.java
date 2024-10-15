package com.hechizos.culinarios.Controllers;

import java.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.hechizos.culinarios.Auth.JwtTokenDecoder;
import com.hechizos.culinarios.Config.CloudinaryService;
import com.hechizos.culinarios.Dto.ImageDto;
import com.hechizos.culinarios.Dto.RecipeDto;
import com.hechizos.culinarios.Dto.RecipeSimpleDto;
import com.hechizos.culinarios.Dto.StepsDto;
import com.hechizos.culinarios.Exception.GenericResponseRecord;
import com.hechizos.culinarios.Models.Images;
import com.hechizos.culinarios.Models.Recipe;
import com.hechizos.culinarios.Models.Steps;
import com.hechizos.culinarios.Models.User;
import com.hechizos.culinarios.Services.ImagesService;
import com.hechizos.culinarios.Services.RecipeService;
import com.hechizos.culinarios.Services.StepsService;
import com.hechizos.culinarios.Services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final ImagesService imagesService;
    private final StepsService stepsService;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity<GenericResponseRecord<RecipeSimpleDto>> readAll() throws Exception {
        List<RecipeSimpleDto> list = recipeService.readAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(list)));
    }

    @GetMapping("/all/own")
    public ResponseEntity<GenericResponseRecord<RecipeSimpleDto>> readAllOwn(
            @RequestHeader("Authorization") String token)
            throws Exception {
        Long userId = JwtTokenDecoder.getUserId(token);
        User user = userService.findByIdUser(userId);
        List<RecipeSimpleDto> list = recipeService.findByUser(user).stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(list)));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponseRecord<RecipeDto>> createRecipe(
            @Valid @RequestPart("recipe") RecipeDto recipeDto,
            @RequestPart("images") List<MultipartFile> images,
            @RequestPart("majorImageIndex") String majorImageIndexStr,
            @RequestHeader("Authorization") String token) throws Exception {

        int majorImageIndex = Integer.parseInt(majorImageIndexStr);
        Long userId = JwtTokenDecoder.getUserId(token);
        User user = userService.findByIdUser(userId);
        Recipe recipe = convertToEntity(recipeDto);
        recipe.setUser(user);
        Recipe savedRecipe = recipeService.save(recipe);

        @SuppressWarnings("rawtypes")
        List<Map> uploadResults = cloudinaryService.uploadMultiple(images);

        for (int i = 0; i < uploadResults.size(); i++) {
            @SuppressWarnings("rawtypes")
            Map result = uploadResults.get(i);
            String cloudinaryId = (String) result.get("public_id");
            String imageUrl = (String) result.get("url");

            Images image = new Images();
            image.setRecipe(savedRecipe);
            image.setIdCloudinary(cloudinaryId);
            image.setLink(imageUrl);

            image.setMajor(i == majorImageIndex ? 1 : 0);

            imagesService.save(image);
        }

        RecipeDto savedRecipeDto = convertToDtoR(savedRecipe);
        List<RecipeDto> savedRecipeDtoList = new ArrayList<>();
        savedRecipeDtoList.add(savedRecipeDto);
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(savedRecipeDtoList)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponseRecord<RecipeDto>> readById(@PathVariable("id") Long id) throws Exception {
        Recipe recipe = recipeService.readById(id);
        RecipeDto recipeDto = convertToDtoR(recipe);
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        recipeDtoList.add(recipeDto);
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(recipeDtoList)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GenericResponseRecord<RecipeDto>> update(
            @Valid @PathVariable("id") Long id,
            @RequestPart("recipe") RecipeDto recipeDto,
            @RequestPart(value = "steps", required = false) List<StepsDto> stepsDto) throws Exception {
        Recipe recipe = convertToEntity(recipeDto);
        Recipe updatedRecipe = recipeService.update(recipe, id);
        if (stepsDto != null) {
            for (StepsDto stepDto : stepsDto) {
                Steps steps = convertToStepsEntity(stepDto);
                if (steps.getIdStep() != null) {
                    stepsService.update(steps, steps.getIdStep());
                } else {
                    steps.setRecipe(updatedRecipe);
                    stepsService.save(steps);
                }
            }
        }
        RecipeDto updatedRecipeDto = convertToDtoR(updatedRecipe);
        List<RecipeDto> updatedRecipeDtoList = new ArrayList<>();
        updatedRecipeDtoList.add(updatedRecipeDto);
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(updatedRecipeDtoList)));
    }

    private RecipeSimpleDto convertToDto(Recipe obj) {
        Optional<Images> majorImage = imagesService.findMajorImageByRecipe(obj);
        RecipeSimpleDto dto = modelMapper.map(obj, RecipeSimpleDto.class);
        majorImage.ifPresent(image -> {
            dto.setImages(List.of(modelMapper.map(image, ImageDto.class)));
        });
        return dto;
    }

    private RecipeDto convertToDtoR(Recipe obj) {
        return modelMapper.map(obj, RecipeDto.class);
    }

    private Recipe convertToEntity(RecipeDto obj) {
        return modelMapper.map(obj, Recipe.class);
    }

    private Steps convertToStepsEntity(StepsDto obj) {
        return modelMapper.map(obj, Steps.class);
    }

}