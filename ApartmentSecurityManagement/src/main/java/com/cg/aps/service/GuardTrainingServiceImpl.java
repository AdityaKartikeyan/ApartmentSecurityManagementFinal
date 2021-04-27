package com.cg.aps.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.aps.entities.GuardTrainingEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.GuardTrainingDao;
/**
 * @author Aditya
 *
 */
@Service("GuardTrainingService")
@Transactional
public class GuardTrainingServiceImpl implements GuardTrainingService {         

	@Autowired
	GuardTrainingDao dao;
	@Override
	public GuardTrainingEntity add(GuardTrainingEntity bean) throws DuplicateRecordException {         //adding guard training 

		

			Optional<GuardTrainingEntity> getId = dao.findByUserId(bean.getUserId());        //if id already present then id already added
			if (getId.isPresent()) {
				throw new DuplicateRecordException("The Id is already added");
			}

			else {
				return dao.save(bean);
			}
                                                                                                   
		
	}

	@Override
	public GuardTrainingEntity update(GuardTrainingEntity bean) throws RecordNotFoundException {      //updating the guard

		
			if (bean.getName().isEmpty()) {

				throw new RecordNotFoundException("Name not found");                  //name empty then name not found
			} else {

				return dao.save(bean);
			}

		

	}

	@Override
	public GuardTrainingEntity delete(long id) throws RecordNotFoundException {                   //deleting the guard

		

			Optional<GuardTrainingEntity> guard = dao.findById(id);
			if (!guard.isPresent()) {
				throw new RecordNotFoundException("Id Not Found");
			} else {
				return dao.deleteById(id);
			}


	}

	@Override
	public GuardTrainingEntity findByName(String name) throws RecordNotFoundException {          //finding by name 

		
			Optional<GuardTrainingEntity> guard = dao.findByName(name);                           //name not there then name not found
			if (!guard.isPresent()) {
				throw new RecordNotFoundException("Name not found");
			} else {
				return guard.get();
			}

		
	}

	@Override
	public GuardTrainingEntity findByPk(long id) throws RecordNotFoundException {           //find by id 

		
			Optional<GuardTrainingEntity> optional = dao.findByUserId(id);                  //id not there , then id not found
			if (optional.isPresent()) {
				return optional.get();
			} else {
				throw new RecordNotFoundException("Invalid id");
			}

		
	}

	
	@Override
	public List<GuardTrainingEntity> search() throws DatabaseException {                 //searching all records

		

			if (dao.findAll().isEmpty()) {                                                  //if empty , then no record available
				throw new DatabaseException("No Records available in Database");              
			} else {
				return dao.findAll();
			}

		
	}

}
