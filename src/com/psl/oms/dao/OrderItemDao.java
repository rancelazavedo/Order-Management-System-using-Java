package com.psl.oms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.psl.oms.model.Customer;
import com.psl.oms.model.OrderItem;
import com.psl.oms.model.PurchaseOrder;
import com.psl.oms.util.ConnectionManager;


// Interacting with database 
// All Sql queries will be executed by this class
public class OrderItemDao {
	private static final Logger logger=Logger.getLogger(OrderItemDao.class.getName());
	Connection cn=ConnectionManager.getConnection();
	
	public Connection getCn() {
		return cn;
	}
	

 
	// create customer in database
	public void addOrderItem(OrderItem e,int p){

		try (PreparedStatement pstmt = cn.prepareStatement("insert into "
				+ "orderitem(`stock_item_number`,`number_of_items`,`po_number`) values(?,?,?)");) 
		{
			pstmt.setInt(1,e.getStockItem().getItemNumber());
			pstmt.setInt(2, e.getNumberOfItems());
			pstmt.setInt(3,p);
			int i=pstmt.executeUpdate();
			System.out.println(i + " records inserted....");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not inserted properly "+e1.getMessage());
			e1.printStackTrace();
		}
			
		
	}
	
	// get Customer details
	public OrderItem getOrderItem(int id){
		String qry="select * from Customer where customer_no=?";
		Customer e=null;
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
			e=new Customer();		
			e.setCustomerId(rs.getInt(1));
			e.setCustomerName(rs.getString(2));
			e.setHomePhoneNo(rs.getString(3));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved properly "+e1.getMessage());
			e1.printStackTrace();
		}
		return e;
	}
	
	public String getOrderItemString(int id){
		String qry="SELECT `stockitems`.`item_decription`,"
				+ "(`number_of_items`*`stockitems`.`item_price`)"
				+ "AS subtotal,`number_of_items`,`stockitems`.`unit` "
				+ "FROM `orderitem` INNER JOIN `stockitems` "
				+ "ON `orderitem`.`stock_item_number`= `stockitems`.`stock_item_number` "
				+ "WHERE `orderitem`.`po_number`=?";
		String s1 = null;
		int total=0;
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			s1="\nItem which are ordered";
			while(rs.next()){
			s1+="\nItem Name:\t"+rs.getString(1)
			   +"\nAmount   :\t"+rs.getInt(2)
			   +"\nQuantity :\t"+rs.getInt(3)+" "+rs.getString(4)+"\n";
			   ;
			   total+=rs.getInt(2);
			}
			s1+="\nTotal Amount = "+total+"\n-----------------------------------";
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not inserted properly "+e1.getMessage());
			e1.printStackTrace();
		}
		return s1;
	}
	
	
}
