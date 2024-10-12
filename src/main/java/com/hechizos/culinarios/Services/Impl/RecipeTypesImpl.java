package com.hechizos.culinarios.Services.Impl;

import org.springframework.stereotype.Service;
import com.hechizos.culinarios.Models.RecipeTypes;
import com.hechizos.culinarios.Repositories.GenericRepository;
import com.hechizos.culinarios.Repositories.RecipeTypesRepository;
import com.hechizos.culinarios.Services.RecipeTypesService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeTypesImpl extends CrudImpl<RecipeTypes, Long> implements RecipeTypesService {

    private final RecipeTypesRepository recipeTypesRepository;

    @Override
    protected GenericRepository<RecipeTypes, Long> getRepository() {
        return recipeTypesRepository;
    }

}
