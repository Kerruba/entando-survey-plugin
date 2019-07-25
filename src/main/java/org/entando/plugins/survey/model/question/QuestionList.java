package org.entando.plugins.survey.model.question;

import lombok.*;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.dto.question.QuestionListDto;
import org.entando.plugins.survey.dto.question.QuestionListOptionDto;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.Survey;

import javax.persistence.*;
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

    @Builder
    public QuestionList(UUID id, String question, int order, Survey survey, boolean multipleChoice, @Singular List<QuestionListOption> options) {
        super(id, QuestionType.list, question, order, survey);

        this.multipleChoice = multipleChoice;
        this.options = options;
    }

    @Override
    public QuestionDto toDto() {
        return QuestionListDto.builder()
                .id(getId().toString())
                .order(getOrder())
                .question(getQuestion())
                .multipleChoice(multipleChoice)
                .options(options.stream().map(QuestionListOptionDto::new).collect(Collectors.toList()))
                .build();
    }
}
