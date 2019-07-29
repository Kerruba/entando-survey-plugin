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
      props.questions.forEach(({ id, type, when }) => {
        answers[id] = { valid: !!when, questionId: id, type, visible: !when };
      });
      this.setState({ answers });
    }
  }

  submit = () => {
    const { onSubmit } = this.props;
    const { answers } = this.state;
    const request = Object.keys(answers)
      .map(questionId => {
        const { type, value, visible } = answers[questionId];
        return { type, value, questionId, visible };
      })
      .filter(({ visible }) => visible)
      .map(({ type, value, questionId }) => {
        const result = { questionId, type };

        // in the future we will evolve to more than one property
        // when we have more types, it will make more sense
        // to create a better archicecture to scale
        if (type === 'rate') {
          result['selectedRate'] = value;
        } else if (type === 'text') {
          result['answerText'] = value
        } else if (type === 'list') {
          result['selectedKeys'] = value
        }

        return result;
      });
    onSubmit(request);
  };

  onValueChange = ({ id, key }) => {
    return (value, valid) => {
      const stateAnswers = this.state.answers;
      const stateAnswer = stateAnswers[id];
      const answer = { ...stateAnswer };

      answer.valid = valid;
      if (valid) answer.value = value;

      const answers = { ...stateAnswers, [id]: answer };

      this.props.questions.filter(question => question.when && question.when.question === key)
        .forEach(question => {
          const answer = answers[question.id];
          const oldVisible = answer.visible;
          answer.visible = this.evaluate(question.when.expression, value);
          if (!oldVisible && answer.visible) {
            answer.valid = false;
          }
        });

      this.setAnswers(answers);
    };
  };

  setAnswers(answers) {
    let finished = true;
    for (let key in answers) {
      if (answers.hasOwnProperty(key)) {
        const answer = answers[key];
        finished &= answer.valid || !answer.visible;
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

  evaluate = (expression, value) => {
    const result = /(!|=|<|>|<=|>=) (.*)/g.exec(expression);
    if (result) {
      const operator = result[1];
      const comparing = result[2];
      switch (operator) {
        case '=':
          if (typeof value === "number")
            return Number(comparing) === value;
          else if (typeof value === "string")
            return comparing === value;
          else if (value && value.length)
            return comparing === value[0];
          else return false;
        case '!=': return typeof value === "number" ? Number(comparing) !== value : comparing !== value;
        case '<': return Number(comparing) < value;
        case '>': return Number(comparing) > value;
        case '<=': return Number(comparing) >= value;
        case '>=': return Number(comparing) >= value;
        default: return false;
      }
    }
  };

  shouldRender = question => {
    return this.state.answers[question.id].visible;
  };

  render() {
    return (
      <div className="survey">
        {this.props.questions.filter(this.shouldRender).map(this.renderQuestion)}
        <Divider />
        <div style={{ padding: 20, paddingTop: 0 }}>
          <Button type="primary" disabled={!this.state.finished} onClick={this.submit}>Submit</Button>
        </div>
      </div>
    )
  }
}

Survey.propTypes = {
  onSubmit: PropTypes.func.isRequired,

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

    when: PropTypes.shape({
      question: PropTypes.string.isRequired,
      expression: PropTypes.string.isRequired,
    }),
  })).isRequired,
};


Survey.defaultProps = {
  questions: [],
  onSubmit: () => {}
};