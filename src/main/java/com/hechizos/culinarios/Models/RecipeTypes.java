package com.hechizos.culinarios.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "recipetypes")
public class RecipeTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idrecipetype")
    private Long idRecipeType;

    @Column(name = "name", length = 20, nullable = false, unique = true)
    private String name;

    @Column(name = "detail", length = 200, nullable = false, unique = true)
    private String detail;

}
