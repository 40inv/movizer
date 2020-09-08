import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import { Button } from "react-bootstrap";
import i18next from "i18next";
import { LanguageProvider } from "./containers/Language";

import * as actions from "./actions/actions.js";

function App() {
  return (
    <LanguageProvider>
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    </LanguageProvider>
  );
}

export default App;
