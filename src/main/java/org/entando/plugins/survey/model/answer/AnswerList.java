package org.entando.plugins.survey.model.answer;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.answer.AnswerDto;
import org.entando.plugins.survey.dto.answer.AnswerListDto;
import org.entando.plugins.survey.model.question.Question;
import org.entando.plugins.survey.model.survey.SurveySubmission;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("list")
public class AnswerList extends Answer {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "answer_id")
    private List<AnswerListOption> selectedOptions;

    @Builder
    public AnswerList(UUID id, Question question, SurveySubmission submission, List<AnswerListOption> selectedOptions) {
        super(id, Question.QuestionType.list, question, submission);

        this.selectedOptions = selectedOptions;
    }

    @Override
    public AnswerDto toDto() {
        return AnswerListDto.builder()
                .questionId(getQuestion().getId().toString())
                .selectedKeys(selectedOptions.stream().map(AnswerListOption::getKey).collect(Collectors.toList()))
                .build();
    }
}
