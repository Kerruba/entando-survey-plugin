package org.entando.plugins.survey.repository.answer;

import org.entando.plugins.survey.model.answer.AnswerList;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface AnswerListRepository extends PagingAndSortingRepository<AnswerList, UUID> {

}
