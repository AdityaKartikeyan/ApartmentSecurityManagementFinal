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

import com.cg.aps.entities.GuardTrainingEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.service.GuardTrainingService;

import io.swagger.annotations.ApiOperation;
/**
 * @author Aditya
 *
 */
@CrossOrigin(origins ="http://localhost:3000")
@RestController
@Controller
@RequestMapping("/guard")

public class GuardTrainingController {

	@Autowired
	GuardTrainingService service;
	
	@ApiOperation(value="Add Guard Training",
			response = GuardTrainingEntity.class,
			tags = "add-guard",
			httpMethod = "POST")
	@PostMapping("/addGuard")         //Adding a guard
	public ResponseEntity<GuardTrainingEntity> addGuardTraining(@RequestBody GuardTrainingEntity guard) throws DuplicateRecordException
	{
		
		 GuardTrainingEntity addGuard =  service.add(guard);
		 return new ResponseEntity<GuardTrainingEntity>(addGuard, HttpStatus.OK);
		
		
	
	}
	
	@ApiOperation(value = "Update GuardTraining", response = GuardTrainingEntity.class, tags = "update-GuardTraining", httpMethod = "PUT")
	@PutMapping("/updateGuard/{id}")
	public ResponseEntity<GuardTrainingEntity> updateGuardTrainingEntity(@PathVariable("id") long id, @RequestBody GuardTrainingEntity GuardTraining)
			throws RecordNotFoundException {
		GuardTraining.setName(GuardTraining.getName());
		GuardTraining.setMobileNo(GuardTraining.getMobileNo());
		GuardTraining.setStatus(GuardTraining.getStatus());
		GuardTraining.setTiming(GuardTraining.getTiming());
		
		GuardTrainingEntity updateGuardTraining = service.update(GuardTraining);
		return new ResponseEntity<GuardTrainingEntity>(updateGuardTraining, HttpStatus.OK);
	}
	
	@ApiOperation(value="Delete Guard Training",
			response = String.class,
			tags = "delete-guard",
			httpMethod = "DELETE")
	@DeleteMapping("/deleteGuard/{id}")          //Deleting a guard
	public ResponseEntity<String> deleteGuardTraining(@PathVariable("id") long id) throws RecordNotFoundException
	{
		
		service.delete(id);
		return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
	
		
		
	}
	
	@ApiOperation(value="Get Guard Name",
			response = GuardTrainingEntity.class,
			tags = "get-guard-name",
			httpMethod = "GET")
	@GetMapping("/getName/{name}")              //Get guard by name
	public ResponseEntity<GuardTrainingEntity> getByName(@PathVariable("name") String name) throws RecordNotFoundException
	{
		
		GuardTrainingEntity getGuardName =  service.findByName(name);
		 return new ResponseEntity<GuardTrainingEntity>(getGuardName, HttpStatus.OK);
		
		
		
	}
	
	@ApiOperation(value="Get Guard Details by Id",
			response = GuardTrainingEntity.class,
			tags = "get-guard-id",
			httpMethod = "GET")
	@GetMapping("/getById/{id}")               //Get guard by id
	public ResponseEntity<GuardTrainingEntity> getByPk(@PathVariable("id") long id) throws RecordNotFoundException
	 {
		
		GuardTrainingEntity getByid = service.findByPk(id);
		  return new ResponseEntity<GuardTrainingEntity>(getByid, HttpStatus.OK);
		
		
	 }
	
	@ApiOperation(value="Get All Guards",response = GuardTrainingEntity.class,
			tags = "get-all-guards",
			httpMethod = "GET"
			)
	@GetMapping("/getAll")                   //Get all guards
	public ResponseEntity<List<GuardTrainingEntity>> searchGuards() throws DatabaseException
	{
		
		List<GuardTrainingEntity> getAllGuards =  service.search();
		  return new ResponseEntity<List<GuardTrainingEntity>>(getAllGuards, HttpStatus.OK);
		
		
	}
	
}
