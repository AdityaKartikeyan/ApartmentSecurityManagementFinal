import React, { Component } from "react";
import DeliveryService from "../services/DeliveryService";
import DomesticHelpService from "../services/DomesticHelpService";
import FlatService from "../services/FlatService";
import GuardSalaryService from "../services/GuardSalaryService";
import GuardShiftService from "../services/GuardShiftService";
import GuardTrainingService from "../services/GuardTrainingService";
import VehicleService from "../services/VehicleService";
import VisitorService from "../services/VisitorService";

class BaseEntity extends Component {
  constructor(props) {
    super(props);

    this.state = {
      userId: this.props.match.params.userId,
      visitorId: this.props.match.params.visitorId,
      vehicleNo: this.props.match.params.vehicleNo,
      flatNo: this.props.match.params.flatNo,
      domestichelpId: this.props.match.params.domestichelpId,
      deliveryId: this.props.match.params.deliveryId,
      base: {},
    };
  }

  componentDidMount() {
    GuardTrainingService.getGuardTrainingByUserId(this.state.userId).then(
      (res) => {
        this.setState({ base: res.data });
      }
    );

    VisitorService.getVisitorByUserId(this.state.visitorId).then((res) => {
      this.setState({ base: res.data });
    });

    VehicleService.getVehiclesByVehicleNo(this.state.vehicleNo).then((res) => {
      this.setState({ base: res.data });
    });

    FlatService.getFlatByflatNo(this.state.flatNo).then((res) => {
      this.setState({ base: res.data });
    });

    DomesticHelpService.getDomesticHelpByDomesticHelpId(
      this.state.domestichelpId
    ).then((res) => {
      this.setState({ base: res.data });
    });

    DeliveryService.getDeliveryByDeliveryId(this.state.deliveryId).then(
      (res) => {
        this.setState({ base: res.data });
      }
    );

    GuardSalaryService.getGuardSalayById(this.state.userId).then((res) => {
      this.setState({ base: res.data });
    });

    GuardShiftService.getGuardShiftByUserId(this.state.userId).then((res) => {
      this.setState({ base: res.data });
    });
  }
  render() {
    return (
      <div>
        <br></br>
        <div className="card col-md-6 offset-md-3">
          <h3 className="text-center"> View Auditing Details</h3>
          <div className="card-body">
            <div className="row">
              <label> Id: </label>
              <div> {this.state.base.id}</div>
            </div>
            <div className="row">
              <label> Created By: </label>
              <div> {this.state.base.createdBy}</div>
            </div>
            <div className="row">
              <label> Modified By: </label>
              <div> {this.state.base.modifiedBy}</div>
            </div>

            <div className="row">
              <label> Modified: </label>
              <div> {this.state.base.modifiedDateTime}</div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default BaseEntity;
