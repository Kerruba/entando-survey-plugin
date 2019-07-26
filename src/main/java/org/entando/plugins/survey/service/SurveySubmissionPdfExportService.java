package org.entando.plugins.survey.service;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.entando.plugins.survey.model.Answer;
import org.entando.plugins.survey.model.Question;
import org.entando.plugins.survey.model.Survey;
import org.entando.plugins.survey.model.SurveySubmission;
import org.entando.plugins.survey.model.answer.AnswerList;
import org.entando.plugins.survey.model.answer.AnswerRate;
import org.entando.plugins.survey.model.answer.AnswerText;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SurveySubmissionPdfExportService {

    private final static float WIDTH = 595.27563f;
    private final static float HEIGHT = 841.8898f;
    private static PDFont font_regular = null;
    private static PDFont font_medium = null;
    private static PDFont font_light = null;

    public byte[] createPdf(final List<SurveySubmission> submissions) throws IOException {
        final PDDocument document = new PDDocument();

        loadFonts(document);
        setAccessCredentials(document);

        for (SurveySubmission submission : submissions) {
            processSubmission(document, submission);
        }

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.save(out);
        return out.toByteArray();
    }

    public byte[] createPdf(final SurveySubmission submission) throws IOException {
        final PDDocument document = new PDDocument();

        loadFonts(document);
        setAccessCredentials(document);

        processSubmission(document, submission);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.save(out);
        return out.toByteArray();
    }

    private void loadFonts(PDDocument document) throws IOException {
        final ClassLoader classLoader = SurveySubmissionPdfExportService.class.getClassLoader();
        font_regular = PDType0Font.load(document, classLoader.getResourceAsStream("fonts/Roboto/Roboto-Regular.ttf"));
        font_medium = PDType0Font.load(document, classLoader.getResourceAsStream("fonts/Roboto/Roboto-Medium.ttf"));
        font_light = PDType0Font.load(document, classLoader.getResourceAsStream("fonts/Roboto/Roboto-Light.ttf"));
    }

    private void setAccessCredentials(PDDocument document) {
        final AccessPermission accessPermission = new AccessPermission();
        /*final StandardProtectionPolicy standardProtectionPolicy = new StandardProtectionPolicy(UUID.randomUUID().toString(),
                StringUtils.isEmpty(user.getDocument()) ? card.getDocument() : user.getDocument().replaceAll("\\D", ""), accessPermission);
        document.protect(standardProtectionPolicy);*/
        accessPermission.setCanPrint(true);
        accessPermission.setCanModify(false);
    }

    private static void createHeaderTitle(final PDPageContentStream contentStream, final PDDocument document, Survey survey) throws IOException {

        contentStream.setFont(font_medium, 18);
        contentStream.beginText();
        contentStream.newLineAtOffset(200, HEIGHT - 35);
        contentStream.showText(survey.getTitle());
        contentStream.endText();

        contentStream.setFont(font_light, 9);
        contentStream.beginText();
        contentStream.newLineAtOffset(200, HEIGHT - 50);
        contentStream.showText(survey.getDescription());
        contentStream.endText();

        //TODO logo should come from survey?
        final String logo = "https://i.ibb.co/zfTttqs/inail-logo.png";

        if (logo != null) {
            final URL url = new URL(logo);
            final BufferedImage bim = ImageIO.read(url);
            if (bim != null) {
                final PDImageXObject pdImage = LosslessFactory.createFromImage(document, bim);
                float ratio = (float)pdImage.getHeight() / (float)pdImage.getWidth();

                int measuredWidth = 100;
                int measuredHeight = (int) (measuredWidth * ratio);
                contentStream.drawImage(pdImage, 20, HEIGHT-20-measuredHeight, measuredWidth, measuredHeight);
            }
        }
    }

    private void processSubmission(PDDocument document, SurveySubmission submission) throws IOException {
        final PDPage page = new PDPage(PDRectangle.A4);
        final PDPageContentStream contentStream = new PDPageContentStream(document, page);

        document.addPage(page);


        Survey survey = submission.getSurvey();
        createHeaderTitle(contentStream, document, survey);

        final float margin = 40;
        final float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        final float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        final BaseTable table = new BaseTable(HEIGHT-100, 0, 20, tableWidth, margin, document, page, false, true);

        for (Question question : survey.getQuestions()){
            Answer questionAnswer = null;
            for (Answer answer : submission.getAnswers()) {
                if (answer.getQuestion().getId().equals(question.getId())) {
                    questionAnswer = answer;
                    break;
                }
            }

            createQuestionTable(document, page, table, question, questionAnswer);
        }

        table.draw();
        contentStream.close();
    }

    private static void createQuestionTable(final PDDocument document, final PDPage page, final BaseTable table, final Question question, Answer answer) throws IOException {
        final Row<PDPage> questionRow = table.createRow(5f);
        Cell<PDPage> questionCell = questionRow.createCell(100, String.format("%s)  %s", question.getOrder(), question.getQuestion()));
        questionCell.setFont(font_regular);
        questionCell.setFontSize(12);
        questionCell.setBottomPadding(0);
        questionCell.setLeftPadding(0);
        questionCell.setTextColor(Color.BLACK);

        if (answer != null) {
            String answerText = "";
            switch (answer.getType()) {
                case text:
                    answerText = ((AnswerText)answer).getAnswerText();
                    break;
                case rate:
                    answerText = String.valueOf(((AnswerRate)answer).getSelectedRate());
                    break;
                case list:
                    answerText = ((AnswerList)answer).getSelectedOptions().stream().map(option -> option.getKey() + ", ").collect(Collectors.joining());
                    answerText = answerText.substring(0, answerText.length()-2); //trim final separator
                    break;
            }


            final Row<PDPage> answerRow = table.createRow(answer.getType().equals("text") ? 30f : 5f);
            Cell<PDPage> answerCell = answerRow.createCell(100, answerText);
            questionCell.setFont(font_medium);
            answerCell.setFontSize(10);
            answerCell.setBottomPadding(10);
            answerCell.setLeftPadding(10);
            answerCell.setTextColor(Color.GRAY);
        }
    }
}

