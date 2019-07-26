import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Card from 'antd/es/card';
import { adminGetSurveySubmissionDetail } from '../api/SurveyApi';

import './SurveyDetails.css';
import Feedback from '../common/Feedback';

const Question = ({ question, answer }) => (
  <Card className="questionDetail" title={question}>
    <div>{answer}</div>
  </Card>
);

const getSelectedLabels = (question, selectedKeys) => {
  return selectedKeys.map(key => question.options
    .filter(opt => opt.key === key).map(opt => opt.label)[0])
    .join(', ');
};

const answerTypes = {
  text: ({ answerText, question }) => <Question question={question.question} answer={answerText} />,
  list: ({ selectedKeys, question }) => <Question question={question.question} answer={getSelectedLabels(question, selectedKeys)} />,
  rate: ({ selectedRate, question }) => <Question question={question.question} answer={selectedRate} />,
};

export default class SurveySubmissionDetails extends Component {

  state = {
    loading: true,
    dataSource: [],
  };

  processSubmission(submission) {
    const { answers, survey } = submission;
    const { questions } = survey;
    const lookupQuestion = (questionId) => questions.filter(q => q.id === questionId)[0];
    answers.forEach(answer => answer.question = lookupQuestion(answer.questionId));
    this.setState({ ...submission });
  }

  fetchData = async () => {
    const { match: { params } } = this.props;
    const { surveyId, submissionId } = params;
    try {
      this.setState({ loading: true, error: '' });
      const { payload } = await adminGetSurveySubmissionDetail(surveyId, submissionId);
      this.processSubmission(payload);
    } catch (err) {
      this.setState({ error: err.message });
    } finally {
      this.setState({ loading: false });
    }
  };

  componentDidMount() {
    this.fetchData();
  }

  renderQuestion = answer => {
    const Answer = answerTypes[answer.type];
    return Answer && (
      <Answer key={answer.questionId} {...answer} />
    );
  };

  render() {
    const { loading, answers, error } = this.state;
    return (
      <Feedback error={error} loading={loading} onRetry={this.fetchData}>
        {() => answers.map(this.renderQuestion)}
      </Feedback>
    );
  }

}

SurveySubmissionDetails.propTypes = {
  match: PropTypes.shape({
    params: PropTypes.shape({
      surveyId: PropTypes.string.isRequired,
      submissionId: PropTypes.string.isRequired,
    }),
  }).isRequired,
};

SurveySubmissionDetails.defaultProps = {
  match: {
    params: {
      surveyId: '',
      submissionId: '' ,
    }
  }
};
