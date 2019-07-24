package org.entando.plugins.survey.model.answer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.AnswerQuestion;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "answer_question_rate")
@Inheritance(strategy = InheritanceType.JOINED)
public class AnswerQuestionRate extends AnswerQuestion {
    @Column(name = "selected_rate")
    int selectedRate;

    @Builder
    public AnswerQuestionRate(UUID id, Question question, int selectedRate) {
        super(id, question);

        this.selectedRate = selectedRate;
    }
}
