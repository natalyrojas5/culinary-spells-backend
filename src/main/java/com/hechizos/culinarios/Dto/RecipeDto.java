package com.hechizos.culinarios.Dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeDto {

    private Long idRecipe;

    private String name;

    private String detail;

    private Integer score;

    private String cookingTime;

    private RecipeTypesDto recipeTypes;

    private UserDto user;

    private List<ImageDto> images;

    private List<StepsDto> steps;
}
