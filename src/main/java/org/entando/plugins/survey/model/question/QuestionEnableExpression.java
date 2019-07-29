package org.entando.plugins.survey.model.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question_enable_expressions")
@Builder
public class QuestionEnableExpression {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "expression")
    private String expression;

    @Column(name = "question_parent_key")
    private String parentKey;

    @PrePersist
    public void setUuid() {
        this.id = UUID.randomUUID();
    }

}
