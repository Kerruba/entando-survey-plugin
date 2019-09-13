import axios from 'axios';

const USE_MOCKS = process.env.USE_MOCKS;
const config = { serviceUrl: '/entando-survey', token: undefined};

const prepareAxiosConfig = () => {
  let axiosConfig = {};
  if (config.token) {
    axiosConfig = {
      headers: {'Authorization': "Bearer " + config.token}
    }
  }
  return axiosConfig;
};

export const setServiceUrl = (serviceUrl) => {
  if (serviceUrl) config.serviceUrl = serviceUrl;
};

export const setToken = (token) => {
  if (token) config.token = token;
}

export const fetchSurvey = async (surveyId) => {
  if (USE_MOCKS) {
    console.log("Using mock method");
    return require('../__mocks__/survey.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/surveys/${surveyId}`, prepareAxiosConfig());
  return data;
};

export const submitSurvey = async (surveyId, answers) => {
  if (USE_MOCKS) {
    console.log("Using mock method");
    return;
  }
  const { data } = await axios.post(`${config.serviceUrl}/surveys/${surveyId}/submissions`, { answers }, prepareAxiosConfig());
  return data;
};

export const adminListSurveys = async ({ page = 1 }) => {
  if (USE_MOCKS) {
    console.log("Using mock method");
    return require('../__mocks__/survey_list.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/surveys?page=${page}`, prepareAxiosConfig());
  return data;
};

export const adminListSurveySubmissions = async (surveyId, { page = 1 }) => {
  if (USE_MOCKS) {
    console.log("Using mock method");
    return require('../__mocks__/survey_submission_list.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/surveys/${surveyId}/submissions?page=${page}`, prepareAxiosConfig());
  return data;
};

export const adminDownloadSurveySubmissionsPDF = async (surveyId, submissionIds) => {
  if (USE_MOCKS) {
    console.log("Using mock method");
    return;
  }

  const downloadLink = document.createElement('a');
  downloadLink.href = `${config.serviceUrl}/surveys/${surveyId}/submissions/export?submissionIds=${submissionIds.join(',')}`;
  downloadLink.download = 'survey_export.pdf';
  downloadLink.click();

  console.log('Downloading PDF', downloadLink.href);

  // to avoid double clicks
  return new Promise(resolve => setTimeout(resolve, 500));
};

export const adminGetSurveyDetail = async (surveyId) => {
  if (USE_MOCKS) {
    console.log("Using mock method");
    return require('../__mocks__/survey_details.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/surveys/${surveyId}`, prepareAxiosConfig());
  return data;
};

export const adminGetSurveySubmissionDetail = async (surveyId, submissionId) => {
  if (USE_MOCKS) {
    console.log("Using mock method");
    return require('../__mocks__/survey_submission_detail.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/surveys/${surveyId}/submissions/${submissionId}`, prepareAxiosConfig());
  return data;
};

export const adminGetQuestionSubmissions = async (surveyId, questionId) => {
  if (USE_MOCKS || true) {
    console.log("Using mock method");
    return require('../__mocks__/survey_question_text_submissions.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/admin/survey/${surveyId}/answers/${questionId}`, prepareAxiosConfig());
  return data;
};
