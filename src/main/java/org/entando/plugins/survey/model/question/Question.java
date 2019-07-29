package org.entando.plugins.survey.model.question;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.dto.question.QuestionDto;
import org.entando.plugins.survey.model.survey.Survey;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    discriminatorType = DiscriminatorType.STRING,
    name = "type",
    columnDefinition = "VARCHAR"
)
@Table(name = "questions")
public abstract class Question {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "key")
    private String key;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", insertable = false, updatable = false)
    private QuestionType type;

    @Column(name = "question")
    private String question;

    @Column(name = "norder")
    private int order;

    @ManyToOne(optional = false)
    @JoinColumn(name="survey_id")
    private Survey survey;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "enable_expression_id")
    protected QuestionEnableExpression enableExpression;

    public Question(final UUID id) {
        this.id = id;
    }

    public Question(final QuestionType type, final String question, String key,
                    final int order, final Survey survey, QuestionEnableExpression enableExpression) {
        this.type = type;
        this.question = question;
        this.key = key;
        this.order = order;
        this.survey = survey;
        this.enableExpression = enableExpression;
    }

    @PrePersist
    public void setUuid() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    public abstract QuestionDto toDto();

    public enum QuestionType {
        list,
        text,
        rate
    }

}
