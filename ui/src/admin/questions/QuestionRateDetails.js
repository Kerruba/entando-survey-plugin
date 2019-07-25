import React, { Component } from 'react';
import Card from 'antd/es/card';
import Statistic from 'antd/es/statistic';

export default class QuestionRateDetails extends Component {
  render() {
    const { question, submissions } = this.props;

    // TODO calculate average from backend
    const { minRate, maxRate } = this.props;
    const average = (minRate + maxRate) / 2;
    
    return (
      <Card className="questionDetail" title={question} extra={`${submissions} submissions`}>
        <Statistic
          value={average}
          precision={2}
        />
      </Card>
    )
  }
}