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
public class RecipeSimpleDto {

    private Long idRecipe;

    private String name;

    private String detail;

    private Long count;

    private Boolean isLike;

    private UserDto user;

    private List<ImageDto> images;
}
