/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.psl.oms.dao;

import com.psl.oms.model.Customer;
import com.psl.oms.model.OrderItem;
import com.psl.oms.model.PurchaseOrder;
import com.psl.oms.service.CustomerService;
import com.psl.oms.util.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rancel
 */

public class PurchaseOrderDaoTest {
    
    static Connection cn = null;
    static PurchaseOrder po = null;
    
    @BeforeClass
    public static void setUpClass() {
        cn = ConnectionManager.getConnection();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        cn.close();
    }
    
    @Before
    public void setUp() throws SQLException {
        po = new PurchaseOrder();
		Customer c=new Customer();
		c.setCustomerId(103);
		c.setCustomerName("Sherlock");
        c.setCellPhoneNo("9999999999");
        c.setWorkPhoneNo("9999999999");
        c.setHomePhoneNo("2727272");
        c.setStreet("221B Baker Street");
        c.setCity("Melbourne");
        c.setState("Victoria");
        c.setZip("3004");
		po.setCustomer(c);
        po.setShipStatus(false);
        po.setOrderDate(new Date(System.currentTimeMillis()));
        po.setShipDate(new Date(System.currentTimeMillis()));
        cn.setAutoCommit(false);
    }
    
    @After
    public void tearDown() throws SQLException {
        cn.rollback();
    }
    
    private int getRecordCountMethod() throws SQLException {
        Statement stmt = cn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM purchaseorder");
        rs.next();
        return rs.getInt(1);
    }
    
    private PurchaseOrder getRecordMethod(int orderID) throws SQLException {
        PreparedStatement pstmt = cn.prepareStatement("SELECT * FROM purchaseorder WHERE po_number = ?");
        pstmt.setInt(1, orderID);
        ResultSet rs = pstmt.executeQuery();
        PurchaseOrder po = null;
        if(rs.next()){
            po = new PurchaseOrder();
            po.setPoNumber(rs.getInt(1));
            po.setCustomerNumber(rs.getInt(2));
            CustomerService service=new CustomerService();
    		Customer c1=service.getCustomer(rs.getInt(2));
    		po.setCustomer(c1);
            po.setOrderDate(rs.getDate(3));
            po.setShipDate(rs.getDate(4));
            po.setShipStatus(rs.getBoolean(5));
        }
        return po;
    }
    
    private int getRecordCountByDateMethod(YearMonth ship_date) throws SQLException {
        PreparedStatement pstmt = cn.prepareStatement("SELECT COUNT(*) FROM purchaseorder WHERE YEAR(ship_date) = ? AND MONTH(ship_date) = ? AND is_shipped = 1");
        pstmt.setInt(1, ship_date.getYear());
        pstmt.setInt(2, ship_date.getMonthValue());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    private double getRecordSumByDateMethod(YearMonth ship_date) throws SQLException {
        PreparedStatement pstmt = cn.prepareStatement("SELECT SUM(item_price * number_of_item) FROM purchaseorder po INNER JOIN orderitem oi ON po.po_number = oi.po_number INNER JOIN stockitem si ON oi.stock_item_number = si.stock_item_number WHERE YEAR(ship_date) = ? AND MONTH(ship_date) = ?");
        pstmt.setInt(1, ship_date.getYear());
        pstmt.setInt(2, ship_date.getMonthValue());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    private int getRecordCountByID(int custID) throws SQLException {
        PreparedStatement pstmt = cn.prepareStatement("SELECT COUNT(*) FROM purchaseorder WHERE customer_no = ?");
        pstmt.setInt(1, custID);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    private PurchaseOrder getRecordCountByOrderID(int custID, int orderID) throws SQLException {
        PreparedStatement pstmt = cn.prepareStatement("SELECT * FROM purchaseorder WHERE customer_no = ? AND po_number = ?");
        pstmt.setInt(1, custID);
        pstmt.setInt(2, orderID);
        ResultSet rs = pstmt.executeQuery();
        PurchaseOrder po = null;
        if(rs.next()){
            po = new PurchaseOrder();
            po.setPoNumber(rs.getInt(1));
            po.setCustomerNumber(rs.getInt(2));
            CustomerService service=new CustomerService();
    		Customer c1=service.getCustomer(rs.getInt(2));
    		po.setCustomer(c1);
            po.setOrderDate(rs.getDate(3));
            po.setShipDate(rs.getDate(4));
            po.setShipStatus(rs.getBoolean(5));
        }
        return po;
    }
    
    private int getRecordCountByorderIDMethod() throws SQLException {
        Statement stmt = cn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM purchaseorder GROUP BY customer_no ORDER BY COUNT(*) DESC LIMIT 1");
        rs.next();
        return rs.getInt(1);
    }
    
    /**
     * Test of getCn method, of class PurchaseOrderDao.
     */
    @Test
    public void testGetCn() {
        System.out.println("getCn");
        PurchaseOrderDao instance = new PurchaseOrderDao();
        Connection expResult = cn;
        Connection result = instance.getCn();
        assertEquals("Connection not established", expResult, result);
    }

    /**
     * Test of addPurchaseOrder method, of class PurchaseOrderDao.
     */
    @Test
    public void testAddPurchaseOrder() throws SQLException {
        System.out.println("addPurchaseOrder");        
        PurchaseOrderDao instance = new PurchaseOrderDao();   
        int records = getRecordCountMethod();
        instance.addPurchaseOrder(po);
        assertTrue("Purchase order not added", records + 1 == getRecordCountMethod());
    }

    /**
     * Test of getAllPurchaseOrderForDate method, of class PurchaseOrderDao.
     */
    @Test
    public void testGetToAndFrom() {
        System.out.println("getToAndFrom");
        Date start = Date.valueOf("2020-09-29");
        Date end = Date.valueOf("2020-09-31");
        PurchaseOrderDao instance = new PurchaseOrderDao();
        List<PurchaseOrder> result = instance.getAllPurchaseOrderForDate(start, end);
        for(PurchaseOrder p : result)
            assertTrue("Order date is out of the given interval", (p.getOrderDate().after(start) && p.getOrderDate().before(end)) || p.getOrderDate().equals(start) || p.getOrderDate().equals(end));
    }

    /**
     * Test of getAllPurchaseOrderForDate method, of class PurchaseOrderDao.
     */
    @Test
    public void testOrdersOnDate() {
        System.out.println("ordersOnDate");
        Date date = Date.valueOf("2020-09-29");
        PurchaseOrderDao instance = new PurchaseOrderDao();
        List<PurchaseOrder> result = instance.getAllPurchaseOrderForDate(date);
        for(PurchaseOrder p : result)
            assertEquals("Order date is incorrect", p.getOrderDate(), date);
    }



    /**
     * Test of getAllPurchaseOrderForCustomer(c method, of class PurchaseOrderDao.
     */
    @Test
    public void testGetAllPurchaseOrderForCustomer() throws SQLException {
        System.out.println("getPurchaseOrderList");
        int custID = 103;
        PurchaseOrderDao instance = new PurchaseOrderDao();
        int expResult = getRecordCountByID(custID);
        CustomerService cservice= new CustomerService();
        Customer c=cservice.getCustomer(custID);
        List<PurchaseOrder> result = instance.getAllPurchaseOrderForCustomer(po.getCustomer());
        assertTrue("Fetched wrong number of orders", expResult == result.size());
    }
}
