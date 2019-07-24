package org.entando.plugins.survey.model.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.SurveySubmission;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question_list_options")
@Builder
public class QuestionListOption {

    @Id
    @Column(name = "id")
    protected UUID id;

    @Column(name = "key")
    protected String key;

    @Column(name = "label")
    protected String label;

    @ManyToOne(optional = false)
    @JoinColumn(name="question_id")
    protected Question question;

}
