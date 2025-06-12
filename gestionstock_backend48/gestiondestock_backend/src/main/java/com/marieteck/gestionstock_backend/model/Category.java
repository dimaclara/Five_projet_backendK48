package com.marieteck.gestionstock_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Category extends  AbstractEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "designation")
    private String designation;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;

    Category(Long id, Instant creationDate, Instant modificationDate) {
        super(id,creationDate,modificationDate );
    }


}
