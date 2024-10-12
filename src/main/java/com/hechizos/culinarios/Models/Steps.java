package com.hechizos.culinarios.Models;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
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
@Entity(name = "steps")
public class Steps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idstep")
    private Long idStep;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "description", length = 200, nullable = false)
    private String detail;

    @ManyToOne
    @JoinColumn(name = "idrecipe", nullable = false, foreignKey = @ForeignKey(name = "fk_steps_recipe"))
    private Recipe recipe;

    @Column(name = "ordernum", nullable = false)
    private Integer orderNum;

    @Column(name = "created_at", nullable = true)
    @CreationTimestamp(source = SourceType.DB)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = true)
    @UpdateTimestamp(source = SourceType.DB)
    private Timestamp updatedAt;
}
