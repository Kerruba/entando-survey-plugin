package org.entando.plugins.survey.model.answer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.AnswerQuestion;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "answer_question_list")
@Inheritance(strategy = InheritanceType.JOINED)
public class AnswerQuestionList extends AnswerQuestion {
    @Lob
    @Column(name = "selected_keys")
    protected List<String> selectedKeys;

    @Builder
    public AnswerQuestionList(UUID id, Question question, List<String> selectedKeys) {
        super(id, question);

        this.selectedKeys = selectedKeys;
    }
}
