package org.entando.plugins.survey.repository.survey;

import org.entando.plugins.survey.model.survey.SurveySubmission;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface SurveySubmissionRepository extends PagingAndSortingRepository<SurveySubmission, UUID>,
        JpaSpecificationExecutor<SurveySubmission> {
    Optional<SurveySubmission> findByIdAndSurveyId(UUID submissionId, UUID surveyId);
}
