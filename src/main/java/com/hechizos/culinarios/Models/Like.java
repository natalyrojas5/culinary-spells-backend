package com.hechizos.culinarios.Models;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idlike")
    private Long idLike;

    @ManyToOne
    @JoinColumn(name = "idrecipe", nullable = false, foreignKey = @ForeignKey(name = "fk_like_recipe"))
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "iduser", nullable = false, foreignKey = @ForeignKey(name = "fk_like_user"))
    private User user;
}
