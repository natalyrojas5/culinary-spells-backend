package com.hechizos.culinarios.Repositories;

import java.util.*;

import com.hechizos.culinarios.Models.Images;
import com.hechizos.culinarios.Models.Recipe;

public interface ImagesRepository extends GenericRepository<Images, Long> {

    Optional<Images> findByRecipeAndMajor(Recipe recipe, Integer major);
}
