import React, { Component } from "react";
import DomesticHelpService from "../services/DomesticHelpService";
let today = new Date();
//const regExp1 = RegExp(/^[0-9]+$/);
const regExp2 = RegExp(/^[0-5]+$/);
const formValid = ({ isError, ...rest }) => {
  let valid = true;

  Object.values(isError).forEach((val) => {
    val.length > 0 && (valid = false);
  });

  Object.values(rest).forEach((val) => {
    val == null && (valid = false);
  });

  return valid;
};
class CreateDomesticHelpComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      createdBy: "",
      modifiedBy: "",

      domestichelpId: "",
      flatNo: "",
      ownerName: "",
      name: "",
      helpType: "",
      arrivalTime: "",
      departureTime: "",
      date: "",
      isError: {
        createdBy: "",
        modifiedBy: "",
        domestichelpId: "",
        flatNo: "",
        ownerName: "",
        name: "",
        helpType: "",
      },
      disabled: true,
    };
    this.changeCreatedByHandler = this.changeCreatedByHandler.bind(this);
    this.changeModifiedByHandler = this.changeModifiedByHandler.bind(this);
    this.changeDomesticHelpIdHandler = this.changeDomesticHelpIdHandler.bind(
      this
    );
    this.changeFlatNoHandler = this.changeFlatNoHandler.bind(this);
    this.changeOwnerNameHandler = this.changeOwnerNameHandler.bind(this);
    this.changeNameHandler = this.changeNameHandler.bind(this);
    this.changeHelpTypeHandler = this.changeHelpTypeHandler.bind(this);
    this.changeArrivalTimeHandler = this.changeArrivalTimeHandler.bind(this);
    this.changeDepartureTimeHandler = this.changeDepartureTimeHandler.bind(
      this
    );
    this.changeDateHandler = this.changeDateHandler.bind(this);
    this.saveDomesticHelp = this.saveDomesticHelp.bind(this);
  }
  saveDomesticHelp = (e) => {
    e.preventDefault();
    if (formValid(this.state)) {
      let domestichelp = {
        createdBy: this.state.createdBy,
        modifiedBy: this.state.modifiedBy,
        domestichelpId: this.state.domestichelpId,
        flatNo: this.state.flatNo,
        ownerName: this.state.ownerName,
        name: this.state.name,
        helpType: this.state.helpType,
        arrivalTime: this.state.arrivalTime,
        departureTime: this.state.departureTime,
        date: today,
      };
      console.log("domestichelp => " + JSON.stringify(domestichelp));
      DomesticHelpService.createDomesticHelp(domestichelp).then((res) => {
        this.props.history.push("/domesticHelp");
        alert("form is valid");
      });
    } else {
      alert("form is invalid");
    }
  };
  changeCreatedByHandler = (event) => {
    this.setState({ createdBy: event.target.value });
  };
  changeModifiedByHandler = (event) => {
    this.setState({ modifiedBy: event.target.value });
  };
  changeDomesticHelpIdHandler = (event) => {
    this.setState({ domestichelpId: event.target.value });
  };
  changeFlatNoHandler = (event) => {
    this.setState({ flatNo: event.target.value });
  };
  changeOwnerNameHandler = (event) => {
    this.setState({ ownerName: event.target.value });
  };
  changeNameHandler = (event) => {
    this.setState({ name: event.target.value });
  };
  changeHelpTypeHandler = (event) => {
    this.setState({ helpType: event.target.value });
  };
  changeArrivalTimeHandler = (event) => {
    this.setState({ arrivalTime: event.target.value });
  };
  changeDepartureTimeHandler = (event) => {
    this.setState({ departureTime: event.target.value });
  };
  changeDateHandler = (event) => {
    this.setState({ date: today });
  };
  formValChange = (e) => {
    e.preventDefault();
    const { name, value } = e.target;
    let isError = { ...this.state.isError };
    switch (name) {
      case "createdBy":
        isError.createdBy =
          value.length < 5 ? "Atleast 5 characters required" : "";
        break;
      case "modifiedBy":
        isError.modifiedBy =
          value.length < 5 ? "Atleast 5 characters required" : "";
        break;
      case "domestichelpId":
        isError.domestichelpId = regExp2.test(value)
          ? ""
          : "Numeric Values Required";
        break;
      case "flatNo":
        isError.flatNo = regExp2.test(value) ? "" : "Numeric Values Required";
        break;
      case "ownerName":
        isError.ownerName =
          value.length < 5 ? "Atleast 5 characters required" : "";
        break;
      case "name":
        isError.name = value.length < 5 ? "Atleast 5 characters required" : "";
        break;
      case "helpType":
        isError.helpType =
          value.length < 5 ? "Atleast 5 characters required" : "";
        break;
      case "status":
        isError.status =
          value.length < 4 ? "Atleast 4 characters required" : "";
        break;
      case "arrivalTime":
        break;
      case "departureTime":
        break;

      default:
        break;
    }
    this.setState(
      {
        isError,
        [name]: value,
        disabled: false,
      },
      () => console.log(this.state)
    );
  };

  cancel() {
    this.props.history.push("");
  }

  render() {
    const { isError } = this.state;
    return (
      <div>
        <br></br>
        <div className="container">
          <div className="row">
            <div className="card col-md-6 offset-md-3 offset-md-3">
              <h3 className="text-center">Add DomesticHelp</h3>
              <div className="card-body">
                <form>
                  <div className="form-group">
                    <label>Created By</label>
                    <input
                      placeholder="createdBy"
                      name="createdBy"
                      className={
                        isError.createdBy.length > 0
                          ? "is-invalid form-control"
                          : "form-control"
                      }
                      value={this.state.createdBy}
                      noValidate
                      onChange={
                        (this.changeCreatedByHandler, this.formValChange)
                      }
                    />
                    {isError.createdBy.length > 0 && (
                      <span className="invalid-feedback">
                        {isError.createdBy}
                      </span>
                    )}
                  </div>
                  <div className="form-group">
                    <label>Domestic Help Id </label>
                    <input
                      placeholder="Domestic Help Id"
                      name="domestichelpId"
                      className={
                        isError.domestichelpId.length > 0
                          ? "is-invalid form-control"
                          : "form-control"
                      }
                      value={this.state.domestichelpId}
                      onChange={
                        (this.changeDomesticHelpIdHandler, this.formValChange)
                      }
                    />
                    {isError.domestichelpId.length > 0 && (
                      <span className="invalid-feedback">
                        {isError.domestichelpId}
                      </span>
                    )}
                  </div>
                  <div className="form-group">
                    <label>Flat No </label>
                    <input
                      placeholder="Flat No"
                      name="flatNo"
                      className={
                        isError.flatNo.length > 0
                          ? "is-invalid form-control"
                          : "form-control"
                      }
                      value={this.state.flatNo}
                      onChange={(this.changeFlatNoHandler, this.formValChange)}
                    />
                    {isError.flatNo.length > 0 && (
                      <span className="invalid-feedback">{isError.flatNo}</span>
                    )}
                  </div>
                  <div className="form-group">
                    <label>Owner Name</label>
                    <input
                      placeholder="Owner Name"
                      name="ownerName"
                      className={
                        isError.ownerName.length > 0
                          ? "is-invalid form-control"
                          : "form-control"
                      }
                      value={this.state.ownerName}
                      onChange={
                        (this.changeOwnerNameHandler, this.formValChange)
                      }
                    />
                    {isError.ownerName.length > 0 && (
                      <span className="invalid-feedback">
                        {isError.ownerName}
                      </span>
                    )}
                  </div>
                  <div className="form-group">
                    <label>Name</label>
                    <input
                      placeholder="Name"
                      name="name"
                      className={
                        isError.name.length > 0
                          ? "is-invalid form-control"
                          : "form-control"
                      }
                      value={this.state.name}
                      onChange={(this.changeNameHandler, this.formValChange)}
                    />
                    {isError.name.length > 0 && (
                      <span className="invalid-feedback">{isError.name}</span>
                    )}
                  </div>
                  <div className="form-group">
                    <label>Help Type</label>
                    <input
                      placeholder="Help Type"
                      name="helpType"
                      className={
                        isError.helpType.length > 0
                          ? "is-invalid form-control"
                          : "form-control"
                      }
                      value={this.state.helpType}
                      onChange={
                        (this.changeHelpTypeHandler, this.formValChange)
                      }
                    />
                    {isError.helpType.length > 0 && (
                      <span className="invalid-feedback">
                        {isError.helpType}
                      </span>
                    )}
                  </div>
                  <div className="form-group">
                    <label>Arrival Time</label>
                    <input
                      placeholder="Arrival Time"
                      name="arrivalTime"
                      className="form-control"
                      value={this.state.arrivalTime}
                      onChange={this.changeArrivalTimeHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label>Departure Time</label>
                    <input
                      placeholder="Departure Time"
                      name="departureTime"
                      className="form-control"
                      value={this.state.departureTime}
                      onChange={this.changeDepartureTimeHandler}
                    />
                  </div>
                  <div className="form-group">
                    <label>Date</label>
                    <input
                      placeholder="Date"
                      name="date"
                      className="form-control"
                      value={today}
                      onChange={this.changeDateHandler}
                    />
                  </div>

                  <button
                    disabled={this.state.disabled}
                    className="btn btn-success"
                    onClick={this.saveDomesticHelp}
                  >
                    {" "}
                    Save{" "}
                  </button>
                  <button
                    className="btn btn-danger"
                    onClick={this.cancel.bind(this)}
                    style={{ marginLeft: "10px" }}
                  >
                    Cancel
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
export default CreateDomesticHelpComponent;
