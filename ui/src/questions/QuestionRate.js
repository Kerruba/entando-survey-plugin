import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Radio from 'antd/es/radio';
import './Question.css';
import QuestionList from './QuestionList';

export default class QuestionRate extends Component {

  state = { value: null, values: [] };

  onChange = e => {
    const { value } = e.target;
    this.setState({ value });
    this.props.onValueChange(value, true);
  };

  componentWillMount() {
    this.processProps(this.props);
  }

  processProps(props) {
    const { maxRate = 10, minRate = 0 } = props;
    const quantity = maxRate - minRate;
    const values = Array.apply(null, Array(quantity + 1)).map((_, index) => index + minRate);
    this.setState({ values });
  }

  componentWillReceiveProps(nextProps, nextContext) {
    this.processProps(nextProps);
  }

  render() {
    const { question } = this.props;
    const { values } = this.state;
    return (
      <div className="question-container">
        <div className="question">{question}</div>
        <Radio.Group value={this.state.value} onChange={this.onChange}>
          {values.map(value => (
            <Radio.Button key={value} value={value}>{value}</Radio.Button>
          ))}
        </Radio.Group>
      </div>
    );
  }
}

QuestionList.propTypes = {
  id: PropTypes.string.isRequired,
  type: PropTypes.string.isRequired,
  question: PropTypes.string.isRequired,
  minRate: PropTypes.number.isRequired,
  maxRate: PropTypes.number.isRequired,
};

QuestionList.defaultProps = {
  id: '',
  type: 'rate',
  question: '',
  minRate: 0,
  maxRate: 10,
};