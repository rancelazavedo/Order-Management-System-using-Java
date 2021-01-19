/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.psl.oms.dao;

import com.psl.oms.model.StockItem;
import com.psl.oms.util.ConnectionManager;
import com.psl.oms.util.Unit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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

public class StockItemDaoTest {
    
    static Connection cn = null;
    static StockItem s = null;
    
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
        s = new StockItem();
        s.setItemDescription("Test item");
        s.setItemPrice(50.5);
        s.setQuantity(30);
        s.setUnit("KG");
        cn.setAutoCommit(false);
    }
    
    @After
    public void tearDown() throws SQLException {
        cn.rollback();
    }

    private int getRecordCountMethod() throws SQLException {
        Statement stmt = cn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM stockitems");
        rs.next();
        return rs.getInt(1);
    }
    
    private StockItem getRecordByID(int stockID) throws SQLException {
        PreparedStatement pstmt = cn.prepareStatement("SELECT * FROM customer WHERE customer_no = ?");
        pstmt.setInt(1, stockID);
        StockItem s = null;
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
                s = new StockItem();		
                s.setItemId(rs.getInt(1));
                s.setItemDescription(rs.getString(2));
                s.setItemPrice(rs.getDouble(3));
                s.setUnit(rs.getString(4));
                s.setQuantity(rs.getInt(5));
        }
        return s;
    }
    
    private int getRecordQuantityMethod(int stockID) throws SQLException {
        PreparedStatement pstmt = cn.prepareStatement("SELECT quantity FROM stockitems WHERE stock_item_number = ?");
        pstmt.setInt(1, stockID);
        ResultSet rs = pstmt.executeQuery();
        int quantity = 0;
        if(rs.next())
            quantity = rs.getInt(1);
        return quantity;
    }
    
    /**
     * Test of getCn method, of class StockItemDao.
     */
    @Test
    public void testGetCn() {
        System.out.println("getCn");
        StockItemDao instance = new StockItemDao();
        Connection expResult = cn;
        Connection result = instance.getCn();
        assertEquals("Connection not established", expResult, result);
    }

    /**
     * Test of addStockItem method, of class StockItemDao.
     */
    @Test
    public void testAddStockItem() throws SQLException {
        System.out.println("addStockItem");
        StockItemDao instance = new StockItemDao();
        int records = getRecordCountMethod();
        instance.addStockItem(s);
        assertTrue("Stock item not added", records + 1 == getRecordCountMethod());
    }

    /**
     * Test of getStockItem method, of class StockItemDao.
     */
    @Test
    public void testGetStockItem_0args() throws SQLException {
        System.out.println("getStockItem");
        StockItemDao instance = new StockItemDao();
        int expResult = getRecordCountMethod();
        List<StockItem> result = instance.getAllStockItem();
        assertEquals("All stock items not fetched", expResult, result.size());
    }

    /**
     * Test of getStockItem method, of class StockItemDao.
     */
    @Test
    public void testGetStockItem_int() throws SQLException {
        System.out.println("getStockItem");
        int stockID = 0;
        StockItemDao instance = new StockItemDao();
        StockItem expResult = getRecordByID(stockID);
        StockItem result = instance.getStockItem(stockID);
        assertEquals("Stock item not fetched", expResult, result);
    }
    
}
