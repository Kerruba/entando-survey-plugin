import React, { Component, Fragment } from 'react';
import Table from 'antd/es/table';
import Button from 'antd/es/button';
import Card from 'antd/es/card';
import Spin from 'antd/es/spin';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import { adminListSurveySubmissions, adminDownloadSurveySubmissionsPDF } from '../api/SurveyApi';

import 'antd/lib/table/style/css';

const columns = [
  {
    title: '#',
    dataIndex: 'submissionId',
    key: 'submissionId',
    render: (title, row) => <Link to={`/${row.surveyId}/submissions/${row.submissionId}`}>{title}</Link>
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
    exporting: false,
    dataSource: [],
    selectedRowKeys: [],
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

  onSelectChange = selectedRowKeys => {
    this.setState({ selectedRowKeys });
  };

  exportPdf = async () => {
    const { surveyId } = this.props;
    try {
      this.setState({ exporting: true });
      await adminDownloadSurveySubmissionsPDF(surveyId, this.state.selectedRowKeys);
    } finally {
      this.setState({ exporting: false });
    }

  };

  render() {
    const { selectedRowKeys, dataSource, loading } = this.state;
    const rowSelection = { selectedRowKeys, onChange: this.onSelectChange };

    const exportButton = (
      <Button type="primary" disabled={!selectedRowKeys.length} onClick={this.exportPdf}>
        Export as PDF
      </Button>
    );

    const table = (
      <Table rowKey="submissionId"
             rowSelection={rowSelection}
             dataSource={dataSource}
             loading={loading}
             columns={columns} />
    );

    return (
      <Fragment>
        <Card title="Submissions" extra={exportButton}>
          {this.state.exporting ? <Spin tip="Exporting">table</Spin> : table}
        </Card>
      </Fragment>
    );
  }

}

SurveySubmissionList.propTypes = {
  surveyId: PropTypes.string.isRequired,
};

SurveySubmissionList.defaultProps = {
  surveyId: ''
};