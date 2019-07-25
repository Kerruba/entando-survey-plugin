import React, { Component } from 'react';
import Tabs from 'antd/es/tabs';
import Loader from '../common/Loader';
import QuestionTextDetails from './questions/QuestionTextDetails';
import QuestionRateDetails from './questions/QuestionRateDetails';
import QuestionListDetails from './questions/QuestionListDetails';
import SurveySubmissionList from './SurveySubmissionList';
import { adminGetSurveyDetail } from '../api/SurveyApi';

import './SurveyDetails.css';

const { TabPane } = Tabs;

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
    const { match } = this.props;
    try {
      this.setState({ loading: true });
      console.log(this.props);
      const { payload } = await adminGetSurveyDetail(match.params.id);
      payload.submissions = 376; // TODO submissions from backend
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
    const { survey, loading } = this.state;
    return loading ? <Loader/> : (
      <Tabs>
        <TabPane tab="Analytics" key="analytics" style={{ padding: '0 15px' }}>
          {this.state.loading ? <Loader /> : survey.questions.map(this.renderQuestion)}
        </TabPane>
        <TabPane tab="Responses" key="responses">
          <SurveySubmissionList surveyId={survey.id} />
        </TabPane>
      </Tabs>
    );
  }

}
