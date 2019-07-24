import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Radio from 'antd/es/radio';
import Checkbox from 'antd/es/checkbox';
import './Question.css';

export default class QuestionList extends Component {

  state = { value: null, values: [] };

  onChange = e => {
    const { multipleChoice } = this.props;
    const { values } = this.state;
    const { value, checked } = e.target;

    if (multipleChoice) {
      const index = values.indexOf(value);
      const contains = index !== -1;

      if (contains && !checked) {
        values.splice(index, 1);
      } else if (!contains && checked) {
        values.push(value);
      }

      this.setState({ values });
      this.onValueChange(values);
    } else {
      this.setState({ value });
      this.onValueChange(value);
    }
  };

  onValueChange(value) {
    const { multipleChoice, onValueChange } = this.props;
    if (multipleChoice) {
      onValueChange(value, value.length > 0);
    } else {
      onValueChange(value, !!value);
    }
  }

  render() {
    const { question, options, multipleChoice } = this.props;
    return (
      <div className="question-container">
        <div className="question">{question}</div>
        {multipleChoice ? (
          <div>
            {options.map(item => (
              <Checkbox key={item.key} className="option" value={item.key} onChange={this.onChange}>
                {item.label}
              </Checkbox>
            ))}
          </div>
        ): (
          <Radio.Group onChange={this.onChange} value={this.state.value}>
            {options.map(item => (
              <Radio key={item.key} className="option" value={item.key}>
                {item.label}
              </Radio>
            ))}
          </Radio.Group>
        )}
      </div>
    );
  }
}

QuestionList.propTypes = {
  id: PropTypes.string.isRequired,
  type: PropTypes.string.isRequired,
  question: PropTypes.string.isRequired,

  multipleChoice: PropTypes.bool.isRequired,
  options: PropTypes.arrayOf(PropTypes.shape({
    key: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
  })).isRequired,
};

QuestionList.defaultProps = {
  id: '',
  type: 'list',
  question: '',
  multipleChoice: false,
  options: [],
};