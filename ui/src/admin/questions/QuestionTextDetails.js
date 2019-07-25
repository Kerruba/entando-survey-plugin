import React, { Component } from 'react';
import Card from 'antd/es/card';
import Row from 'antd/es/row';
import Col from 'antd/es/col';
import Comment from 'antd/es/comment';
import Loader from '../../common/Loader';
import { adminGetQuestionSubmissions } from '../../api/SurveyApi';

export default class QuestionTextDetails extends Component {
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
