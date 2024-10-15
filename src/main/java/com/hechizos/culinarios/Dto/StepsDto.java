package com.hechizos.culinarios.Dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StepsDto {

    @JsonBackReference
    private RecipeDto recipe;

    private Long idStep;

    private String name;

    private String detail;

    private Integer orderNum;
}
