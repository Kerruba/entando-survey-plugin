import React, { Component } from 'react';
import { adminListSurveySubmissions } from '../api/SurveyApi';
import Table from 'antd/es/table';
// import { Link } from 'react-router-dom';

import 'antd/lib/table/style/css';

const columns = [
  {
    title: '#',
    dataIndex: 'submissionId',
    key: 'submissionId',
    // render: (title, row) => <Link to={`/${row.submissionId}`}>{title}</Link>
  },
  {
    title: 'Date',
    dataIndex: 'submissionDate',
    key: 'submissionDate',
  },
];

export default class SurveySubmissionList extends Component {

  state = {
    loading: false,
    dataSource: [],
  };

  componentDidMount() {
    this.fetchData();
  }

  async fetchData() {
    try {
      const { surveyId } = this.props;
      this.setState({ loading: true });
      const { payload, metadata } = await adminListSurveySubmissions(surveyId, { page: 1 });
      this.setState({ dataSource: payload, metadata });
    } finally {
      this.setState({ loading: false });
    }
  }

  render() {
    return <Table rowKey="id"
                  dataSource={this.state.dataSource}
                  loading={this.state.loading}
                  columns={columns} />;
  }

}
