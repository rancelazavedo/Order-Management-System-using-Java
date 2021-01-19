package com.psl.oms.service;

import java.sql.SQLException;
import java.util.List;

import com.psl.oms.dao.CustomerDao;
import com.psl.oms.model.Customer;
import com.psl.oms.util.ConnectionManager;

/**
 * @author rancel
*
*/
public class CustomerService {
	
	CustomerDao dao=new CustomerDao();
	
	public Customer getCustomer(int id){
		return dao.getCustomer(id);
	}
	
	
	public void insertCustomer(Customer e){
		dao.addCustomer(e);
	}
	public void insertCustomer(List<Customer> cusList){
		
		
		try {
			// this code is written for single threaded execution
		//	ConnectionManager.getConnection().setAutoCommit(false);
			// alternate way
			dao.getCn().setAutoCommit(false);
			for (Customer customer : cusList) {
			if(customer.getCustomerName()==null)
				throw new Exception("Customer Name can't be null.....");
			 dao.addCustomer(customer);
			}
			
			
			// ConnectionManager.getConnection().commit();
			dao.getCn().commit();
			dao.getCn().setAutoCommit(true);
		} catch (Exception e) {
			
			try {
				//ConnectionManager.getConnection().rollback();
				dao.getCn().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
}
