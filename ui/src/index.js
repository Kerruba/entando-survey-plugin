import React from 'react';
import ReactDOM from 'react-dom';

import 'antd/lib/button/style/css';
import 'antd/lib/divider/style/css';
import 'antd/lib/input/style/css';
import 'antd/lib/radio/style/css';
import 'antd/lib/checkbox/style/css';
import 'antd/lib/result/style/css';
import 'antd/lib/spin/style/css';

import './index.css';

import SurveyManager from './questions/SurveyManager';
import SurveyAdmin from './admin/SurveyAdmin';
import * as surveyApi from './api/SurveyApi';

class EntandoSurvey extends HTMLElement {
  connectedCallback() {
    const mountPoint = document.createElement('div');
    this.appendChild(mountPoint);

    surveyApi.setServiceUrl(this.getAttribute('service-url'));

    const surveyId = this.getAttribute('survey-id');
    ReactDOM.render(React.createElement(SurveyManager, { surveyId: surveyId }, null), mountPoint);
  }
}

class EntandoAdmin extends HTMLElement {
  connectedCallback() {
    const mountPoint = document.createElement('div');
    this.appendChild(mountPoint);

    surveyApi.setServiceUrl(this.getAttribute('service-url'));
    ReactDOM.render(React.createElement(SurveyAdmin, {}, null), mountPoint);
  }
}

customElements.define('en-survey', EntandoSurvey);
customElements.define('en-survey-admin', EntandoAdmin);