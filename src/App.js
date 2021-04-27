import "./App.css";
import React from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import FooterComponent from "./components/FooterComponent";
import HeaderComponent from "./components/HeaderComponent";
import ListDeliveryComponent from "./components/ListDeliveryComponent";
import CreateDeliveryComponent from "./components/CreateDeliveryComponent";
import UpdateDeliveryComponent from "./components/UpdateDeliveryComponent";
import ViewDeliveryComponent from "./components/ViewDeliveryComponent";
import ListAllVisitorsComponent from "./components/ListAllVisitorsComponent";
import CreateVisitorComponent from "./components/CreateVisitorComponent";
import UpdateVisitorComponent from "./components/UpdateVisitorComponent";
import ViewVisitorComponent from "./components/ViewVisitorComponent";
import ListGuardShiftComponent from "./components/ListGuardShiftComponent";
import CreateGuardShiftComponent from "./components/CreateGuardShiftComponent";
import UpdateGuardShiftComponent from "./components/UpdateGuardShiftComponent";
import ViewGuardShiftComponent from "./components/ViewGuardShiftComponent";
import ListVehicleComponent from "./components/ListVehicleComponent";
import CreateVehicleComponent from "./components/CreateVehicleComponent";
import UpdateVehicleComponent from "./components/UpdateVehicleComponent";
import ViewVehicleComponent from "./components/ViewVehicleComponent";
import ListGuardSalaryComponent from "./components/ListGuardSalaryComponent";
import CreateGuardSalaryComponent from "./components/CreateGuardSalaryComponent";
import UpdateGuardSalaryComponent from "./components/UpdateGuardSalaryComponent";
import ViewGuardSalaryComponent from "./components/ViewGuardSalaryComponent";
import ListFlatComponent from "./components/ListFlatComponent";
import CreateFlatComponent from "./components/CreateFlatComponent";
import UpdateFlatComponent from "./components/UpdateFlatComponent";
import ViewFlatComponent from "./components/ViewFlatComponent";
import ListDomesticHelpComponent from "./components/ListDomesticHelpComponent";
import CreateDomesticHelpComponent from "./components/CreateDomesticHelpComponent";
import UpdateDomesticHelpComponent from "./components/UpdateDomesticHelpComponent";
import ViewDomesticHelpComponent from "./components/ViewDomesticHelpComponent";
import ListGuardTrainingComponent from "./components/ListGuardTrainingComponent";
import CreateGuardTrainingComponent from "./components/CreateGuardTrainingComponent";
import UpdateGuardTrainingComponent from "./components/UpdateGuardTrainingComponent";
import ViewGuardComponent from "./components/ViewGuardComponent";

import BaseEntity from "./components/BaseEntity";

function App() {
  return (
    <div className="App">
      <Router>
        <HeaderComponent />
        <div className="container">
          <Switch>
            <Route path="/" exact component={ListDeliveryComponent}></Route>
            <Route path="/getAll" component={ListDeliveryComponent}></Route>
            <Route
              path="/GuardShift"
              component={ListGuardShiftComponent}
            ></Route>
            <Route
              path="/add-GuardShift"
              component={CreateGuardShiftComponent}
            ></Route>
            <Route
              path="/update-GuardShift/:userId"
              component={UpdateGuardShiftComponent}
            ></Route>
            <Route
              path="/getByGuardShiftId/:userId"
              component={ViewGuardShiftComponent}
            ></Route>
            <Route path="/vehicle" component={ListVehicleComponent}></Route>
            <Route
              path="/add-Vehicle"
              component={CreateVehicleComponent}
            ></Route>
            <Route
              path="/update-Vehicle/:vehicleNo"
              component={UpdateVehicleComponent}
            ></Route>
            <Route
              path="/getByVehicleId/:vehicleNo"
              component={ViewVehicleComponent}
            ></Route>
            <Route path="/getByPk3/:vehicleNo" component={BaseEntity}></Route>
            <Route
              path="/GuardSalary"
              component={ListGuardSalaryComponent}
            ></Route>
            <Route
              path="/add-GuardSalary"
              component={CreateGuardSalaryComponent}
            ></Route>
            <Route
              path="/update-GuardSalary/:userId"
              component={UpdateGuardSalaryComponent}
            ></Route>
            <Route
              path="/getByGuardSalaryId/:userId"
              component={ViewGuardSalaryComponent}
            ></Route>
            <Route path="/getByPk1/:userId" component={BaseEntity}></Route>

            <Route path="/flat" component={ListFlatComponent}></Route>
            <Route path="/addFlat" component={CreateFlatComponent}></Route>
            <Route
              path="/updateFlat/:flatNo"
              component={UpdateFlatComponent}
            ></Route>
            <Route
              path="/getByFlatId/:flatNo"
              component={ViewFlatComponent}
            ></Route>
            <Route
              path="/domesticHelp"
              component={ListDomesticHelpComponent}
            ></Route>
            <Route
              path="/addDomesticHelp"
              component={CreateDomesticHelpComponent}
            ></Route>
            <Route
              path="/updateDomesticHelp/:domestichelpId"
              component={UpdateDomesticHelpComponent}
            ></Route>
            <Route
              path="/getByDomesticHelpId/:domestichelpId"
              component={ViewDomesticHelpComponent}
            ></Route>
            <Route
              path="/getByPk5/:domestichelpId"
              component={BaseEntity}
            ></Route>
            <Route
              path="/guardTraining"
              component={ListGuardTrainingComponent}
            ></Route>
            <Route
              path="/add-guardTraining"
              component={CreateGuardTrainingComponent}
            ></Route>
            <Route
              path="/update-guardTraining/:userId"
              component={UpdateGuardTrainingComponent}
            ></Route>
            <Route
              path="/getByGuardId/:userId"
              component={ViewGuardComponent}
            ></Route>
            <Route path="/getByPk7/:userId" component={BaseEntity}></Route>

            <Route path="/getByPk4/:flatNo" component={BaseEntity}></Route>

            <Route path="/getByPk8/:userId" component={BaseEntity}></Route>

            <Route
              path="/visitors"
              component={ListAllVisitorsComponent}
            ></Route>
            <Route
              path="/addVisitor"
              component={CreateVisitorComponent}
            ></Route>
            <Route
              path="/updateVisitor/:visitorId"
              component={UpdateVisitorComponent}
            ></Route>
            <Route
              path="/getByVisitorId/:visitorId"
              component={ViewVisitorComponent}
            ></Route>
            <Route path="/getByPk2/:visitorId" component={BaseEntity}></Route>
            <Route
              path="/addDelivery"
              component={CreateDeliveryComponent}
            ></Route>
            <Route
              path="/updateDelivery/:deliveryId"
              component={UpdateDeliveryComponent}
            ></Route>
            <Route
              path="/getByDeliveryId/:deliveryId"
              component={ViewDeliveryComponent}
            ></Route>
            <Route path="/getByPk6/:deliveryId" component={BaseEntity}></Route>
          </Switch>
        </div>
        <FooterComponent />
      </Router>
    </div>
  );
}

export default App;
