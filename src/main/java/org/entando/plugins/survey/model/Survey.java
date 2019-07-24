package org.entando.plugins.survey.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "surveys")
public class Survey {

    @Id
    @Column(name = "id")
    UUID id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Singular
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Question> questions;
}
