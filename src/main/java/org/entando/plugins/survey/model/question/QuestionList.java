package org.entando.plugins.survey.model.question;

import lombok.*;
import org.entando.plugins.survey.dto.answer.AnswerListDto;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.dto.question.QuestionListDto;
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
    @Lob
    protected List<Option> options;

    @Column(name = "multiple_choice")
    protected boolean multipleChoice;

    @Builder
    public QuestionList(UUID id, String question, Survey survey, boolean multipleChoice, @Singular List<Option> options) {
        super(id, QuestionType.list, question, survey);

        this.multipleChoice = multipleChoice;
        this.options = options;
    }

    @Override
    public QuestionDto toDto() {
        return QuestionListDto.builder()
                .id(id.toString())
                .question(question)
                .multipleChoice(multipleChoice)
                .options(options)
                .build();
    }

    @Data
    public static class Option {
        protected String key;
        protected String label;

        public Option(String key, String label) {
            this.key = key;
            this.label = label;
        }
    }
}
