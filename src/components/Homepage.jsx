import React, { Component } from "react";
import Header from "./Header";
import Footer from "./Footer";
import Body from "./Body";
import "./component.scss";
import LanguageSelector from "./LanguageSelector";
import { LanguageProvider } from "../containers/Language";
class HomePage extends Component {
  render() {
    return (
      <LanguageProvider>
        <div>
          <Header />

          <Body />
          <Footer />
        </div>
      </LanguageProvider>
    );
  }
}

export default HomePage;
