package com.psl.oms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.psl.oms.model.StockItem;
import com.psl.oms.util.ConnectionManager;

/**
 * @author rancel
 *
 */

// Interacting with database 
// All Sql queries will be executed by this class
public class StockItemDao {
	
	private static final Logger logger=Logger.getLogger(StockItemDao.class.getName());
	Connection cn=ConnectionManager.getConnection();
	
	public Connection getCn() {
		return cn;
	}
	

 
	// create customer in database
	public void addStockItem(StockItem e){

		try (PreparedStatement pstmt = cn.prepareStatement("insert into stockitems values(?,?,?,?,?)");) 
		{
			pstmt.setInt(1, e.getItemNumber());
			pstmt.setString(2, e.getItemDescription());
			pstmt.setDouble(3, e.getItemPrice());
			pstmt.setString(4, e.getUnit());
			pstmt.setInt(5, e.getQuantity());
			int i=pstmt.executeUpdate();
			System.out.println(i + " records inserted....");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not inserted properly ");
			e1.printStackTrace();
		}
			
		
	}
	
	// get Customer details
	public StockItem getStockItem(int id){
		String qry="select * from stockitems where stock_item_number=?";
		StockItem e=null;
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
			e=new StockItem();		
			e.setItemId(rs.getInt(1));
			e.setItemDescription(rs.getString(2));
			e.setItemPrice(rs.getDouble(3));
			e.setUnit(rs.getString(4));
			e.setQuantity(rs.getInt(5));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved successfully ");
			e1.printStackTrace();
		}
		return e;
	}
	
	// get All Employees
	public List<StockItem> getAllStockItem(){
		String qry="select * from stockitems";
		StockItem e=null;
		List<StockItem> eList=new ArrayList<>();
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
			e=new StockItem();		
			e.setItemId(rs.getInt(1));
			e.setItemDescription(rs.getString(2));
			e.setItemPrice(rs.getDouble(3));
			e.setUnit(rs.getString(4));
			e.setQuantity(rs.getInt(5));
			eList.add(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved successfully ");
			e1.printStackTrace();
		}
		return eList;
		
	}
	
}