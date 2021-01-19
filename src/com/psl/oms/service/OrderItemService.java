package com.psl.oms.service;

import java.sql.SQLException;
import java.util.List;

import com.psl.oms.dao.CustomerDao;
import com.psl.oms.dao.OrderItemDao;
import com.psl.oms.model.Customer;
import com.psl.oms.model.OrderItem;
import com.psl.oms.model.PurchaseOrder;
import com.psl.oms.util.ConnectionManager;

/**
  interact with Dao and controller class 
  
 */
public class OrderItemService {
	
	 OrderItemDao dao=new  OrderItemDao();
	
	public  OrderItem getOrderItem(int id){
		return dao.getOrderItem(id);
	}
	
	
	public void insertOrderItem(OrderItem o,int p){
		dao.addOrderItem(o,p);
	}
	public String getOrderItemString(int id)
	{
		return dao.getOrderItemString(id);
	}
	public void insertCustomer(List< OrderItem> list,int p){
		
		try {
			// this code is written for single threaded execution
		//	ConnectionManager.getConnection().setAutoCommit(false);
			// alternate way
			dao.getCn().setAutoCommit(false);
			for (OrderItem product : list) {
			 dao.addOrderItem(product,p);
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
