INSERT INTO surveys (id, title, description)
VALUES ('982fb709-6148-4fc8-890f-1058ade6e134', 'INAIL POC', 'Sample Survey for INAIL POC');

--INSERT INTO questions (id, survey_id, type, question, multiple_choice, options)
--VALUES ('7aae5609-da9a-491b-bc5c-921ac2a2d7ab', '982fb709-6148-4fc8-890f-1058ade6e134', 'list', 'What is the best programming language in your opinion?', false, RAWTOHEX('[B@2fa52cee'));

INSERT INTO questions (id, survey_id, type, question, min_length, max_length)
VALUES ('3e7a1072-e20f-4362-8a27-b5aba6ac07a0', '982fb709-6148-4fc8-890f-1058ade6e134', 'text', 'Describe your experience with this programming language', 20, 200);

INSERT INTO questions (id, survey_id, type, question, min_rate, max_rate)
VALUES ('3170e3b0-7795-4749-833f-30ca6f4c83a0', '982fb709-6148-4fc8-890f-1058ade6e134', 'rate', 'From 0 to 10, what is your level of expertise with this programming language', 0, 10);


INSERT INTO survey_submissions(id, survey_id, submission_date)
VALUES ('b735b282-d137-423e-b0fb-5b60da5bf47c', '982fb709-6148-4fc8-890f-1058ade6e134', NOW());

INSERT INTO answers(id, question_id, submission_id, type, selected_rate)
VALUES ('d42425ac-0d0b-4b35-96c8-7781036563fb', '3170e3b0-7795-4749-833f-30ca6f4c83a0', 'b735b282-d137-423e-b0fb-5b60da5bf47c', 'rate', 5);