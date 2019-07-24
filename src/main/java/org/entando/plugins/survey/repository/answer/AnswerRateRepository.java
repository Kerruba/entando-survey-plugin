package org.entando.plugins.survey.repository.answer;

import org.entando.plugins.survey.model.answer.AnswerRate;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface AnswerRateRepository extends PagingAndSortingRepository<AnswerRate, UUID> {

}
