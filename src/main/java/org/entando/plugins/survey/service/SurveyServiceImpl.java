package org.entando.plugins.survey.service;

import org.entando.plugins.survey.exception.SurveyNotFoundException;
import org.entando.plugins.survey.model.QuestionList;
import org.entando.plugins.survey.model.QuestionRate;
import org.entando.plugins.survey.model.QuestionText;
import org.entando.plugins.survey.model.Survey;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Override
    public Survey get(final String uuid) {
        if (!uuid.equals("1")) { //Hardcoded for POC
            throw new SurveyNotFoundException();
        }

        QuestionList questionList1 = QuestionList.builder()
            .question("What is the best programming language in your opinion?")
            .id(UUID.randomUUID())
            .option(new QuestionList.Option("javascript", "Javascript"))
            .option(new QuestionList.Option("golang", "Go"))
            .option(new QuestionList.Option("java", "Java"))
            .build();

        QuestionText questionText1 = QuestionText.builder()
            .question("Describe your experience with this programming language")
            .id(UUID.randomUUID())
            .minLength(10)
            .maxLength(200)
            .build();

        QuestionRate questionRate1 = QuestionRate.builder()
            .question("From 0 to 10, what is your level of expertise with this programming language")
            .id(UUID.randomUUID())
            .minRate(0)
            .maxRate(10)
            .build();

        return Survey.builder()
            .title("INAIL POC")
            .description("Sample Survey for INAIL POC")
            .id(UUID.randomUUID())
            .question(questionList1)
            .question(questionText1)
            .question(questionRate1)
            .build();
    }
}
