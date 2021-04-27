import React, { Component } from "react";
import DomesticHelpService from "../services/DomesticHelpService";
let today = new Date();
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
class UpdateDomesticHelpComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      createdBy: "",
      modifiedBy: "",
      domestichelpId: this.props.match.params.domestichelpId,
      id: "",
      flatNo: "",
      ownerName: "",
      name: "",
      helpType: "",
      arrivalTime: "",
      departureTime: "",
      isError: {
        createdBy: "",
        modifiedBy: "",
        domestichelpId: "",
        flatNo: "",
        ownerName: "",
        name: "",
        helpType: "",
        arrivalTime: "",
        departureTime: "",
      },
      disabled: true,
    };
    this.changeCreatedByHandler = this.changeCreatedByHandler.bind(this);
    this.changeModifiedByHandler = this.changeModifiedByHandler.bind(this);
    this.changeDomesticHelpIdHandler = this.changeDomesticHelpIdHandler.bind(
      this
    );
    this.changeIdHandler = this.changeIdHandler.bind(this);
    this.changeFlatNoHandler = this.changeFlatNoHandler.bind(this);
    this.changeOwnerNameHandler = this.changeOwnerNameHandler.bind(this);
    this.changeNameHandler = this.changeNameHandler.bind(this);
    this.changeHelpTypeHandler = this.changeHelpTypeHandler.bind(this);
    this.changeArrivalTimeHandler = this.changeArrivalTimeHandler.bind(this);
    this.changeDepartureTimeHandler = this.changeDepartureTimeHandler.bind(
      this
    );
    this.updateDomesticHelp = this.updateDomesticHelp.bind(this);
  }

  componentDidMount() {
    DomesticHelpService.getDomesticHelpByDomesticHelpId(
      this.state.domestichelpId
    ).then((res) => {
      let domestichelp = res.data;
      this.setState({
        createdBy: domestichelp.createdBy,
        modifiedBy: domestichelp.modifiedBy,
        id: domestichelp.id,
        domestichelpId: domestichelp.domestichelpId,
        flatNo: domestichelp.flatNo,
        ownerName: domestichelp.ownerName,
        name: domestichelp.name,
        helpType: domestichelp.helpType,
        arrivalTime: domestichelp.arrivalTime,
        departureTime: domestichelp.departureTime,
      });
    });
    console.log(this.state);
  }

  updateDomesticHelp = (e) => {
    e.preventDefault();
    if (formValid(this.state)) {
      let domestichelp = {
        createdBy: this.state.createdBy,
        modifiedBy: this.state.modifiedBy,
        id: this.state.id,
        domestichelpId: this.state.domestichelpId,
        flatNo: this.state.flatNo,
        ownerName: this.state.ownerName,
        name: this.state.name,
        helpType: this.state.helpType,
        arrivalTime: this.state.arrivalTime,
        departureTime: this.state.departureTime,
      };
      console.log("domestichelp => " + JSON.stringify(domestichelp));
      DomesticHelpService.updateDomesticHelp(
        domestichelp,
        this.state.domestichelpId
      ).then((res) => {
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
    this.setState({ domesticHelpid: event.target.value });
  };
  changeIdHandler = (event) => {
    this.setState({ id: event.target.value });
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
              <h3 className="text-center">Update DomesticHelp</h3>
              <div className="card-body">
                <form>
                  <div className="form-group">
                    <label>Modified By</label>
                    <input
                      placeholder="modifiedBy"
                      name="modifiedBy"
                      className={
                        isError.modifiedBy.length > 0
                          ? "is-invalid form-control"
                          : "form-control"
                      }
                      value={this.state.modifiedBy}
                      noValidate
                      onChange={
                        (this.changeModifiedByHandler, this.formValChange)
                      }
                    />
                    {isError.modifiedBy.length > 0 && (
                      <span className="invalid-feedback">
                        {isError.modifiedBy}
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
                    <label>Arrival Time:</label>
                    <br></br>
                    <select
                      className="form-control"
                      name="arrivalTime"
                      id="arrivalTime"
                      value={this.state.arrivalTime}
                      noValidate
                      onChange={
                        (this.changeArrivalTimeHandler, this.formValChange)
                      }
                    >
                      <option value="0730">7.30 am</option>
                      <option value="0830">8.30 am</option>
                      <option value="0930">9.30 am</option>
                      <option value="1030">10.30 am</option>
                      <option value="1130">11.30 am</option>
                      <option value="1230">12.30 pm</option>
                      <option value="1330">1.30 pm</option>
                      <option value="1430">2.30 pm</option>
                      <option value="1530">3.30 pm</option>
                      <option value="1630">4.30 pm</option>
                      <option value="1730">5.30 pm</option>
                      <option value="18.30">6.30 pm</option>
                      <option value="19.30">7.30 pm</option>
                      <option value="20.30">8.30 pm</option>
                      <option value="21.30">9.30 pm</option>
                      <option value="22.30">10.30 pm</option>
                      <option value="23.30">11.30 pm</option>
                    </select>
                  </div>
                  <div className="form-group">
                    <label>Departure Time:</label>
                    <br></br>
                    <select
                      className="form-control"
                      name="departureTime:"
                      id="departureTime"
                      value={this.state.departureTime}
                      noValidate
                      onChange={
                        (this.changeDepartureTimeHandler, this.formValChange)
                      }
                    >
                      <option value="8.00">8.00 am</option>
                      <option value="9.00">9.00 am</option>
                      <option value="10.00">10.00 am</option>
                      <option value="11.00">11.00 am</option>
                      <option value="12.00">12.00 pm</option>
                      <option value="13.00">1.00 pm</option>
                      <option value="14.00">2.00 pm</option>
                      <option value="15.00">3.00 pm</option>
                      <option value="16.00">4.00 pm</option>
                      <option value="17.00">5.00 pm</option>
                      <option value="18.00">6.00 pm</option>
                      <option value="19.00">7.00 pm</option>
                      <option value="20.00">8.00 pm</option>
                      <option value="21.00">9.00 pm</option>
                      <option value="22.00">10.00 pm</option>
                      <option value="23.00">11.00 pm</option>
                      <option value="00.00">12.00 pm</option>
                    </select>
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
                    onClick={this.updateDomesticHelp}
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

export default UpdateDomesticHelpComponent;
