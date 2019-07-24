--Insert a Survey
INSERT INTO surveys (id, title, description)
VALUES ('982fb709-6148-4fc8-890f-1058ade6e134', 'INAIL POC', 'Sample Survey for INAIL POC');

INSERT INTO questions (id, survey_id, type, norder, question, min_length, max_length)
VALUES ('3e7a1072-e20f-4362-8a27-b5aba6ac07a0', '982fb709-6148-4fc8-890f-1058ade6e134', 'text', 0, 'Describe your experience with this programming language', 20, 200);

INSERT INTO questions (id, survey_id, type, norder, question, min_rate, max_rate)
VALUES ('3170e3b0-7795-4749-833f-30ca6f4c83a0', '982fb709-6148-4fc8-890f-1058ade6e134', 'rate', 1, 'From 0 to 10, what is your level of expertise with this programming language', 0, 10);

INSERT INTO questions (id, survey_id, type, norder, question, multiple_choice)
VALUES ('7aae5609-da9a-491b-bc5c-921ac2a2d7ab', '982fb709-6148-4fc8-890f-1058ade6e134', 'list', 2, 'What is the best programming language in your opinion?', false);

INSERT INTO question_list_options (id, question_id, key, label)
VALUES ('d388980e-d152-460f-bede-111371479b5f', '7aae5609-da9a-491b-bc5c-921ac2a2d7ab', 'javascript', 'Javascript');

INSERT INTO question_list_options (id, question_id, key, label)
VALUES ('d388980e-d152-460f-bede-111371479b5e', '7aae5609-da9a-491b-bc5c-921ac2a2d7ab', 'python', 'Python');

--Insert a Submission
INSERT INTO survey_submissions(id, survey_id, submission_date)
VALUES ('b735b282-d137-423e-b0fb-5b60da5bf47c', '982fb709-6148-4fc8-890f-1058ade6e134', NOW());

INSERT INTO answers(id, question_id, submission_id, type, selected_rate)
VALUES ('d42425ac-0d0b-4b35-96c8-7781036563fb', '3170e3b0-7795-4749-833f-30ca6f4c83a0', 'b735b282-d137-423e-b0fb-5b60da5bf47c', 'rate', 5);