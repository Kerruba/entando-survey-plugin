package org.entando.plugins.survey.dto.answer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.Question;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnswerRateDto.class, name = "rate"),
        @JsonSubTypes.Type(value = AnswerListDto.class, name = "list"),
        @JsonSubTypes.Type(value = AnswerTextDto.class, name = "text")
})
public abstract class AnswerDto {

    @NotEmpty
    protected String questionId;

    @NotNull
    protected Question.QuestionType type;

    public abstract Answer toModel();
}
