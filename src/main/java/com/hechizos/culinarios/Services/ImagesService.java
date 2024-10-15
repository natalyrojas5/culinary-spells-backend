package com.hechizos.culinarios.Services;

import java.util.*;

import com.hechizos.culinarios.Models.Images;
import com.hechizos.culinarios.Models.Recipe;

public interface ImagesService extends ICrud<Images, Long> {
    Optional<Images> findMajorImageByRecipe(Recipe recipe);
}
