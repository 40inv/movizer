import React, { Component, Fragment } from "react";
import "./component.scss";
import Generator from "./Generator.jsx";
import Fade from "react-reveal/Fade";
import lady from "../media/lady.png";
import { Text } from "../containers/Language";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";

import * as actions from "../actions/actions.js";

class Body extends Component {
  render() {
    return (
      <div className="fragment">
        <Fragment>
          <div className="block-div1">
            <Fade left>
              <div className="title-div">
                <div className="cell-left">
                  <Text tid="choose_film" />
                  <br />
                  <Text tid="secondLine" />
                  <br />
                  <Text tid="thirdLine" />
                </div>
              </div>
              <div className="title-div">
                <div className="cell-right">
                  <img className="img" src={lady} alt="popkorn" />
                </div>
              </div>
            </Fade>
          </div>
          <div>
            <Generator />
          </div>
        </Fragment>
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

export default connect(mapStateToProps, actionsStateToProps)(Body);
