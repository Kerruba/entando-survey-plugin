package org.entando.plugins.survey.service;

import org.entando.plugins.survey.exception.SurveyNotFoundException;
import org.entando.plugins.survey.exception.SurveySubmissionNotFoundException;
import org.entando.plugins.survey.model.*;
import org.entando.plugins.survey.model.question.QuestionList;
import org.entando.plugins.survey.model.question.QuestionRate;
import org.entando.plugins.survey.model.question.QuestionText;
import org.entando.plugins.survey.repository.SurveyRepository;
import org.entando.plugins.survey.repository.SurveySubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveySubmissionRepository surveySubmissionRepository;
    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyServiceImpl(final SurveySubmissionRepository surveySubmissionRepository, final SurveyRepository surveyRepository) {
        this.surveySubmissionRepository = surveySubmissionRepository;
        this.surveyRepository = surveyRepository;
    }

    @Override
    public Survey get(final String uuid) {
        /*if (!uuid.equals("982fb709-6148-4fc8-890f-1058ade6e134")) { //Hardcoded for POC
            throw new SurveyNotFoundException();
        }

        QuestionList questionList1 = QuestionList.builder()
            .question("What is the best programming language in your opinion?")
            .id(UUID.fromString("7aae5609-da9a-491b-bc5c-921ac2a2d7ab"))
            .option(new QuestionList.Option("javascript", "Javascript"))
            .option(new QuestionList.Option("golang", "Go"))
            .option(new QuestionList.Option("java", "Java"))
            .build();

        QuestionText questionText1 = QuestionText.builder()
            .question("Describe your experience with this programming language")
            .id(UUID.fromString("3e7a1072-e20f-4362-8a27-b5aba6ac07a0"))
            .minLength(10)
            .maxLength(200)
            .build();

        QuestionRate questionRate1 = QuestionRate.builder()
            .question("From 0 to 10, what is your level of expertise with this programming language")
            .id(UUID.fromString("3170e3b0-7795-4749-833f-30ca6f4c83a0"))
            .minRate(0)
            .maxRate(10)
            .build();

        return Survey.builder()
            .title("INAIL POC")
            .description("Sample Survey for INAIL POC")
            .id(UUID.fromString(uuid))
            .question(questionList1)
            .question(questionText1)
            .question(questionRate1)
            .build();*/

        return surveyRepository.findById(UUID.fromString(uuid))
                .orElseThrow(SurveyNotFoundException::new);
    }

    @Override
    public SurveySubmission submit(String uuid, Date date, List<AnswerQuestion> answers) {
        /*Survey survey = surveyRepository.findById(UUID.fromString(uuid))
                .orElseThrow(SurveyNotFoundException::new);*/

        SurveySubmission submission = SurveySubmission.builder()
                .id(UUID.randomUUID())
                //.survey(survey)
                .answers(answers)
                .submissionDate(date)
                .build();

        submission = surveySubmissionRepository.save(submission);

        return submission;
    }

    @Override
    public SurveySubmission getSubmission(String uuid, String submissionUuid) {
        return surveySubmissionRepository.findByIdAndSurveyId(UUID.fromString(uuid), UUID.fromString(submissionUuid))
                .orElseThrow(SurveySubmissionNotFoundException::new);
    }

    @Override
    public BasePage<SurveySubmission> list(String uuid, BasePageable page) {
        List<SurveySubmission> result = surveySubmissionRepository.findAll(PageRequest.of(page.getPage(), page.getPageSize()))
                .getContent();

        return BasePage.<SurveySubmission>builder()
            .list(result)
            .page(page.getPage())
            .pageSize(page.getPageSize())
            .build();
    }
}
