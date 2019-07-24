import React from 'react';
import './App.css';
import Survey from './questions/Survey';

const questionListSingle = {
  key: 'a8c26ad0-72b1-43c3-94c1-eff10ae7196b',
  type: 'list',
  multipleChoice: false,
  question: 'What is the best programming language in your opinion?',
  options: [
    { key: 'javascript', label: 'Javascript' },
    { key: 'golang', label: 'Go' },
    { key: 'java', label: 'Java' }
  ],
};

const questionListMulti = {
  key: '206ffc71-8181-4c5a-9755-8694d90e3952',
  type: 'list',
  multipleChoice: true,
  question: 'Select the options that you think that fits this language?',
  options: [
    { key: 'frontend', label: 'Frontend' },
    { key: 'backend', label: 'Backend' },
    { key: 'infra', label: 'Infrastructure' },
    { key: 'database', label: 'Database' },
  ],
};

const questionRate = {
  key: 'dfa750ae-6b86-4660-b197-e47662e6922f',
  type: 'rate',
  question: 'How would you rate this programming language?',
  minRate: 0,
  maxRate: 10
};

const questionText = {
  key: '438b0844-eaaf-4f11-83ec-314e931592ba',
  type: 'text',
  question: 'Tell me your experience in this programming language',
  minLength: 10,
  maxLength: 200
};

const questions = [questionListSingle, questionListMulti, questionRate, questionText];

function App() {
  return <Survey questions={questions} />;
}

export default App;
