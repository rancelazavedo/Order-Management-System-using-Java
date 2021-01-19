package com.psl.oms.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import com.psl.oms.dao.PurchaseOrderDao;
import com.psl.oms.model.Customer;
import com.psl.oms.model.PurchaseOrder;
import com.psl.oms.util.ConnectionManager;

/**
 * @author rancel
 *
 */
public class PurchaseOrderService {
	
	PurchaseOrderDao dao=new PurchaseOrderDao();
	
	public int getMaxCustomer(){
		return dao.getMax();
	}
	
	public PurchaseOrder getPurchaseOrder(int id){
		return dao.getPurchaseOrder(id);
	}
	public PurchaseOrder getPurchaseOrder(int id1,int id2){
		return dao.getPurchaseOrder(id1,id2);
	}
	public List<PurchaseOrder> getAllPurchaseOrderForCustomer(Customer c)
	{
		return dao.getAllPurchaseOrderForCustomer(c);
	}
	public List<PurchaseOrder> getAllPurchaseOrderForDate(Date d1,Date d2)
	{
		return dao.getAllPurchaseOrderForDate(d1,d2);
	}
	public List<PurchaseOrder> getAllPurchaseOrderForDate(Date d)
	{
		return dao.getAllPurchaseOrderForDate(d);
	}
	public void updatePurchaseOrder(int id)
	{
		dao.updatePurchaseOrder(id);
	}
	public List<PurchaseOrder> delayedPurchaseOrder()
	{
		return dao.delayedPurchaseOrder();
	}
	public void insertPurchaseOrder(PurchaseOrder e){
		dao.addPurchaseOrder(e);
	}
	public void findTotalMonthlyOrders()
	{
		dao.findTotalMonthlyOrders();
	}
	public void findMonthlyProfits()
	{
		dao.findMonthlyProfits();
	}

	public void insertPurchaseOrder(List<PurchaseOrder> productList){
		
		
		try {
			// this code is written for single threaded execution
			//	ConnectionManager.getConnection().setAutoCommit(false);
			// alternate way
			dao.getCn().setAutoCommit(false);
			for (PurchaseOrder product : productList) {
			 dao.addPurchaseOrder(product);
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
