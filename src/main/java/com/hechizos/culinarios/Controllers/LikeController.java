package com.hechizos.culinarios.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.hechizos.culinarios.Auth.JwtTokenDecoder;
import com.hechizos.culinarios.Dto.LikeDto;
import com.hechizos.culinarios.Exception.GenericResponseRecord;
import com.hechizos.culinarios.Models.Like;
import com.hechizos.culinarios.Models.Recipe;
import com.hechizos.culinarios.Models.User;
import com.hechizos.culinarios.Services.LikeService;
import com.hechizos.culinarios.Services.RecipeService;
import com.hechizos.culinarios.Services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;
    private final RecipeService recipeService;
    private final UserService userService;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    @PostMapping("/{idRecipe}")
    public ResponseEntity<GenericResponseRecord<LikeDto>> likeRecipe(@PathVariable Long idRecipe,
            @RequestHeader("Authorization") String token) throws Exception {
        Long userId = JwtTokenDecoder.getUserId(token);
        Recipe recipe = recipeService.readById(idRecipe);
        User user = userService.readById(userId);
        Like like = new Like();
        like.setRecipe(recipe);
        like.setUser(user);
        Like like1 = likeService.findByRecipeAndUser(recipe, user);
        if (like1 != null) {
            likeService.delete(like1.getIdLike());
            Long count = likeService.countByRecipe(recipe);
            LikeDto likeDto = new LikeDto();
            likeDto.setCount(count);
            return ResponseEntity
                    .ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(Arrays.asList(likeDto))));
        }
        Like like2 = likeService.save(like);
        Long count = likeService.countByRecipe(recipe);
        LikeDto likeDto = convertToDto(like2);
        likeDto.setCount(count);
        List<LikeDto> likeDtoList = new ArrayList<>();
        likeDtoList.add(likeDto);
        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(likeDtoList)));
    }

    @DeleteMapping("/{idRecipe}")
    public ResponseEntity<GenericResponseRecord<LikeDto>> unlikeRecipe(@PathVariable Long idRecipe,
            @RequestHeader("Authorization") String token) throws Exception {
        Long userId = JwtTokenDecoder.getUserId(token);
        User user = userService.readById(userId);
        Recipe recipe = recipeService.readById(idRecipe);
        Like likes = likeService.findByRecipeAndUser(recipe, user);
        Long idlike = likes.getIdLike();
        likeService.delete(idlike);
        return ResponseEntity.noContent().build();
    }

    private LikeDto convertToDto(Like obj) {
        return modelMapper.map(obj, LikeDto.class);
    }

}
