import React, { Component } from "react";

class HeaderComponent extends Component {
  render() {
    return (
      <div className="menu-bar">
        <header className="header">
          <nav className="navbar navbar-expand-lg navbar-light">
            <div>
              <a
                href="https://github.com/AdityaKartikeyan/ApartmentSecurityManagementFinal.git"
                className="navbar-brand"
              >
                Apartment Security Management
              </a>
            </div>

            <button
              className="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarNav"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <i className="fas fa-bars"></i>
            </button>
            <div className="collapse navbar-collapse" id="navbarNav">
              <ul className="navbar-nav ml-auto">
                <li className="nav-item dropdown">
                  <a
                    class="nav-link dropdown-toggle"
                    href="/guardTraining"
                    id="navbarDropdown"
                    role="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    <strong>Guard Management</strong>
                  </a>
                  <div
                    className="dropdown-menu"
                    aria-labelledby="navbarDropdown"
                  >
                    <a className="dropdown-item" href="/guardTraining">
                      Guard Training
                    </a>
                    <a className="dropdown-item" href="/GuardSalary">
                      Guard Salary
                    </a>

                    <a className="dropdown-item" href="/GuardShift">
                      Guard Shift
                    </a>
                  </div>
                </li>
                <li className="nav-item">
                  <a className="nav-link" href="/visitors">
                    <strong>Visitors</strong>
                  </a>
                </li>
                <li className="nav-item">
                  <a className="nav-link" href="/vehicle">
                    <strong>Vehicle</strong>
                  </a>
                </li>
                <li className="nav-item dropdown">
                  <a
                    class="nav-link dropdown-toggle"
                    href="/flat"
                    id="navbarDropdown"
                    role="button"
                    data-toggle="dropdown"
                    aria-haspopup="true"
                    aria-expanded="false"
                  >
                    <strong>Flat Management</strong>
                  </a>
                  <div
                    className="dropdown-menu"
                    aria-labelledby="navbarDropdown"
                  >
                    <a className="dropdown-item" href="/flat">
                      Flat
                    </a>
                    <a className="dropdown-item" href="/domesticHelp">
                      Domestic Help
                    </a>
                  </div>
                </li>
                <li className="nav-item">
                  <a className="nav-link" href="/getAll">
                    <strong>Delivery</strong>
                  </a>
                </li>
              </ul>
            </div>
          </nav>
        </header>
      </div>
    );
  }
}

export default HeaderComponent;
