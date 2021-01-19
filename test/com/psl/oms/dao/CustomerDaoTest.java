package com.psl.oms.dao;

import com.psl.oms.model.Customer;
import com.psl.oms.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class CustomerDaoTest {
    
    static Connection cn = null;
    static Customer c = null;
    
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
        c = new Customer();
        c.setCustomerName("Sherlock");
        c.setCellPhoneNo("9999999999");
        c.setWorkPhoneNo("9999999999");
        c.setHomePhoneNo("2727272");
        c.setStreet("221B Baker Street");
        c.setCity("Melbourne");
        c.setState("Victoria");
        c.setZip("3004");
        cn.setAutoCommit(false);
    }
    
    @After
    public void tearDown() throws SQLException {
        cn.rollback();
    }
    
    private int getRecordCountMethod() throws SQLException {
        Statement stmt = cn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM customer");
        rs.next();
        return rs.getInt(1);
    }
    
    private Customer getRecordByID(int custID) throws SQLException {
        PreparedStatement pstmt = cn.prepareStatement("SELECT * FROM customer WHERE customer_no = ?");
        pstmt.setInt(1, custID);
        Customer customer = null;
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            customer = new Customer();
            customer.setCustomerId(rs.getInt(1));
            customer.setCustomerName(rs.getString(2));
            customer.setHomePhoneNo(rs.getString(3));
            customer.setCellPhoneNo(rs.getString(4));
            customer.setWorkPhoneNo(rs.getString(5));
            customer.setStreet(rs.getString(6));
            customer.setCity(rs.getString(7));
            customer.setState(rs.getString(8));
            customer.setZip(rs.getString(9));
        }
        return customer;
    }

    /**
     * Test of getCn method, of class CustomerDao.
     */
    @Test
    public void testGetCn() {
        System.out.println("getCn");
        CustomerDao instance = new CustomerDao();
        Connection expResult = cn;
        Connection result = instance.getCn();
        assertEquals("Connection not established", expResult, result);
    }

    /**
     * Test of addCustomer method, of class CustomerDao.
     */
    @Test
    public void testAddCustomer() throws SQLException {
        System.out.println("addCustomer");
        CustomerDao instance = new CustomerDao();
        int records = getRecordCountMethod();
        instance.addCustomer(c);
        assertTrue("Customer not added", records + 1 == getRecordCountMethod());
    }

    /**
     * Test of getCustomer method, of class CustomerDao.
     */
    @Test
    public void testGetCustomer() throws SQLException {
        System.out.println("getCustomer");
        int id = 0;
        CustomerDao instance = new CustomerDao();
        Customer expResult = getRecordByID(id);
        Customer result = instance.getCustomer(id);
        assertEquals("Customer not fetched", expResult, result);
    }
    
}
