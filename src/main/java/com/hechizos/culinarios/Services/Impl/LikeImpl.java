package com.hechizos.culinarios.Services.Impl;

import org.springframework.stereotype.Service;

import com.hechizos.culinarios.Models.Like;
import com.hechizos.culinarios.Models.Recipe;
import com.hechizos.culinarios.Models.User;
import com.hechizos.culinarios.Repositories.GenericRepository;
import com.hechizos.culinarios.Repositories.LikeRepository;
import com.hechizos.culinarios.Services.LikeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeImpl extends CrudImpl<Like, Long> implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    protected GenericRepository<Like, Long> getRepository() {
        return likeRepository;
    }

    public Like findByRecipeAndUser(Recipe recipe, User user) {
        return likeRepository.findByRecipeAndUser(recipe, user);
    }

    public long countByRecipe(Recipe recipe) {
        return likeRepository.countByRecipe(recipe);
    }

}
