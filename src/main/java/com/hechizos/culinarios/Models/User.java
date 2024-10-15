package com.hechizos.culinarios.Models;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DynamicUpdate
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "iduser")
    private Long idUser;

    @Size(min = 5, max = 50, message = "El email debe tener entre 5 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El email no es válido")
    @Column(name = "email", length = 50, nullable = false, unique = true)
    @NotNull(message = "El email es obligatorio")
    private String email;

    @Size(min = 8, max = 100, message = "La contraseña debe tener mas de 8 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$", message = "La contraseña debe tener al menos una letra mayúscula, una letra minúscula y un número")
    @Column(name = "password", length = 100, nullable = false)
    @NotNull(message = "La contraseña es obligatoria")
    private String password;

    @Size(min = 5, max = 50, message = "El nombre debe tener entre 5 y 50 caracteres")
    @Column(name = "name", length = 50, nullable = false)
    @NotNull(message = "El nombre es obligatorio")
    private String name;

    @Column(name = "lastname", length = 50, nullable = true)
    private String lastname;

    @OneToOne
    @JoinColumn(name = "idcountry", nullable = false, foreignKey = @ForeignKey(name = "fk_country_user"))
    @NotNull(message = "Debe seleccionar un país")
    private Country country;

    @Column(name = "gender", length = 1, nullable = false)
    @NotNull(message = "Debe seleccionar un género")
    private Integer gender;

    @Column(name = "created_at", nullable = true)
    @CreationTimestamp(source = SourceType.DB)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = true)
    @UpdateTimestamp(source = SourceType.DB)
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Recipe> recipe;

}
