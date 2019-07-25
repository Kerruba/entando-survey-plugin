import React, { Component } from 'react';
import Tabs from 'antd/es/tabs';
import Table from 'antd/es/table';
import Card from 'antd/es/card';
import Row from 'antd/es/row';
import Col from 'antd/es/col';
import Comment from 'antd/es/comment';
import Progress from 'antd/es/progress';
import Statistic from 'antd/es/statistic';
import Loader from '../common/Loader';
import { adminGetSurveyDetail, adminGetQuestionSubmissions } from '../api/SurveyApi';

import 'antd/lib/table/style/css';
import 'antd/lib/tabs/style/css';
import 'antd/lib/card/style/css';
import 'antd/lib/progress/style/css';
import 'antd/lib/statistic/style/css';
import 'antd/lib/row/style/css';
import 'antd/lib/col/style/css';
import 'antd/lib/comment/style/css';
import './SurveyDetails.css';

const { TabPane } = Tabs;

class QuestionTextDetails extends Component {
  state = { loading: true };

  componentDidMount() {
    this.fetchData();
  }

  async fetchData() {
    try {
      const { questionId, id } = this.props;
      this.setState({ loading: true });
      const { payload, metadata } = await adminGetQuestionSubmissions(questionId, id);
      this.setState({ answers: payload, metadata });
    } finally {
      this.setState({ loading: false });
    }
  }

  render() {
    const { question, submissions } = this.props;
    return (
      <Card className="questionDetail" title={question} extra={`${submissions} submissions`}>
        <Row>
          {this.state.loading ? <Loader/> : (
            this.state.answers.map(({ id, user, answer }) => (
              <Col key={id} span={12}>
                <Card>
                  <Comment
                    className="answerText"
                    author={user}
                    content={<p>{answer}</p>}
                  />
                </Card>
              </Col>
            ))
          )}
        </Row>
      </Card>
    )
  }
}

class QuestionRateDetails extends Component {
  render() {
    const { question, average, submissions } = this.props;
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

class QuestionListDetails extends Component {
  calculateProgress = submissions => {
    return this.props.submissions === 0
      ? 0
      : Number((submissions / this.props.submissions * 100).toFixed(2));
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
      render: row => `${row}`
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

const questionTypes = {
  list: QuestionListDetails,
  rate: QuestionRateDetails,
  text: QuestionTextDetails
};

export default class SurveyDetails extends Component {

  state = {
    loading: true,
    dataSource: [],
  };

  componentDidMount() {
    this.fetchData();
  }

  async fetchData() {
    try {
      this.setState({ loading: true });
      const { payload } = await adminGetSurveyDetail(this.props.surveyId);
      this.setState({ survey: payload });
    } finally {
      this.setState({ loading: false });
    }
  }

  renderQuestion = question => {
    const Question = questionTypes[question.type];
    return Question && (
      <Question key={question.id} {...question} submissions={this.state.survey.submissions}/>
    );
  };

  render() {
    const { survey } = this.state;
    return (
      <Tabs>
        <TabPane tab="Analytics" key="analytics" style={{ padding: '0 15px' }}>
          {this.state.loading ? <Loader /> : survey.questions.map(this.renderQuestion)}
        </TabPane>
        <TabPane tab="Responses" key="responses">
        </TabPane>
      </Tabs>
    );
  }

}
