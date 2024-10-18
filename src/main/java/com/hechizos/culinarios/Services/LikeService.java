package com.hechizos.culinarios.Services;

import com.hechizos.culinarios.Models.Like;
import com.hechizos.culinarios.Models.Recipe;
import com.hechizos.culinarios.Models.User;

public interface LikeService extends ICrud<Like, Long> {

    Like findByRecipeAndUser(Recipe recipe, User user);

    long countByRecipe(Recipe recipe);

}
