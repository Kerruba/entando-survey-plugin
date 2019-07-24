package org.entando.plugins.survey.model.answer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.answer.AnswerDto;
import org.entando.plugins.survey.dto.answer.AnswerListDto;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.SurveySubmission;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("listSubmissions")
public class AnswerList extends Answer {
    @Lob
    @Column(name = "selected_keys")
    protected List<String> selectedKeys;

    @Builder
    public AnswerList(UUID id, Question question, SurveySubmission submission, List<String> selectedKeys) {
        super(id, Question.QuestionType.list, question, submission);

        this.selectedKeys = selectedKeys;
    }

    @Override
    public AnswerDto toDto() {
        return AnswerListDto.builder()
                .questionId(question.getId().toString())
                .selectedOptionsKeys(selectedKeys)
                .build();
    }
}
