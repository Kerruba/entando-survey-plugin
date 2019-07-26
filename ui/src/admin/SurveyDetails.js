import React, { Component } from 'react';
import Tabs from 'antd/es/tabs';
import PropTypes from 'prop-types';
import Loader from '../common/Loader';
import SurveyAnalytics from './SurveyAnalytics';
import SurveySubmissionList from './SurveySubmissionList';
import { adminGetSurveyDetail } from '../api/SurveyApi';

import './SurveyDetails.css';
import Feedback from '../common/Feedback';

const { TabPane } = Tabs;

export default class SurveyDetails extends Component {

  state = {
    loading: true,
    dataSource: [],
  };

  fetchData = async () => {
    const { match } = this.props;
    try {
      this.setState({ loading: true, error: '' });
      const { payload } = await adminGetSurveyDetail(match.params.id);
      payload.submissions = 376; // TODO submissions from backend
      this.setState({ survey: payload });
    } catch (err) {
      this.setState({ error: err.message });
    } finally {
      this.setState({ loading: false });
    }
  };

  componentDidMount() {
    this.fetchData();
  }
  
  renderContent = () => (
    <Tabs>
      <TabPane tab="Responses" key="responses">
        <SurveySubmissionList surveyId={this.state.survey.id} />
      </TabPane>
      <TabPane tab="Analytics" key="analytics" style={{ padding: '0 15px' }}>
        {this.state.loading ? <Loader /> : <SurveyAnalytics survey={this.state.survey} />}
      </TabPane>
    </Tabs>
  );

  render() {
    const { loading, error } = this.state;
    return (
      <Feedback error={error} loading={loading} onRetry={this.fetchData}>
        {this.renderContent}
      </Feedback>
    );
  }

}

SurveyDetails.propTypes = {
  match: PropTypes.shape({
    params: PropTypes.shape({
      id: PropTypes.string.isRequired,
    }),
  }).isRequired,
};

SurveyDetails.defaultProps = {
  match: { params: { id: '' } }
};
