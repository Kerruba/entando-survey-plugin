import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Button from 'antd/es/button';
import Divider from 'antd/es/divider';
import QuestionList from './QuestionList';
import QuestionRate from './QuestionRate';
import QuestionText from './QuestionText';

import './Survey.css';

const questionTypes = {
  list: QuestionList,
  rate: QuestionRate,
  text: QuestionText
};

export default class Survey extends Component {

  state = { answers: {}, finished: false };

  componentWillMount() {
    this.processProps(this.props);
  }

  componentWillReceiveProps(nextProps, nextContext) {
    this.processProps(nextProps);
  }

  processProps(props) {
    const answers = {};
    if (props.questions !== undefined) {
      props.questions.forEach(question => {
        answers[question.key] = { valid: false };
      });
      this.setState({ answers });
    }
  }

  submit = () => {

  };

  onValueChange = question => {
    return (value, valid) => {
      const stateAnswers = this.state.answers;
      const stateAnswer = stateAnswers[question.key];
      const answer = stateAnswer ? { ...stateAnswer } : { key: question.key };

      answer.valid = valid;
      if (valid) answer.value = value;

      const answers = { ...stateAnswers, [question.key]: answer };
      this.setAnswers(answers);
    };
  };

  setAnswers(answers) {
    console.log(answers);
    let finished = true;
    for (let key in answers) {
      if (answers.hasOwnProperty(key)) {
        finished &= answers[key].valid;
      }
    }
    this.setState({ answers, finished });
  }

  renderQuestion = question => {
    const Question = questionTypes[question.type];
    return Question && (
      <Question key={question.id} {...question} onValueChange={this.onValueChange(question)} />
    );
  };

  render() {
    return (
      <div className="survey">
        {this.props.questions.map(this.renderQuestion)}
        <Divider />
        <div style={{ padding: 20, paddingTop: 0 }}>
          <Button type="primary" disabled={!this.state.finished} onClick={this.submit}>Submit</Button>
        </div>
      </div>
    )
  }
}

Survey.propTypes = {
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
};


Survey.defaultProps = {
  questions: [],
};