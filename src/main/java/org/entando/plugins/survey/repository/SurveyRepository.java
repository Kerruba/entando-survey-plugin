package org.entando.plugins.survey.repository;

import org.entando.plugins.survey.model.Survey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface SurveyRepository extends PagingAndSortingRepository<Survey, UUID> {

}
