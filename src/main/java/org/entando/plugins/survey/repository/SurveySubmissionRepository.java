package org.entando.plugins.survey.repository;

import org.entando.plugins.survey.model.SurveySubmission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface SurveySubmissionRepository extends PagingAndSortingRepository<SurveySubmission, UUID> {
    Optional<SurveySubmission> findByIdAndSurveyId(UUID submissionId, UUID surveyId);
}
