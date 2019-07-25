package org.entando.plugins.survey.model.answer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.Question;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer_list_options")
@Builder
public class AnswerListOption {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "key")
    private String key;

    @ManyToOne(optional = false)
    @JoinColumn(name="answer_id")
    private Answer answer;

    @PrePersist
    public void setUuid() {
        this.id = UUID.randomUUID();
    }

}
