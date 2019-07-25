package org.entando.plugins.survey.dto.answer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.Question;

@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnswerRateDto.class, name = "rate"),
        @JsonSubTypes.Type(value = AnswerListDto.class, name = "listSubmissions"),
        @JsonSubTypes.Type(value = AnswerTextDto.class, name = "text")
})
public abstract class AnswerDto {

    protected String questionId;
    protected Question.QuestionType type;

    public abstract Answer toModel();
}
