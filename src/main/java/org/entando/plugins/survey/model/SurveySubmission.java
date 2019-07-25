package org.entando.plugins.survey.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name="survey_id")
    private Survey survey;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "submission_date")
    private Date submissionDate;

    @OneToMany(mappedBy = "submission")
    @Singular List<Answer> answers;

    //TODO user?
}
