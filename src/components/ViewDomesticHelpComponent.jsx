import React, { Component } from 'react';
import DomesticHelpService from "../services/DomesticHelpService";

class ViewDomesticHelpComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            domestichelpId: this.props.match.params.domestichelpId,
            domestichelp: {}
        }
    }
    
    componentDidMount(){
        DomesticHelpService.getDomesticHelpByDomesticHelpId(this.state.domestichelpId).then(res=>{
            this.setState({domestichelp: res.data});
        })
    }
    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View DomesticHelp Details</h3>
                    <div className = "card-body">
                    <div className = "row">
                            <label> DomesticHelp Flat No: </label>
                            <div> {this.state.domestichelp.flatNo }</div>
                        </div>
                        <div className = "row">
                            <label> DomesticHelp Owner Name: </label>
                            <div> {this.state.domestichelp.ownerName }</div>
                        </div>
                        <div className = "row">
                            <label> DomesticHelp Name: </label>
                            <div> {this.state.domestichelp.name }</div>
                        </div>
                        <div className = "row">
                            <label> DomesticHelp Help Type: </label>
                            <div> {this.state.domestichelp.helpType }</div>
                        </div>
                        <div className = "row">
                            <label> DomesticHelp Arrival Time: </label>
                            <div> { this.state.domestichelp.arrivalTime }</div>
                        </div>
                        <div className = "row">
                            <label> DomesticHelp Departure Time: </label>
                            <div> { this.state.domestichelp.departureTime }</div>
                        </div>
                       
                    </div>

                </div>
            </div>
        )
    }
}

export default ViewDomesticHelpComponent;