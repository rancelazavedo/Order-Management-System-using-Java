package com.psl.oms.service;

import java.sql.SQLException;
import java.util.List;
import com.psl.oms.dao.StockItemDao;
import com.psl.oms.model.Customer;
import com.psl.oms.model.StockItem;
import com.psl.oms.util.ConnectionManager;

/**
 * @author rancel
 *
 */
public class StockItemService {
	
	StockItemDao dao=new StockItemDao();
	
	public StockItem getStockItem(int id){
		return dao.getStockItem(id);
	}
	
	
	public void insertStockItem(StockItem e){
		dao.addStockItem(e);
	}
	
	public List<StockItem> getAllStockItem()
	{
		return dao.getAllStockItem();
	}
	public void insertStockItem(List<StockItem> stockList){
		
		
		try {
			// this code is written for single threaded execution
		//	ConnectionManager.getConnection().setAutoCommit(false);
			// alternate way
			dao.getCn().setAutoCommit(false);
			for (StockItem item : stockList) {
			if(item.getItemDescription()==null)
				throw new Exception("Stock Item can't be null.....");
			 dao.addStockItem(item);
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
