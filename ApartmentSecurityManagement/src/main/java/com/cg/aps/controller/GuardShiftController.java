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

import com.cg.aps.entities.GuardShiftEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.service.GuardShiftService;

import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "http://localhost:3000")
@RestController //combines controller and request body
@Controller     //
@RequestMapping("/guardShift")
public class GuardShiftController {

	@Autowired
	GuardShiftService service;
	
	@ApiOperation(value="Add Guard Training",
			response = GuardShiftEntity.class,
			tags = "add-guard",
			httpMethod = "POST")
	@PostMapping("/addGuard")
	public ResponseEntity<GuardShiftEntity> addGuardTraining(@RequestBody GuardShiftEntity guard) throws DuplicateRecordException
	{
		
		 GuardShiftEntity addGuard =  service.add(guard);
		 return new ResponseEntity<GuardShiftEntity>(addGuard, HttpStatus.OK);
		
		
	
	}
	
	@ApiOperation(value = "Update GuardShift", response = GuardShiftEntity.class, tags = "update-GuardShift", httpMethod = "PUT")
	@PutMapping("/updateGuard/{id}")
	public ResponseEntity<GuardShiftEntity> updateGuardShiftEntity(@PathVariable("id") long id, @RequestBody GuardShiftEntity GuardShift)
			throws RecordNotFoundException {
		GuardShift.setName(GuardShift.getName());
		GuardShift.setTime(GuardShift.getTime());
		GuardShift.setDate(GuardShift.getDate());
		
		
		GuardShiftEntity updateGuardShift = service.update(GuardShift);
		return new ResponseEntity<GuardShiftEntity>(updateGuardShift, HttpStatus.OK);
	

	}
	
	@ApiOperation(value="Delete Guard Training",
			response = String.class,
			tags = "delete-guard",
			httpMethod = "DELETE")
	@DeleteMapping("/deleteGuard/{id}")
	public ResponseEntity<String> deleteGuardTraining(@PathVariable("id") long id) throws RecordNotFoundException
	{
		
		service.delete(id);
		return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
	
		
		
	}
	
	@ApiOperation(value="Get Guard Name",
			response = GuardShiftEntity.class,
			tags = "get-guard-name",
			httpMethod = "GET")
	@GetMapping("/getName/{name}")
	public ResponseEntity<GuardShiftEntity> getByName(@PathVariable("name") String name) throws RecordNotFoundException
	{
		
		GuardShiftEntity getGuardName =  service.findByName(name);
		 return new ResponseEntity<GuardShiftEntity>(getGuardName, HttpStatus.OK);
		
		
		
	}
	
	@ApiOperation(value="Get Guard Details by Id",
			response = GuardShiftEntity.class,
			tags = "get-guard-id",
			httpMethod = "GET")
	@GetMapping("/getById/{id}")
	public ResponseEntity<GuardShiftEntity> getByPk(@PathVariable("id") long id) throws RecordNotFoundException
	 {
		
		GuardShiftEntity getByid = service.findByPk(id);
		  return new ResponseEntity<GuardShiftEntity>(getByid, HttpStatus.OK);
		
		
	 }
	
	@ApiOperation(value="Get All Guards",response = GuardShiftEntity.class,
			tags = "get-all-guards",
			httpMethod = "GET"
			)
	@GetMapping("/getAll")
	public ResponseEntity<List<GuardShiftEntity>> searchGuards() throws DatabaseException
	{
		
		List<GuardShiftEntity> getAllGuards =  service.search();
		  return new ResponseEntity<List<GuardShiftEntity>>(getAllGuards, HttpStatus.OK);
		
		
	}
	}