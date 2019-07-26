import React, { Component } from 'react';
import Survey from './Survey';
import PropTypes from 'prop-types';
import Result from 'antd/es/result';
import Spin from 'antd/es/spin';
import { fetchSurvey, submitSurvey } from '../api/SurveyApi';
import Feedback from '../common/Feedback';

export default class SurveyManager extends Component {
  state = {
    loaded: false,
    loading: false,
    submitting: false,
    questions: []
  };

  componentDidMount() {
    this.fetchData();
  }

  async fetchData() {
    const { surveyId } = this.props;
    this.setState({ loading: true });
    fetchSurvey(surveyId)
      .then(({ payload }) => this.setState({ ...payload, loaded: true }))
      .catch(() => { this.setState({ error: 'Something bad happened' })})
      .then(() => this.setState({ loading: false }));
  }

  onSubmit = request => {
    const { surveyId } = this.props;
    this.setState({ submitting: true });
    submitSurvey(surveyId, request)
      .then(() => this.setState({ success: true }))
      .catch(() => this.setState({ error: 'Something bad happened' }))
      .then(() => this.setState({ submitting: false }))
  };

  clearError = () => {
    this.setState({ error: null });
    if (!this.state.loaded) {
      this.fetchData();
    }
  };

  renderContent = () => {
    const survey = <Survey questions={this.state.questions} onSubmit={this.onSubmit} />;
    return this.state.submitting
      ? <Spin tip="Submitting Survey">{survey}</Spin>
      : survey;
  };

  render() {
    const { success, error, loading } = this.state;
    return success ? (
      <Result status="success"
              title="The survey has been submitted successfully"
              subTitle="We really appreciate your contribution" />
    ) : (
      <Feedback error={error} loading={loading} onRetry={this.clearError}>
        {this.renderContent}
      </Feedback>
    );
  }
}

SurveyManager.propTypes = {
  surveyId: PropTypes.string.isRequired,
};

SurveyManager.defaultProps = {
  surveyId: ''
};