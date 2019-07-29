package org.entando.plugins.survey.dto.answer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.entando.plugins.survey.model.answer.Answer;

import javax.validation.constraints.NotEmpty;

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

    public abstract Answer toModel();
}
