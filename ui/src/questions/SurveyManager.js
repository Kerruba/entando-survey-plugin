import React, { Component } from 'react';
import Survey from './Survey';
import PropTypes from 'prop-types';
import * as surveyApi from '../api/SurveyApi';

const Loader = () => <div>Carregando...</div>;

export default class SurveyManager extends Component {
  state = {
    loading: false,
    questions: []
  };

  componentWillMount() {
    const { serviceUrl } = this.props;
    surveyApi.setServiceUrl(serviceUrl);
  }

  componentDidMount() {
    this.fetchSurvey();
  }

  async fetchSurvey() {
    const { surveyId } = this.props;
    this.setState({ loading: true });
    surveyApi.fetchSurvey(surveyId).then((survey) => {
      this.setState({ ...survey });
    }).catch(err => {
      console.log(err);
    }).then(() => this.setState({ loading: false }));
  }

  render() {
    return this.state.loading
      ? <Loader />
      : <Survey questions={this.state.questions} />;
  }
}

SurveyManager.propTypes = {
  serviceUrl: PropTypes.string.isRequired,
  surveyId: PropTypes.string.isRequired,
};

SurveyManager.defaultProps = {
  serviceUrl: '',
  surveyId: ''
};