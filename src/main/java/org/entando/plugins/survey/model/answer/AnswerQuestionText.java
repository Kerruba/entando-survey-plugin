package org.entando.plugins.survey.model.answer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.AnswerQuestion;

import javax.persistence.*;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "answer_question_text")
@Inheritance(strategy = InheritanceType.JOINED)
public class AnswerQuestionText extends AnswerQuestion {
    @Lob
    @Column(name = "answer_text")
    String answerText;

    @Builder
    public AnswerQuestionText(UUID id, Question question, String answerText) {
        super(id, question);

        this.answerText = answerText;
    }
}
