import React, { Component } from 'react';
import { HashRouter as Router, Route } from 'react-router-dom';

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