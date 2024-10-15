package com.hechizos.culinarios.Services.Impl;

import java.util.*;
import org.springframework.stereotype.Service;

import com.hechizos.culinarios.Models.Images;
import com.hechizos.culinarios.Models.Recipe;
import com.hechizos.culinarios.Repositories.GenericRepository;
import com.hechizos.culinarios.Repositories.ImagesRepository;
import com.hechizos.culinarios.Services.ImagesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImagesImpl extends CrudImpl<Images, Long> implements ImagesService {

    private final ImagesRepository imagesRepository;

    @Override
    protected GenericRepository<Images, Long> getRepository() {
        return imagesRepository;
    }

    public Optional<Images> findMajorImageByRecipe(Recipe recipe) {
        return imagesRepository.findByRecipeAndMajor(recipe, 1);
    }

}
