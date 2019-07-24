package org.entando.plugins.survey.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "answer_question")
public abstract class AnswerQuestion {
    @Id
    @Column(name = "id")
    protected UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name="question_id")
    protected Question question;


}
