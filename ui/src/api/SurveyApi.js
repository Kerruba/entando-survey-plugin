import axios from 'axios';

const USE_MOCKS = process.env.USE_MOCKS;
const config = { serviceUrl: '/entando-survey' };

export const setServiceUrl = (serviceUrl) => {
  if (serviceUrl) config.serviceUrl = serviceUrl;
};

export const fetchSurvey = async (surveyId) => {
  if (USE_MOCKS) {
    return require('../__mocks__/survey.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/surveys/${surveyId}`);
  return data;
};

export const submitSurvey = async (surveyId, answers) => {
  if (USE_MOCKS) {
    return;
  }
  const { data } = await axios.post(`${config.serviceUrl}/surveys/${surveyId}/submissions`, { answers });
  return data;
};

export const adminListSurveys = async ({ page = 1 }) => {
  if (USE_MOCKS) {
    return require('../__mocks__/survey_list.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/surveys?page=${page}`);
  return data;
};

export const adminListSurveySubmissions = async (surveyId, { page = 1 }) => {
  if (USE_MOCKS) {
    return require('../__mocks__/survey_submission_list.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/surveys/${surveyId}/submissions?page=${page}`);
  return data;
};

export const adminGetSurveyDetail = async (surveyId) => {
  if (USE_MOCKS) {
    return require('../__mocks__/survey_details.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/surveys/${surveyId}`);
  return data;
};

export const adminGetQuestionSubmissions = async (surveyId, questionId) => {
  if (USE_MOCKS || true) {
    return require('../__mocks__/survey_question_text_submissions.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/admin/survey/${surveyId}/answers/${questionId}`);
  return data;
};