package com.hechizos.culinarios.Repositories;

import com.hechizos.culinarios.Models.Like;
import com.hechizos.culinarios.Models.Recipe;
import com.hechizos.culinarios.Models.User;

public interface LikeRepository extends GenericRepository<Like, Long> {
    Like findByRecipeAndUser(Recipe recipe, User user);

    long countByRecipe(Recipe recipe);
}
