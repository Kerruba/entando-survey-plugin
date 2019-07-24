import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Input from 'antd/es/input';
import './Question.css';
import QuestionList from './QuestionList';

const { TextArea } = Input;

export default class QuestionText extends Component {

  state = { value: '' };

  onChange = e => {
    const { onValueChange, minLength } = this.props;
    const { value } = e.target;
    this.setState({ value });
    onValueChange(value, value.length >= minLength);
  };

  render() {
    const { question, maxLength, minLength } = this.props;
    return (
      <div className="question-container">
        <div className="question">{question}</div>
        <span className="help">
          {minLength > 0 ? `Fill at least ${minLength} characters` : null}
        </span>
        <TextArea
          style={{ fontSize: 18 }}
          onChange={this.onChange}
          placeholder="Describe your answer here"
          autosize={{ minRows: 3 }}
          maxLength={maxLength}
        />
        <span className="length">
          {`${this.state.value.length}/${maxLength}`}
        </span>
      </div>
    );
  }
}

QuestionList.propTypes = {
  type: PropTypes.string.isRequired,
  question: PropTypes.string.isRequired,
  minLength: PropTypes.number.isRequired,
  maxLength: PropTypes.number.isRequired,
};

QuestionList.defaultProps = {
  type: 'text',
  question: '',
  minLength: 1,
  maxLength: 200,
};