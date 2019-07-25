import React, { Component } from 'react';
import Progress from 'antd/es/progress';
import Card from 'antd/es/card';
import Table from 'antd/es/table';

export default class QuestionListDetails extends Component {
  calculateProgress = () => {
    return Math.floor((Math.random() * 100));
  };

  columns = [
    {
      title: 'Option',
      dataIndex: 'label',
      key: 'label',
    },
    {
      title: 'Submissions',
      dataIndex: 'submissions',
      key: 'submissionsQuantity',
      render: () => `376` // TODO submissions from backend
    },
    {
      title: 'Submissions',
      dataIndex: 'submissions',
      key: 'submissionsProgress',
      render: row => <Progress percent={this.calculateProgress(row)}  />,
      width: '100%'
    },
  ];

  render() {
    const { question, options, submissions } = this.props;
    return (
      <Card className="questionDetail" title={question} extra={`${submissions} submissions`}>
        <Table rowKey="key"
               pagination={false}
               showHeader={false}
               dataSource={options}
               columns={this.columns} />
      </Card>
    )
  }
}