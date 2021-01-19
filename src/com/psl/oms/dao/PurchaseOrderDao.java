package com.psl.oms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.psl.oms.model.Customer;
import com.psl.oms.model.PurchaseOrder;
import com.psl.oms.model.StockItem;
import com.psl.oms.service.CustomerService;
import com.psl.oms.service.PurchaseOrderService;
import com.psl.oms.util.ConnectionManager;

/**
 * @author rancel
 *
 */

// Interacting with database 
// All Sql queries will be executed by this class
public class PurchaseOrderDao {
	
	private static final Logger logger=Logger.getLogger(OrderItemDao.class.getName());
	Connection cn=ConnectionManager.getConnection();
	
	public Connection getCn() {
		return cn;
	}
	

 
	// create customer in database
	public void addPurchaseOrder(PurchaseOrder e){

		try (PreparedStatement pstmt = cn.prepareStatement("insert into purchaseorder values(?,?,?,?,?)");) 
		{
			pstmt.setInt(1, e.getPoNumber());
			CustomerService cservice=new CustomerService();
			PurchaseOrderService pservice=new PurchaseOrderService();
    		Customer c1=e.getCustomer();
			pstmt.setInt(2, c1.getCustomerId());
			pstmt.setDate(3, e.getOrderDate());
			pstmt.setDate(4, e.getShipDate());
			pstmt.setBoolean(5, e.getShipStatus());
			int i=pstmt.executeUpdate();
			System.out.println(i + " records inserted....");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not inserted properly ");
			e1.printStackTrace();
		}
			
		
	}
	
