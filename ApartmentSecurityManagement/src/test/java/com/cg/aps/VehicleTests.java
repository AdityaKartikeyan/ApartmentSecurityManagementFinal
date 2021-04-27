package com.cg.aps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cg.aps.entities.VehicleEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.VehicleDao;
import com.cg.aps.service.VehicleService;

@SpringBootTest 
class VehicleTests {

	/**
	 * @author Anshul Joshi
	 */
	@MockBean   //to add mocks to a Spring ApplicationContext
	VehicleDao dao;
	@Autowired //it injects dependencies to spring 
	VehicleService service;

	// adding vehicle
	@Test //
	 void testAddVehicle() throws DuplicateRecordException {

		VehicleEntity obj = new VehicleEntity("Anshul", "Joshi", "AJ", "101", Date.valueOf(LocalDate.now()), "103",
				"Honda City");

		Mockito.when(dao.save(obj)).thenReturn(obj);
		assertEquals(obj, service.add(obj));

	}
	
	//testing with duplicate record
	@Test
	 void testAddVehicleWrong() throws DuplicateRecordException {
		String vehicleNo = "7476";
		VehicleEntity obj = new VehicleEntity("Anshul", "Joshi", "AJ", "101", Date.valueOf(LocalDate.now()), "103",
				"Honda City");

		Mockito.when(dao.save(obj)).thenReturn(obj);
		try {
			assertEquals(vehicleNo, service.add(obj).getVehicleNo());
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	//testing with exisisting record
	@Test
	 void testAddExistingVehicle() throws DuplicateRecordException {
		String vehicleNo = "103";
		VehicleEntity obj = new VehicleEntity("Anshul", "Joshi", "AJ", "101", Date.valueOf(LocalDate.now()), "103",
				"Honda City");
		/*
		 * VehicleEntity obj1 = new VehicleEntity("Anshul", "Joshi", "AJ", "101",
		 * Date.valueOf(LocalDate.now()), "103", "Honda City");
		 */
		Mockito.when(dao.save(obj)).thenReturn(obj);
		try {
			assertEquals(vehicleNo, service.add(obj).getVehicleNo());
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	// testing update vehicle entity
	@Test
	 void testUpdateVehicle() throws RecordNotFoundException {

		VehicleEntity obj = new VehicleEntity("Anshul", "Joshi", "AJ", "101", Date.valueOf(LocalDate.now()), "103",
				"Honda Jazz");

		Mockito.when(dao.save(obj)).thenReturn(obj);

		try {
			assertEquals(obj, service.update(obj));
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}
	}

	// testing duplicate update vehicle entity
	@Test
	 void testUpdateVehicleWrong() throws RecordNotFoundException {
		String vehicleName = "Fit";
		VehicleEntity obj = new VehicleEntity("Anshul", "Joshi", "AJ", "101", Date.valueOf(LocalDate.now()), "103",
				"Honda Jazz");

		Mockito.when(dao.save(obj)).thenReturn(obj);

		try {
			assertEquals(vehicleName, service.update(obj));
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}
	}

	// testing delete vehicle entity

	@Test
	 void testDeleteVehicle() throws RecordNotFoundException {
			/*
			 * VehicleEntity obj = new VehicleEntity("Anshul", "Joshi", "AJ", "101",
			 * Date.valueOf(LocalDate.now()), "103", "Honda City");
			 */
		Optional<VehicleEntity> obj1 = Optional.of(new VehicleEntity(1, "Anshul", "Joshi", null, null, "AJ", "101",
				null, null, Date.valueOf(LocalDate.now()), "103", "Honda City"));
		Mockito.when(dao.findById(obj1.get().getId())).thenReturn(obj1);
		Mockito.when(dao.deleteById(obj1.get().getId())).thenReturn(obj1.get());
		try {
			VehicleEntity src = service.delete(obj1.get().getId());

			assertEquals(1, src.getId());
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}
	}

	// testing dupclicate delete vehicle entity

	@Test
	 void testDeleteVehicleWrong() throws RecordNotFoundException {
			/*
			 * VehicleEntity obj = new VehicleEntity("Anshul", "Joshi", "AJ", "101",
			 * Date.valueOf(LocalDate.now()), "103", "Honda City");
			 */
		Optional<VehicleEntity> obj1 = Optional.of(new VehicleEntity(1, "Anshul", "Joshi", null, null, "AJ", "101",
				null, null, Date.valueOf(LocalDate.now()), "103", "Honda City"));
		Mockito.when(dao.findById(obj1.get().getId())).thenReturn(obj1);
		Mockito.when(dao.deleteById(obj1.get().getId())).thenReturn(obj1.get());
		try {
			VehicleEntity src = service.delete(obj1.get().getId());

			assertEquals(0, src.getId());
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}
	}

	// testing find by name

	@Test
	 void testFindByName() throws RecordNotFoundException {

		Optional<VehicleEntity> obj = Optional.of(
				new VehicleEntity("Anshul", "Joshi", "AJ", "101", Date.valueOf(LocalDate.now()), "103", "Honda Fit"));

		Mockito.when(dao.findByName(obj.get().getName())).thenReturn(obj);
		Optional<VehicleEntity> obj1 = Optional.of(service.findByName(obj.get().getName()));
		assertEquals(obj.get().getName(), obj1.get().getName());

	}

	// testing duplicate find by name

	@Test
	void testFindByNameWrong() throws RecordNotFoundException {

		String name = "Ramu Bhaiya";
		Optional<VehicleEntity> obj = Optional.of(
				new VehicleEntity("Anshul", "Joshi", "AJ", "101", Date.valueOf(LocalDate.now()), "103", "Honda Fit"));

		try {
			Mockito.when(dao.findByName(obj.get().getName())).thenReturn(obj);
			Optional<VehicleEntity> obj1 = Optional.of(service.findByName(obj.get().getName()));
			assertEquals(name, obj1.get().getName());
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}
	}

	// testing for find by ID
	@Test
	 void testFindByPk() throws RecordNotFoundException {
		Optional<VehicleEntity> obj = Optional.of(
				new VehicleEntity("Anshul", "Joshi", "AJ", "101", Date.valueOf(LocalDate.now()), "103", "Honda Fit"));

		Mockito.when(dao.findByVehicleNo(obj.get().getVehicleNo())).thenReturn(obj);
		Optional<VehicleEntity> obj1 = Optional.of(service.findByPk(obj.get().getVehicleNo()));
		assertEquals(obj.get().getVehicleNo(), obj1.get().getVehicleNo());

	}

	// testing for duplicate ID

	@Test
	void testFindByPkWrong() throws RecordNotFoundException {
		String vehicleNo = "1245";
		Optional<VehicleEntity> obj = Optional.of(
				new VehicleEntity("Anshul", "Joshi", "AJ", "101", Date.valueOf(LocalDate.now()), "103", "Honda Fit"));
		try {
			Mockito.when(dao.findByVehicleNo(obj.get().getVehicleNo())).thenReturn(obj);
			Optional<VehicleEntity> obj1 = Optional.of(service.findByPk(obj.get().getVehicleNo()));
			assertEquals(vehicleNo, obj1.get().getVehicleNo());
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}
	}

	// SEARCHING

	@Test
	 void testSearch() throws DatabaseException {
		VehicleEntity obj = new VehicleEntity("Anshul", "Joshi", "AJ", "104", Date.valueOf(LocalDate.now()), "108",
				"Honda Fit");
		VehicleEntity obj1 = new VehicleEntity("Aditya", "Aravind", "AJ", "109", Date.valueOf(LocalDate.now()), "110",
				"Honda Jazz");
		List<VehicleEntity> list1 = new ArrayList<VehicleEntity>();
		list1.add(obj);
		list1.add(obj1);

		Mockito.when(dao.findAll()).thenReturn(list1);
		assertEquals(2, service.search().size());
	}

	// Duplicate SEARCHING

	@Test
	 void testSearchWrong() throws DatabaseException {
		VehicleEntity obj = new VehicleEntity("Anshul", "Joshi", "AJ", "104", Date.valueOf(LocalDate.now()), "108",
				"Honda Fit");
		VehicleEntity obj1 = new VehicleEntity("Aditya", "Aravind", "AJ", "109", Date.valueOf(LocalDate.now()), "110",
				"Honda Jazz");
		List<VehicleEntity> list1 = new ArrayList<VehicleEntity>();
		list1.add(obj);
		list1.add(obj1);

		Mockito.when(dao.findAll()).thenReturn(list1);
		assertEquals(3, service.search().size());
	}

}
