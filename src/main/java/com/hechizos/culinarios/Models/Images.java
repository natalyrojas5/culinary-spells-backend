package com.hechizos.culinarios.Models;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SourceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DynamicUpdate
@Entity(name = "images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idimage")
    private Long idImage;

    @Column(name = "idcloudinary", nullable = false, unique = true)
    private String idCloudinary;

    @Column(name = "link", length = 150, nullable = false, unique = true)
    private String link;

    @Column(name = "path", length = 50, nullable = false, unique = true)
    private String path;

    @Column(name = "major", nullable = true)
    private Integer major;

    @ManyToOne
    @JoinColumn(name = "idrecipe", nullable = false, foreignKey = @ForeignKey(name = "fk_recipe_image"))
    private Recipe recipe;

    @Column(name = "created_at", nullable = true)
    @CreationTimestamp(source = SourceType.DB)
    private Timestamp createdAt;
}
