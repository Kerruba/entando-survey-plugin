import React, { Component } from 'react';
import { adminListSurveys } from '../api/SurveyApi';
import Table from 'antd/es/table';
import { Link } from 'react-router-dom';

import 'antd/lib/table/style/css';

const columns = [
  {
    title: 'Title',
    dataIndex: 'title',
    key: 'title',
    render: (title, row) => <Link to={`/${row.id}`}>{title}</Link>
  },
  {
    title: 'Submissions',
    dataIndex: 'submissions',
    key: 'submissions',
  },
  {
    title: 'Description',
    dataIndex: 'description',
    key: 'description',
  },
];

export default class SurveyList extends Component {

  state = {
    loading: false,
    dataSource: [],
  };

  componentDidMount() {
    this.fetchData();
  }

  async fetchData() {
    try {
      this.setState({ loading: true });
      const { payload, metadata } = await adminListSurveys({ page: 1 });
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
