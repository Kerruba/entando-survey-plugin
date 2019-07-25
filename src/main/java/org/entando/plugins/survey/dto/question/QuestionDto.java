package org.entando.plugins.survey.dto.question;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.entando.plugins.survey.model.Question;

@Data
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuestionRateDto.class, name = "rate"),
        @JsonSubTypes.Type(value = QuestionListDto.class, name = "list"),
        @JsonSubTypes.Type(value = QuestionTextDto.class, name = "text")
})
public abstract class QuestionDto {

    protected String id;
    protected String question;
    protected int order;

    public abstract Question toModel();
}
