package com.hechizos.culinarios.Dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    private Long count;

    private String cookingTime;

    private Long recipeTypes;

    private String recipeTypeName;

    private UserDto user;

    private List<ImageDto> images;

    @JsonManagedReference
    private List<StepsDto> steps;
}
