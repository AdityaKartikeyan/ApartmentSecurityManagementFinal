import React, { Component } from "react";
import DomesticHelpService from "../services/DomesticHelpService";

class ListDomesticHelpComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      domestichelp: [],
    };
    this.addDomesticHelp = this.addDomesticHelp.bind(this);
    this.editDomesticHelp = this.editDomesticHelp.bind(this);
    this.deleteDomesticHelp = this.deleteDomesticHelp.bind(this);
  }
  baseEntity(domestichelpId) {
    this.props.history.push(`/getByPk5/${domestichelpId}`);
  }
  componentDidMount() {
    DomesticHelpService.getDomesticHelp().then((res) => {
      this.setState({ domestichelp: res.data });
    });
  }
  addDomesticHelp() {
    this.props.history.push("/addDomesticHelp");
  }
  editDomesticHelp(domestichelpId) {
    this.props.history.push(`/updateDomesticHelp/${domestichelpId}`);
  }
  viewDomesticHelp(domestichelpId) {
    this.props.history.push(`/getByDomesticHelpId/${domestichelpId}`);
  }
  deleteDomesticHelp(id) {
    DomesticHelpService.deleteDomesticHelp(id).then((res) => {
      this.setState({
        domestichelp: this.state.domestichelp.filter(
          (domestichelp) => domestichelp.id !== id
        ),
      });
    });
  }
  render() {
    return (
      <div>
        <h2 className="text-center">DomesticHelpList</h2>
        <div className="row">
          <button className="btn btn-primary" onClick={this.addDomesticHelp}>
            Add DomesticHelp
          </button>
        </div>
        <br></br>
        <div className="row">
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th> Domestic Help Id</th>
                <th> Flat No</th>
                <th> Owner Name</th>
                <th> Name</th>
                <th> Help Type</th>
                <th> Arrival Time</th>
                <th> Departure Time</th>
                <th> Date</th>
                <th> Action</th>
              </tr>
            </thead>
            <tbody>
              {this.state.domestichelp.map((domestichelp) => (
                <tr key={domestichelp.domestichelpId}>
                  <td> {domestichelp.domestichelpId}</td>
                  <td> {domestichelp.flatNo}</td>
                  <td> {domestichelp.ownerName}</td>
                  <td> {domestichelp.name}</td>
                  <td> {domestichelp.helpType}</td>
                  <td> {domestichelp.arrivalTime}</td>
                  <td> {domestichelp.departureTime}</td>
                  <td> {domestichelp.date}</td>
                  <td>
                    <button
                      onClick={() =>
                        this.editDomesticHelp(domestichelp.domestichelpId)
                      }
                      className="btn btn-info"
                    >
                      Update
                    </button>
                    <button
                      style={{ marginLeft: "10px" }}
                      onClick={() => this.deleteDomesticHelp(domestichelp.id)}
                      className="btn btn-danger"
                    >
                      Delete
                    </button>
                    <button
                      style={{ marginLeft: "10px" }}
                      onClick={() =>
                        this.viewDomesticHelp(domestichelp.domestichelpId)
                      }
                      className="btn btn-success"
                    >
                      View
                    </button>
                    <button
                      style={{ marginLeft: "10px" }}
                      onClick={() =>
                        this.baseEntity(domestichelp.domestichelpId)
                      }
                      className="btn btn-warning"
                    >
                      Audit
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default ListDomesticHelpComponent;
