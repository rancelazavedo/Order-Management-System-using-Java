package com.psl.oms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.psl.oms.model.Customer;
import com.psl.oms.util.ConnectionManager;

/**
 * @author rancel
 *
 */

// Interacting with database 
// All Sql queries will be executed by this class
public class CustomerDao {
	private static final Logger logger=Logger.getLogger(CustomerDao.class.getName());
	Connection cn=ConnectionManager.getConnection();
	
	public Connection getCn() {
		return cn;
	}
	

 
	// create customer in database
	public void addCustomer(Customer e){

		try (PreparedStatement pstmt = cn.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?)");) 
		{
			pstmt.setInt(1, e.getCustomerId());
			pstmt.setString(2, e.customerName());
			pstmt.setString(3, e.getHomePhoneNo());
			pstmt.setString(4, e.getCellPhoneNo());
			pstmt.setString(5, e.getWorkPhoneNo());
			pstmt.setString(6, e.getStreet());
			pstmt.setString(7, e.getCity());
			pstmt.setString(8, e.getState());
			pstmt.setString(9, e.getZip());
			int i=pstmt.executeUpdate();
			System.out.println(i + " records inserted....");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Customer not saved successfully "+e1.getMessage());
			e1.printStackTrace();
		}
			
		
	}
	
	// get Customer details
	public Customer getCustomer(int id){
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
			e.setCellPhoneNo(rs.getString(4));
			e.setWorkPhoneNo(rs.getString(5));
			e.setStreet(rs.getString(6));
			e.setCity(rs.getString(7));
			e.setState(rs.getString(8));
			e.setZip(rs.getString(9));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Customer not retrieved successfully"+e1.getMessage());
			e1.printStackTrace();
		}
		return e;
	}
	
}