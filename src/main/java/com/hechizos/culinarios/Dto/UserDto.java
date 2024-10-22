package com.hechizos.culinarios.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String email;

    private String password;

    private String name;

    private String lastname;

    private Long idCountry;

    private String countryName;

    private Integer gender;

    private Boolean featuredRecipesExist;
}
