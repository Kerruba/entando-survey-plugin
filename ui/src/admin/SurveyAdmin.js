import React, { Component } from 'react';
import { HashRouter as Router, Route } from 'react-router-dom';

import 'antd/lib/table/style/css';
import 'antd/lib/tabs/style/css';
import 'antd/lib/card/style/css';
import 'antd/lib/progress/style/css';
import 'antd/lib/statistic/style/css';
import 'antd/lib/row/style/css';
import 'antd/lib/col/style/css';
import 'antd/lib/comment/style/css';

import SurveyList from './SurveyList';
import SurveyDetails from './SurveyDetails';

export default class SurveyAdmin extends Component {
  render() {
    return (
      <Router>
        <Route path="/" exact={true} component={SurveyList} />
        <Route path="/:id" component={SurveyDetails} />
      </Router>
    )
  }
}