package com.hechizos.culinarios.Models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import java.sql.Timestamp;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "El nombre es obligatorio")
    private String name;

    @Column(name = "detail", length = 200, nullable = false)
    @NotNull(message = "La descripción es obligatoria")
    private String detail;

    @Column(name = "score", nullable = true)
    private Integer score;

    @Column(name = "cookingtime", length = 15, nullable = false)
    @NotNull(message = "El tiempo de preparacion es obligatorio")
    private String cookingTime;

    @Column(name = "idrecipetype", nullable = false)
    @NotNull(message = "Debe seleccionar un tipo de receta")
    private Long recipeTypes;

    @ManyToOne
    @JoinColumn(name = "iduser", nullable = false, foreignKey = @ForeignKey(name = "fk_user_recipe"))
    private User user;

    @Column(name = "created_at", nullable = true)
    @CreationTimestamp(source = SourceType.DB)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull(message = "Debe agregar al menos una imagen")
    private List<Images> images;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull(message = "Debe agregar al menos un paso")
    private List<Steps> steps;
}