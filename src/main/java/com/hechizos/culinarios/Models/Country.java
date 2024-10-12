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
@Entity(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "idcountry")
    private Long idCountry;

    @Column(name = "iso", length = 2, nullable = false, unique = true)
    private String iso;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "nicename", length = 50, nullable = false, unique = true)
    private String niceName;

    @Column(name = "iso3", length = 3, nullable = true)
    private String iso3;

    @Column(name = "numcode", length = 1, nullable = true)
    private Integer numcode;

    @Column(name = "phonecode", length = 5, nullable = true)
    private Integer phoneCode;

    @OneToOne(mappedBy = "country", cascade = CascadeType.ALL)
    private User user;

}
