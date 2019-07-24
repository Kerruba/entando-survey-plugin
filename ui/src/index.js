import React from 'react';
import ReactDOM from 'react-dom';

import 'antd/lib/button/style/css';
import 'antd/lib/divider/style/css';
import 'antd/lib/input/style/css';
import 'antd/lib/radio/style/css';
import 'antd/lib/checkbox/style/css';

import './index.css';

import SurveyManager from './questions/SurveyManager';

class EntandoSurvey extends HTMLElement {
  connectedCallback() {
    const mountPoint = document.createElement('div');
    this.appendChild(mountPoint);

    const serviceUrl = this.getAttribute('service-url');
    const surveyId = this.getAttribute('survey-id');
    ReactDOM.render(React.createElement(SurveyManager, { serviceUrl: serviceUrl, surveyId: surveyId }, null), mountPoint);
  }
}

customElements.define('en-survey', EntandoSurvey);