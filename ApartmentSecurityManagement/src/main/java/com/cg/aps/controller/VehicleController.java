package com.cg.aps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.aps.entities.VehicleEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.service.VehicleService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Anshul Joshi
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController  //it combines Controller and ResponseBody
@Controller
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	VehicleService service;
	
	@ApiOperation(value="Add Vehicle",
			response = VehicleEntity.class,
			tags = "add-vehicle",
			httpMethod = "POST")
	@PostMapping("/addVehicle")
	public ResponseEntity<VehicleEntity> addVehicle(@RequestBody VehicleEntity vehicle) throws DuplicateRecordException
	{
		
		 VehicleEntity addVehicle =  service.add(vehicle);
		 return new ResponseEntity<VehicleEntity>(addVehicle, HttpStatus.OK);
		
		
	
	}
	
	@ApiOperation(value = "Update Vehicle", response = VehicleEntity.class, tags = "update-Vehicle", httpMethod = "PUT")
	@PutMapping("/updateVehicle/{id}")
	public ResponseEntity<VehicleEntity> updateVehicleEntity(@PathVariable("id") String id, @RequestBody VehicleEntity Vehicle)
			throws RecordNotFoundException {
		Vehicle.setName(Vehicle.getName());
		Vehicle.setParkingNo(Vehicle.getParkingNo());
		Vehicle.setArrivalTime(Vehicle.getArrivalTime());
		Vehicle.setDepartureTime(Vehicle.getDepartureTime());
		Vehicle.setVehicleType(Vehicle.getVehicleType());

		
		VehicleEntity updateVehicle = service.update(Vehicle);
		return new ResponseEntity<VehicleEntity>(updateVehicle, HttpStatus.OK);
	}
	
	@ApiOperation(value="Delete Vehicle",
			response = String.class,
			tags = "delete-vehicle",
			httpMethod = "DELETE")
	@DeleteMapping("/deleteVehicle/{id}")
	public ResponseEntity<String> deleteVehicle(@PathVariable("id") long id) throws RecordNotFoundException
	{
		
		service.delete(id);
		return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
	
		
		
	}
	
	@ApiOperation(value="Get Vehicle",
			response = VehicleEntity.class,
			tags = "get-vehicle",
			httpMethod = "GET")
	@GetMapping("/getName/{name}")
	public ResponseEntity<VehicleEntity> getByName(@PathVariable("name") String name) throws RecordNotFoundException
	{
		
		VehicleEntity getVehicle =  service.findByName(name);
		 return new ResponseEntity<VehicleEntity>(getVehicle, HttpStatus.OK);
		
		
		
	}
	
	@ApiOperation(value="Get Vehicle Details by Id",
			response = VehicleEntity.class,
			tags = "get-vehicle-id",
			httpMethod = "GET")
	@GetMapping("/getById/{id}")
	public ResponseEntity<VehicleEntity> getByPk(@PathVariable("id") String id) throws RecordNotFoundException
	 {
		
		VehicleEntity getByid = service.findByPk(id);
		  return new ResponseEntity<VehicleEntity>(getByid, HttpStatus.OK);
		
		
	 }
	
	@ApiOperation(value="Get All Vehicles",response = VehicleEntity.class,
			tags = "get-all-vehicles",
			httpMethod = "GET"
			)
	@GetMapping("/getAll")
	public ResponseEntity<List<VehicleEntity>> searchVehicles() throws DatabaseException
	{
		
		List<VehicleEntity> getAllVehicles =  service.search();
		  return new ResponseEntity<List<VehicleEntity>>(getAllVehicles, HttpStatus.OK);
		
		
	}
}