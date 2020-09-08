import React from "react";
import "./component.scss";

function Footer() {
  return (
    <div>
      <div className="row headerColor fixed-bottom">
        <div className="col-md-12">
          <div className="footer p-3 ml-5 text-light">
            Developed by:
            <span className="font-weight-normal"> Project Fazan</span>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Footer;
