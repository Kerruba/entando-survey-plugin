import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Result from 'antd/es/result';
import Button from 'antd/es/button';

import Loader from './Loader';

export default class Feedback extends Component {

  render() {
    const { loading, error, onRetry, children } = this.props;
    if (loading) {
      return <Loader />;
    }
    if (error) {
      return (
        <Result
          status="error"
          title={error}
          subTitle="Please try again"
          extra={[<Button onClick={onRetry}>Back</Button>]}
        />
      )
    }
    return children();
  }

}

Feedback.propTypes = {
  loading: PropTypes.bool,
  error: PropTypes.string,
  onRetry: PropTypes.func.isRequired,
  children: PropTypes.func.isRequired,
};

Feedback.defaultProps = {
  loading: false,
  error: '',
  onRetry: () => {},
  children: () => {},
};