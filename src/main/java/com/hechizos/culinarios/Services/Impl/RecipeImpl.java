package com.hechizos.culinarios.Services.Impl;

import org.springframework.stereotype.Service;
import com.hechizos.culinarios.Models.Recipe;
import com.hechizos.culinarios.Models.User;
import com.hechizos.culinarios.Repositories.GenericRepository;
import com.hechizos.culinarios.Repositories.RecipeRepository;
import com.hechizos.culinarios.Services.RecipeService;
import java.util.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeImpl extends CrudImpl<Recipe, Long> implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    protected GenericRepository<Recipe, Long> getRepository() {
        return recipeRepository;
    }

    public List<Recipe> findByUser(User user) {
        return recipeRepository.findByUser(user);
    }
}
