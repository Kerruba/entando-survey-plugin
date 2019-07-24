package org.entando.plugins.survey.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @JoinColumn(name = "survey_id")
    List<Question> questions;
}
