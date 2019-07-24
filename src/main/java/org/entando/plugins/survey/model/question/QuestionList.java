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

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("list")
public class QuestionList extends Question {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    protected List<QuestionListOption> options;

    @Column(name = "multiple_choice")
    protected boolean multipleChoice;

    @Builder
    public QuestionList(UUID id, String question, int order, Survey survey, boolean multipleChoice, @Singular List<QuestionListOption> options) {
        super(id, QuestionType.list, question, order, survey);

        this.multipleChoice = multipleChoice;
        this.options = options;
    }

    @Override
    public QuestionDto toDto() {
        QuestionListDto.QuestionListDtoBuilder builder = QuestionListDto.builder()
                .id(id.toString())
                .order(order)
                .question(question)
                .multipleChoice(multipleChoice);

        for (QuestionListOption option : options) {
            builder.option(new QuestionListOptionDto(option.key, option.label));
        }

        return builder.build();
    }
}
