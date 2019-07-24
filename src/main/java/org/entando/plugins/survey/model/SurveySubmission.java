package org.entando.plugins.survey.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "survey_submissions")
public class SurveySubmission {

    @Id
    @Column(name = "id")
    UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name="survey_id")
    Survey survey;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "submission_date")
    Date submissionDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "id")
    @Singular List<AnswerQuestion> answers;

    //TODO user?
}
