import React, { Component } from "react";
import "./component.scss";
import classnames from "classnames";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import LanguageSelector from "./LanguageSelector";

import { Button } from "react-bootstrap";
import * as actions from "../actions/actions.js";

import { Text } from "../containers/Language";

class Header extends Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: true,
    };
  }

  // Adds an event listener when the component is mount.
  componentDidMount() {
    window.addEventListener("scroll", this.handleScroll);
  }

  // Remove the event listener when the component is unmount.
  componentWillUnmount() {
    window.removeEventListener("scroll", this.handleScroll);
  }

  // Hide or show the menu.
  handleScroll = () => {
    const { prevScrollpos } = this.state;

    const currentScrollPos = window.pageYOffset;
    const visible = prevScrollpos > currentScrollPos;

    this.setState({
      prevScrollpos: currentScrollPos,
      visible,
    });
  };

  render() {
    return (
      <div>
        <nav
          className={classnames("navbar navbar-light px-2 headerColor", {
            "navbar--hidden": !this.state.visible,
          })}
        >
          <div className="container">
            <div className="navbar-header projectNameHeader">
              <Text tid="project_name"></Text>
            </div>
          </div>
          <div>
            <LanguageSelector />
          </div>
        </nav>
      </div>
    );
  }
}

function mapStateToProps(state) {
  return {
    globalState: state.globalState,
  };
}

function actionsStateToProps(dispatch) {
  return {
    actions: bindActionCreators(actions, dispatch),
  };
}

export default connect(mapStateToProps, actionsStateToProps)(Header);
