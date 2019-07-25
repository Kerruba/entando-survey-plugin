package org.entando.plugins.survey.dto.question;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.question.QuestionList;
import org.entando.plugins.survey.model.question.QuestionListOption;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionListDto extends QuestionDto {

    private List<QuestionListOptionDto> options;
    private boolean multipleChoice;

    @Builder
    public QuestionListDto(String id, String question, int order, @Singular List<QuestionListOptionDto> options, boolean multipleChoice) {
        super(id, question, order);

        this.options = options;
        this.multipleChoice = multipleChoice;
    }

    @Override
    public Question toModel() {
        UUID questionId = UUID.randomUUID();
        QuestionList.QuestionListBuilder builder = QuestionList.builder()
                .order(order)
                .question(question)
                .multipleChoice(multipleChoice);

        for(QuestionListOptionDto dto : options) {
            builder.option(QuestionListOption.builder()
                    .key(dto.getKey())
                    .label(dto.getLabel())
                    .question(QuestionList.builder().id(questionId).build())
                    .build());
        }

        return builder.build();
    }
}
