 package com.cg.aps.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.aps.entities.GuardShiftEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.GuardShiftDao;

/**
 * @author HP
 *
 */
@Service("GuardShiftService") //it performs specific service
@Transactional  //defines single database transaction

public class GuardShiftServiceImpl implements GuardShiftService
{
	@Autowired
	GuardShiftDao dao;

	@Override
	//adding data
	public GuardShiftEntity add(GuardShiftEntity bean) throws DuplicateRecordException {

		

			
			  Optional<GuardShiftEntity> getId = dao.findById(bean.getUserId());
			  if(getId.isPresent())
			  {
				  throw new DuplicateRecordException("The Id is already added");
			  }
			  
			else {
				return dao.save(bean);
			}

	}

	@Override
	//updating data
	public GuardShiftEntity update(GuardShiftEntity bean) throws RecordNotFoundException {

				
			  if(bean.getName().isEmpty())
		        {
		           
		            throw new RecordNotFoundException("Name not found");
		        }
			  else {
				  
				  return dao.save(bean);
			  }
			
	}

	@Override
	//deleting data
	public GuardShiftEntity delete(long id) throws RecordNotFoundException {

		 Optional<GuardShiftEntity> guard =dao.findById(id);
		if (!guard.isPresent()) {
			throw new RecordNotFoundException("Id Not Found");
		} else {
		     return dao.deleteById(id);
		}

	}  

	@Override
	//finding record by name
	public GuardShiftEntity findByName(String name) throws RecordNotFoundException {

		
			   Optional<GuardShiftEntity> guard =dao.findByName(name);
			   if(!guard.isPresent())
			   {
				   throw new RecordNotFoundException("Name not found");
			   }
			   else
			   {
				   return guard.get();
			   }
			
			}
	

	@Override
	//finding record by id
	public GuardShiftEntity findByPk(long id) throws RecordNotFoundException {

				
			Optional<GuardShiftEntity> optional = dao.findByUserId(id);
			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new RecordNotFoundException("Invalid id");
			}
		
		}
	
	

	

	@Override
	//searching data
	public List<GuardShiftEntity> search() throws DatabaseException {

		
			
			if(dao.findAll().isEmpty())
			{
				throw new DatabaseException("No Records available in Database");
			}
			else {
				return dao.findAll();
			}
		
		}
	}


