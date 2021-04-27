
/**
 * @author AMBARISH DATAR
 *
 */
package com.cg.aps.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.cg.aps.entities.VisitorEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.service.VisitorService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Controller
public class VisitorController {
	
	@Autowired
	VisitorService service;
	
	//Add Visitor
	@ApiOperation(value="Add Visitor", response = VisitorEntity.class, tags = "add-Visitor",	httpMethod = "POST")
	@PostMapping("/addVisitor")
	public ResponseEntity<VisitorEntity> addVisitorSalary(@RequestBody VisitorEntity visitor) throws DuplicateRecordException
	{
			VisitorEntity addVisitor =  service.add(visitor);
			return new ResponseEntity<VisitorEntity>(addVisitor, HttpStatus.OK);
	}
	
	//Update Visitor
	@ApiOperation(value = "Update Visitor", response = VisitorEntity.class, tags = "update-Visitor", httpMethod = "PUT")
	@PutMapping("/updateVisitor/{id}")
	public ResponseEntity<VisitorEntity> updateVisitorEntity(@PathVariable("id") String id, @RequestBody VisitorEntity Visitor)
			throws RecordNotFoundException {
		
		Visitor.setName(Visitor.getName());
		Visitor.setOwnerName(Visitor.getOwnerName());
		Visitor.setFlatNo(Visitor.getFlatNo());
		Visitor.setDate(Visitor.getDate());
		Visitor.setArrivalTime(Visitor.getArrivalTime());
		Visitor.setDepartureTime(Visitor.getDepartureTime());
		
		VisitorEntity updateVisitor = service.update(Visitor);
		return new ResponseEntity<VisitorEntity>(updateVisitor, HttpStatus.OK);
	}
	
	//Delete Visitor
	@ApiOperation(value="Delete Visitor",response = String.class,tags = "delete-Visitor",httpMethod = "DELETE")
	@DeleteMapping("/deleteVisitor/{id}")
	public ResponseEntity<String> deleteVisitorTraining(@PathVariable("id") long id) throws RecordNotFoundException
	{
		service.delete(id);
		return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
	}
	
	//Search Visitor by name
	@ApiOperation(value="Get Visitor Name",response = VisitorEntity.class,tags = "get-Visitor-name",httpMethod = "GET")
	@GetMapping("/getName/{name}")
	public ResponseEntity<VisitorEntity> getByName(@PathVariable("name") String name) throws RecordNotFoundException
	{
		VisitorEntity getVisitorName =  service.findByName(name);
		return new ResponseEntity<VisitorEntity>(getVisitorName, HttpStatus.OK);
	}
	
	//Search Visitor by visitor id
	@ApiOperation(value="Get Visitor Details by Id",response = VisitorEntity.class,tags = "get-Visitor-id",httpMethod = "GET")
	@GetMapping("/getById/{id}")
	public ResponseEntity<VisitorEntity> getByPk(@PathVariable("id") String id) throws RecordNotFoundException
	 {
		VisitorEntity getByid = service.findByPk(id);
		return new ResponseEntity<VisitorEntity>(getByid, HttpStatus.OK);
	 }
	
	//get all Visitors
	@ApiOperation(value="Get All Visitors",response = VisitorEntity.class,tags = "get-all-Visitors",httpMethod = "GET")
	@GetMapping("/getAll")
	public ResponseEntity<List<VisitorEntity>> searchVisitors() throws DatabaseException
	{
		List<VisitorEntity> getAllVisitors =  service.search();
		return new ResponseEntity<List<VisitorEntity>>(getAllVisitors, HttpStatus.OK);
	}
}