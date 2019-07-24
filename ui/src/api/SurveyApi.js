import axios from 'axios';

const USE_MOCKS = process.env.USE_MOCKS;
const config = { serviceUrl: '/entando-survey' };

export const setServiceUrl = (serviceUrl) => {
  config.serviceUrl = serviceUrl;
};

export const fetchSurvey = async (surveyId) => {
  if (USE_MOCKS) {
    return require('../__mocks__/survey.json');
  }
  const { data } = await axios.get(`${config.serviceUrl}/surveys/${surveyId}`);
  return data;
};