package com.psl.oms.model;

import java.util.*;

import com.psl.oms.service.StockItemService;

/**
 * @author rancel
 *
 */

public class OrderItem {
	StockItem stockItem=new StockItem();
	private int numberOfItems;
	public OrderItem()
	{
		
	}
	OrderItem(StockItem stockItem,int numberOfItems)
	{
		this.stockItem=stockItem;
		this.numberOfItems= numberOfItems;
	}
	public void setStockItem(StockItem stockitem) {
		this.stockItem=stockItem;
	}
	public StockItem getStockItem() {
        return this.stockItem;
    }
	public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems=numberOfItems;
    }
	public int getNumberOfItems() {
        return this.numberOfItems;
    }
	
	public double getTotal()
	{
		double total=stockItem.getItemPrice()*numberOfItems;
		return total;
	}
	public void printOrders()
	{
		System.out.println(stockItem.getItemNumber()+"\t"+numberOfItems+"\t"+stockItem.getItemDescription());
	}
	
}
