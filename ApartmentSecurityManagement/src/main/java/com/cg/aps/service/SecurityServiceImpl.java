/**
 * 
 */
package com.cg.aps.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.cg.aps.entities.SecurityEntity;
import com.cg.aps.exception.DatabaseException;
import com.cg.aps.exception.DuplicateRecordException;
import com.cg.aps.exception.RecordNotFoundException;
import com.cg.aps.repository.SecurityDao;

/**
 * @author Aditya
 *
 */
@Service("SecurityService")
@Transactional
public class SecurityServiceImpl implements SecurityService {       

	@Autowired
	SecurityDao dao;
	@Override
	public SecurityEntity add(SecurityEntity bean) throws DuplicateRecordException {    //adding security 
		
	
			
			  Optional<SecurityEntity> getId = dao.findByAlertId(bean.getAlertId());      //if id already present then id already added
			  if(getId.isPresent())                                                                  
			  {
				  throw new DuplicateRecordException("The Id is already added");           
			  }
			  
			else {
				return dao.save(bean);
			}
		
	}

	@Override
	public SecurityEntity update(SecurityEntity bean) throws RecordNotFoundException {      //updating the security
		
					
			  if(bean.getAlertMessage().isEmpty())                                        //alert message empty then message not found
		        {
		           
		            throw new RecordNotFoundException("Message not found");
		        }
			  else {
				  
				  return dao.save(bean);
			  }
		
	}

	@Override
	public SecurityEntity delete(long id) throws RecordNotFoundException {              //deleting the security entity
		
		
			
			Optional<SecurityEntity> guard =dao.findById(id);                          //if id not there ,then id not found
			if (!guard.isPresent()) {
				throw new RecordNotFoundException("Id Not Found");
			} else {
			     return dao.deleteById(id);
			}

		
		 
		 
	}

	@Override
	public SecurityEntity findByPk(String id) throws RecordNotFoundException {           //finding the security by id
		
				
			Optional<SecurityEntity> optional = dao.findByAlertId(id);                 //id not there , then invalid id
			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new RecordNotFoundException("Invalid id");
			}
		
	}

	

	@Override
	public List<SecurityEntity> search() throws DatabaseException {                //searching all the security 
		
		
		

			if (dao.findAll().isEmpty()) {                                              //No record available then throw exception
				throw new DatabaseException("No Records available in Database");
			} else {
				return dao.findAll();
			}

		
	}
	

	
}
