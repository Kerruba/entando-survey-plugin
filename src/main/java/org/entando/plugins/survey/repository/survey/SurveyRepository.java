package org.entando.plugins.survey.repository.survey;

import org.entando.plugins.survey.model.Survey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface SurveyRepository extends PagingAndSortingRepository<Survey, UUID> {

}
