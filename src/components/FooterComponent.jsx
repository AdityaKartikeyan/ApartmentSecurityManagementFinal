import React, { Component } from "react";

class FooterComponent extends Component {
  render() {
    return (
      <div>
        <footer className="footer">
          <a
            style={{ textDecoration: "none", color: "teal" }}
            href="https://github.com/AdityaKartikeyan/ApartmentSecurityManagementFinal.git"
          >
            <strong>Github link</strong>
          </a>

          <br></br>

          <span className="text-muted">All Rights Reversed 2020</span>
        </footer>
      </div>
    );
  }
}

export default FooterComponent;
