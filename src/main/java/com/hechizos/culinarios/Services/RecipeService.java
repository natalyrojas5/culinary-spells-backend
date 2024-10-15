package com.hechizos.culinarios.Services;

import java.util.List;

import com.hechizos.culinarios.Models.Recipe;
import com.hechizos.culinarios.Models.User;

public interface RecipeService extends ICrud<Recipe, Long> {

    List<Recipe> findByUser(User user);
}
