package com.hechizos.culinarios.Repositories;

import com.hechizos.culinarios.Models.Recipe;
import com.hechizos.culinarios.Models.User;
import java.util.List;

public interface RecipeRepository extends GenericRepository<Recipe, Long> {

    List<Recipe> findByUser(User user);
}
