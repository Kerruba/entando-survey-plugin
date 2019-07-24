package org.entando.plugins.survey.repository.answer;

import org.entando.plugins.survey.model.answer.AnswerText;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface AnswerTextRepository extends PagingAndSortingRepository<AnswerText, UUID> {

}
