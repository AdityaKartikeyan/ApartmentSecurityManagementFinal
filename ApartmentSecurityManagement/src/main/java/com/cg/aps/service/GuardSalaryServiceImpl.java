package com.cg.aps.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.aps.entities.GuardSalaryEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.GuardSalaryDao;

/**
 * @author Aravind
 *
 */
@Service("GuardSalaryService")
@Transactional
public class GuardSalaryServiceImpl implements GuardSalaryService {

	@Autowired
	GuardSalaryDao dao;

	@Override
	public GuardSalaryEntity add(GuardSalaryEntity bean) throws DuplicateRecordException {                       //adding guard values

		Optional<GuardSalaryEntity> getId = dao.findByUserId(bean.getUserId());
		if (getId.isPresent()) {
			throw new DuplicateRecordException("The Id is already added");
		}

		else {
			return dao.save(bean);
		}

	}

	@Override
	public GuardSalaryEntity update(GuardSalaryEntity bean) throws RecordNotFoundException {                  //updating existing guard values

		if (bean.getName().isEmpty()) {

			throw new RecordNotFoundException("Name not found");
		} else {

			return dao.save(bean);
		}

	}

	@Override
	public GuardSalaryEntity delete(long id) throws RecordNotFoundException {                                //deleting guard values
 
		Optional<GuardSalaryEntity> guard = dao.findById(id);
		if (!guard.isPresent()) {
			throw new RecordNotFoundException("Id Not Found");
		} else {
			return dao.deleteById(id);
		}

	}

	@Override
	public GuardSalaryEntity findByName(String name) throws RecordNotFoundException {                   //finding guard details using guard name

		Optional<GuardSalaryEntity> guard = dao.findByName(name);
		if (!guard.isPresent()) {
			throw new RecordNotFoundException("Name not found");
		} else {
			return guard.get();
		}

	}

	@Override
	public GuardSalaryEntity findByPk(long id) throws RecordNotFoundException {                     //finding guard details using guard userid
		Optional<GuardSalaryEntity> optional = dao.findByUserId(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new RecordNotFoundException("Invalid id");
		}

	}

	@Override
	public List<GuardSalaryEntity> search() throws DatabaseException {                              //finding all existing values

		if (dao.findAll().isEmpty()) {
			throw new DatabaseException("No Records available in Database");
		} else {
			return dao.findAll();
		}

	}
}
