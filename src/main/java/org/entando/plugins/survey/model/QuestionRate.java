package org.entando.plugins.survey.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "question_rate")
public class QuestionRate extends Question {
    @Column(name = "min_rate")
    protected int minRate;

    @Column(name = "max_rate")
    protected int maxRate;

    @Builder
    public QuestionRate(UUID id, String question, int minRate, int maxRate) {
        super(id, QuestionType.rate, question);

        this.minRate = minRate;
        this.maxRate = maxRate;
    }
}
