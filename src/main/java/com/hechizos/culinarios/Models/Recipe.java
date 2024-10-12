package com.hechizos.culinarios.Models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idrecipe")
    private Long idRecipe;

    @Column(name = "name", length = 80, nullable = false, unique = true)
    private String name;

    @Column(name = "detail", length = 200, nullable = false)
    private String detail;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "cookingtime", length = 15, nullable = false)
    private String cookingTime;

    @OneToOne
    @JoinColumn(name = "idrecipetype", nullable = false, foreignKey = @ForeignKey(name = "fk_recipetypes_recipe"))
    private RecipeTypes recipeTypes;

    @OneToOne
    @JoinColumn(name = "iduser", nullable = false, foreignKey = @ForeignKey(name = "fk_user_recipe"))
    private User user;

    @Column(name = "created_at", nullable = true)
    @CreationTimestamp(source = SourceType.DB)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Images> images;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Steps> steps;
}