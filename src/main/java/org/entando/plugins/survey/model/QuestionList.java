package org.entando.plugins.survey.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "question_list")
public class QuestionList extends Question {
    @Lob
    protected List<Option> options;

    @Column(name = "multiple_choice")
    protected boolean multipleChoice;

    @Builder
    public QuestionList(UUID id, String question, boolean multipleChoice, @Singular List<Option> options) {
        super(id, QuestionType.list, question);

        this.multipleChoice = multipleChoice;
        this.options = options;
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
