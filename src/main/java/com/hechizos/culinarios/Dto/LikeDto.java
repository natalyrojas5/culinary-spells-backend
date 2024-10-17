package com.hechizos.culinarios.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LikeDto {

    private Long idLike;

    private Long count;

    private RecipeDto recipeDto;

    private UserDto userDto;
}