	// get Customer details
	public PurchaseOrder getPurchaseOrder(int id){
		String qry="select * from purchaseorder where po_number=?";
		PurchaseOrder e=null;
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			pstmt.setInt(1, id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
			e=new PurchaseOrder();		
			e.setPoNumber(rs.getInt(1));
			e.setCustomerNumber(rs.getInt(2));
			e.setOrderDate(rs.getDate(3));
			e.setShipDate(rs.getDate(4));
			e.setShipStatus(rs.getBoolean(5));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved successfully ");
			e1.printStackTrace();
		}
		return e;
	}
	public PurchaseOrder getPurchaseOrder(int id1,int id2){
		String qry="select * from purchaseorder where po_number=? AND customer_no=?";
		PurchaseOrder e=null;
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			pstmt.setInt(1, id1);
			pstmt.setInt(2, id2);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
			e=new PurchaseOrder();		
			e.setPoNumber(rs.getInt(1));
			int cid=rs.getInt(2);
			CustomerService service=new CustomerService();
			Customer c1=service.getCustomer(id2);
			e.setCustomer(c1);
			e.setOrderDate(rs.getDate(3));
			e.setShipDate(rs.getDate(4));
			e.setShipStatus(rs.getBoolean(5));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved successfully ");
			e1.printStackTrace();
		}
		return e;
	}
	
	// get All Employees
	public List<PurchaseOrder> getAllPurchaseOrderForCustomer(Customer c){
		String qry="select * from purchaseorder where `customer_no`=?";
		PurchaseOrder e=null;
		 List<PurchaseOrder> list=new ArrayList<>();
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			pstmt.setInt(1, c.getCustomerId());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
			e=new PurchaseOrder();		
			e.setPoNumber(rs.getInt(1));
			//e.setCustomerNumber(rs.getInt(2));
			int cid=rs.getInt(2);
			CustomerService service=new CustomerService();
			Customer c1=service.getCustomer(cid);
			e.setCustomer(c1);
			e.setOrderDate(rs.getDate(3));
			e.setShipDate(rs.getDate(4));
			e.setShipStatus(rs.getBoolean(5));
			//System.out.println(e.getPoNumber());
			//if(c1.getCustomerId()==c.getCustomerId())
			list.add(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved successfully ");
			e1.printStackTrace();
		}
		return list;
	}
	
	public List<PurchaseOrder> getAllPurchaseOrderForDate(Date d1,Date d2){
		String qry="SELECT * FROM `purchaseorder` WHERE `order_date` BETWEEN ? AND ?";
		PurchaseOrder e=null;
		 List<PurchaseOrder> list=new ArrayList<>();
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			pstmt.setDate(1, d1);
			pstmt.setDate(2, d2);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
			e=new PurchaseOrder();		
			e.setPoNumber(rs.getInt(1));
			//e.setCustomerNumber(rs.getInt(2));
			int cid=rs.getInt(2);
			CustomerService service=new CustomerService();
			Customer c1=service.getCustomer(cid);
			e.setCustomer(c1);
			e.setOrderDate(rs.getDate(3));
			e.setShipDate(rs.getDate(4));
			e.setShipStatus(rs.getBoolean(5));
			list.add(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved successfully ");
			e1.printStackTrace();
		}
		return list;
	}
	public List<PurchaseOrder> getAllPurchaseOrderForDate(Date d){
		String qry="SELECT * FROM `purchaseorder` WHERE `order_date`= ?";
		PurchaseOrder e=null;
		 List<PurchaseOrder> list=new ArrayList<>();
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			pstmt.setDate(1, d);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
			e=new PurchaseOrder();		
			e.setPoNumber(rs.getInt(1));
			//e.setCustomerNumber(rs.getInt(2));
			int cid=rs.getInt(2);
			CustomerService service=new CustomerService();
			Customer c1=service.getCustomer(cid);
			e.setCustomer(c1);
			e.setOrderDate(rs.getDate(3));
			e.setShipDate(rs.getDate(4));
			e.setShipStatus(rs.getBoolean(5));
			//System.out.println(e.getPoNumber());
			list.add(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved successfully ");
			e1.printStackTrace();
		}
		return list;
	}
	public List<PurchaseOrder> delayedPurchaseOrder(){
		String qry="SELECT * FROM `purchaseorder` WHERE `is_shipped`=false AND  DATE_ADD(`order_date`,INTERVAL 4 DAY)<=CURRENT_DATE()";
		PurchaseOrder e=null;
		 List<PurchaseOrder> list=new ArrayList<>();
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
			e=new PurchaseOrder();		
			e.setPoNumber(rs.getInt(1));
			int cid=rs.getInt(2);
			CustomerService service=new CustomerService();
			Customer c1=service.getCustomer(cid);
			e.setCustomer(c1);
			e.setOrderDate(rs.getDate(3));
			e.setShipDate(rs.getDate(4));
			e.setShipStatus(rs.getBoolean(5));
			list.add(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved successfully ");
			e1.printStackTrace();
		}
		return list;
	}

	public void updatePurchaseOrder(int id)
	{
		String qry="UPDATE `purchaseorder` SET `ship_date`=CURRENT_DATE(),`is_shipped`=true WHERE `po_number`=?";
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
			System.out.println(" record updated....");
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not updated successfully ");
			e1.printStackTrace();
		}
	}
	public int getMax() {
		String qry="SELECT `customer_no`,COUNT(`customer_no`) AS total\r\n" + 
				"FROM `purchaseorder`\r\n" + 
				"GROUP BY `customer_no`\r\n" + 
				"HAVING MAX(total)\r\n" + 
				"LIMIT 0,1";
		int val=0;	
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			//pstmt.setInt(1,"customer_no");
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				val=rs.getInt(1);
				}
			}catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved successfully ");
			e1.printStackTrace();
		 }
		return val;
	}
	
	public void findTotalMonthlyOrders()
	{
		String qry="SELECT COUNT(`order_date`) AS total ,MONTH(`order_date`) AS month "
				+ "FROM `purchaseorder` GROUP BY MONTH(`order_date`)";
		int total=0;	
		String month;
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			//pstmt.setInt(1,"customer_no");
			System.out.println("\nTotal sales month wise");
			System.out.println("Month"+"\t"+"Profit");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				total=rs.getInt(1);
				month=rs.getString(2);
				System.out.println(month+"\t"+total);
				}
			}catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved successfully ");
			e1.printStackTrace();
		 }
	}
	public void findMonthlyProfits()
	{
		String qry="SELECT MONTH(`purchaseorder`.`order_date`) AS month,\r\n" + 
				"SUM(`orderitem`.`number_of_items`*`stockitems`.`item_price`) AS amount \r\n" + 
				"FROM `purchaseorder` \r\n" + 
				"INNER JOIN\r\n" + 
				"`orderitem`\r\n" + 
				"ON `purchaseorder`.`po_number`= `orderitem`.`po_number`\r\n" + 
				"INNER JOIN\r\n" + 
				"`stockitems`\r\n" + 
				"ON\r\n" + 
				"`stockitems`.`stock_item_number`=  `orderitem`.`stock_item_number`\r\n" + 
				"GROUP BY\r\n" + 
				"MONTH(`purchaseorder`.`order_date`)";
		int total=0;	
		String month;
		try (PreparedStatement pstmt = cn.prepareStatement(qry)) {
			//pstmt.setInt(1,"customer_no");
			System.out.println("\nTotal sales month wise");
			System.out.println("Month"+"\t"+"Total");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				total=rs.getInt(2);
				month=rs.getString(1);
				System.out.println(month+"\t"+total);
				}
			}catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.info("Record not retrieved successfully ");
			e1.printStackTrace();
		 }
	}
}
	
