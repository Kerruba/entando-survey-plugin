package org.entando.plugins.survey.model.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.dto.question.QuestionEnableExpressionDto;
import org.entando.plugins.survey.dto.question.QuestionListDto;
import org.entando.plugins.survey.dto.question.QuestionListOptionDto;
import org.entando.plugins.survey.model.survey.Survey;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("list")
public class QuestionList extends Question {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private List<QuestionListOption> options;

    @Column(name = "multiple_choice")
    private boolean multipleChoice;

    public QuestionList(final UUID id) {
        super(id);
    }

    @Builder
    public QuestionList(final UUID id, String question, String key, int order, Survey survey, final QuestionEnableExpression enableExpression, boolean multipleChoice, @Singular List<QuestionListOption> options) {
        super(QuestionType.list, question, key, order, survey, enableExpression);

        setId(id);
        this.multipleChoice = multipleChoice;
        this.options = options;
    }

    @Override
    public QuestionDto toDto() {
        return QuestionListDto.builder()
                .id(getId().toString())
                .order(getOrder())
                .question(getQuestion())
                .whenExpression(enableExpression == null ? null : new QuestionEnableExpressionDto(enableExpression))
                .multipleChoice(multipleChoice)
                .options(options.stream().map(QuestionListOptionDto::new).collect(Collectors.toList()))
                .build();
    }
}
