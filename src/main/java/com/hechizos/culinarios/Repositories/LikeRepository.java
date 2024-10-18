package com.hechizos.culinarios.Repositories;

import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.hechizos.culinarios.Models.Like;
import com.hechizos.culinarios.Models.Recipe;
import com.hechizos.culinarios.Models.User;

public interface LikeRepository extends GenericRepository<Like, Long> {
    Like findByRecipeAndUser(Recipe recipe, User user);

    long countByRecipe(Recipe recipe);

    @Query(value = "SELECT idrecipe FROM likes GROUP BY idrecipe ORDER BY COUNT(*) DESC LIMIT 1", nativeQuery = true)
    Long findMostLikedRecipe();

    @Query(value = "SELECT idrecipe FROM likes GROUP BY idrecipe ORDER BY COUNT(*) DESC LIMIT 2", nativeQuery = true)
    List<Long> findMostLikedRecipes();
}
