package com.cg.aps.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.aps.entities.VehicleEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.VehicleDao;

/**
 * @author Anshul Joshi
 *
 */
@Service("VehicleService")  //it performs specific service
@Transactional  //defines single database transaction 
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	VehicleDao dao;

	//adding data
	@Override
	public VehicleEntity add(VehicleEntity bean) throws DuplicateRecordException {

		Optional<VehicleEntity> getId = dao.findById(bean.getId());
		if (getId.isPresent()) {
			throw new DuplicateRecordException("The Id is already added");
		}

		else {
			return dao.save(bean);
		}

	}

	//deleting data
	@Override
	public VehicleEntity update(VehicleEntity bean) throws RecordNotFoundException {

		if (bean.getName().isEmpty()) {

			throw new RecordNotFoundException("Name not found");
		} else {

			return dao.save(bean);
		}

	}
	
	//deleting data

	@Override
	public VehicleEntity delete(long id) throws RecordNotFoundException {

		Optional<VehicleEntity> guard = dao.findById(id);
		if (!guard.isPresent()) {
			throw new RecordNotFoundException("Id Not Found");
		} else {
			return dao.deleteById(id);
		}

	}

	//finding record by name
	@Override
	public VehicleEntity findByName(String name) throws RecordNotFoundException {

		Optional<VehicleEntity> vehicle = dao.findByName(name);
		if (!vehicle.isPresent()) {
			throw new RecordNotFoundException("Name not found");
		} else {
			return vehicle.get();
		}

	}
	
	//finding record by ID

	@Override
	public VehicleEntity findByPk(String id) throws RecordNotFoundException {

		Optional<VehicleEntity> optional = dao.findByVehicleNo(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new RecordNotFoundException("Invalid id");
		}

	}

	@Override
	public List<VehicleEntity> search(VehicleEntity bean, long pageNo, int pageSize) {

		return null;
	}
	
	//searching data

	@Override
	public List<VehicleEntity> search() throws DatabaseException {

		if (dao.findAll().isEmpty()) {
			throw new DatabaseException("No Records available in Database");
		} else {
			return dao.findAll();
		}

	}

}
