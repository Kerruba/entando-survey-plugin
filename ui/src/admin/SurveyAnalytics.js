import React, { Component, Fragment } from 'react';
import PropTypes from 'prop-types';

import QuestionListDetails from './questions/QuestionListDetails';
import QuestionRateDetails from './questions/QuestionRateDetails';
import QuestionTextDetails from './questions/QuestionTextDetails';

const questionTypes = {
  list: QuestionListDetails,
  rate: QuestionRateDetails,
  text: QuestionTextDetails
};

export default class SurveyAnalytics extends Component {

  renderQuestion = question => {
    const Question = questionTypes[question.type];
    return Question && (
      <Question key={question.id} {...question} submissions={this.props.survey.submissions}/>
    );
  };
  
  render() {
    const { survey } = this.props;
    return <Fragment>{survey.questions.map(this.renderQuestion)}</Fragment>
  }
  
}

SurveyAnalytics.propTypes = {
  survey: PropTypes.shape({
    questions: PropTypes.arrayOf(PropTypes.shape({
      id: PropTypes.string.isRequired,
      type: PropTypes.string.isRequired,
      question: PropTypes.string.isRequired,

      minRate: PropTypes.number,
      maxRate: PropTypes.number,

      multipleChoice: PropTypes.bool,
      options: PropTypes.arrayOf(PropTypes.shape({
        key: PropTypes.string.isRequired,
        label: PropTypes.string.isRequired,
      })),

      maxLength: PropTypes.number,
      minLength: PropTypes.number,
    })).isRequired,
  }),
};


SurveyAnalytics.defaultProps = {
  survey: { questions: [] }
};