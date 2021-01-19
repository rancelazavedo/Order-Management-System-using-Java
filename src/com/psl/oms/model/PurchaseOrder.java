package com.psl.oms.model;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.psl.oms.service.OrderItemService;
import com.psl.oms.service.StockItemService;

/**
 * @author rancel
 *
 */

public class PurchaseOrder {

	private Customer customer;
	private OrderItem[] order;
	private int poNumber;
	private boolean shipStatus=false;
    private Date orderDate;
    private Date shipDate;
    public PurchaseOrder()
    {
    	
    }
    public PurchaseOrder(int poNumber,Customer customer,Date orderDate,Date shipDate,boolean shipStatus)
    {
    	this.poNumber=poNumber;
    	this.customer=customer;
    	this.orderDate=orderDate;
    	this.shipDate=shipDate;
    	this.shipStatus=shipStatus;
    }
    public int getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(int poNumber) {
		this.poNumber = poNumber;
	}
	public int getCustomerNumber() {
		return customer.getCustomerId();
	}
	public void setCustomerNumber(int number) {
		customer.setCustomerId(number);
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer=customer;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate=orderDate;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate=shipDate;
	}
	public boolean getShipStatus() {
		return shipStatus;
	}
	public void setShipStatus(boolean shipStatus) {
		this.shipStatus=shipStatus;
	}
    public boolean isShipped()
    {
    	return shipStatus;
    }
    public boolean shipPurOrder()
    {
    	setShipDate(Date.valueOf("2020-02-02"));
    	shipStatus=true;
    	return shipStatus;
    }
    public double sumItems()
    {
    	double sum=0;
    	for(OrderItem oi:order)
    	{
    		sum=sum+oi.getTotal();
    	}
    	return sum;
    }
    public void create(int poNo,Date orderDt)
    {
    	poNumber=poNo;
    	orderDate=orderDt;
    	setShipDate(orderDt);
    }
    public void setItems(Customer c,OrderItem... o)
    {
    	this.customer=c;
    	this.order=o;
    	
    }
    public OrderItem[] getItems()
    {
    	return order;
    }
    public void setOrderItems()
	{
    	Scanner s=new Scanner(System.in);
		StockItemService skService=new StockItemService();
		OrderItemService oservice=new OrderItemService();
		List<StockItem> list=skService.getAllStockItem();
		System.out.println("All StockItems currently available with us");
		System.out.printf("%-5s%-20s%-20s%-20s\n","ID","Description","Price","Quantity");
		for(StockItem k:list)
		{
			k.displayStockItem();
		}
		System.out.println("Enter the number of items you want to purchase");
		int n=s.nextInt();
		OrderItem o[]=new OrderItem[n];
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter the stock Id");
			int id=s.nextInt();
			//StockItem stock=new StockItem();
			StockItem stock=skService.getStockItem(id);
			System.out.println("Enter the quantity");
			int quantity=s.nextInt();
			o[i]=new OrderItem(stock,quantity);
		}
		order=o;
		for(OrderItem k:order)
		{
			oservice.insertOrderItem(k,poNumber);
		}
	}
    void printInvoice() {
        System.out.println("\nInvoice for Purchase Order Number : " + this.poNumber);
        System.out.println("=========================================");
        customer.print();
        System.out.println("------------------Items------------------");
        System.out.printf("%-15s%-10s%-10s%-10s\n", "Description", "Quantity", "Price", "Amount");
        for (OrderItem i : order)
            if (i.getStockItem() != null)
                System.out.printf("%-15s%-10s%-10s%-10s\n", i.getStockItem().getItemDescription(), i.getNumberOfItems(),i.getStockItem().getItemPrice(),  i.getTotal());
        System.out.println("-----------------------------------------");
        System.out.printf("%-34s%-10.1f\n", "Total Sum", sumItems());
        System.out.println("-----------------------------------------\n");
    }
    public void displayPurchaseOrder()
	{
		System.out.printf("%-10s%-20s%-20s%-20s%s\n",this.poNumber,this.customer.getCustomerId(),this.orderDate,this.shipDate,
				this.shipStatus);
	}
    public String getStringPurchaseOrder()
	{
    	String s1="------CUSTOMER BILL-------\n"
    			+"PO Number: "+this.poNumber+"\nCustomer Id: "+this.customer.getCustomerId()
    			+"\nCustomer Name: "+this.customer.customerName()	
    			+"\nOrder Date: "+this.orderDate+"\nShip Date: "
    			+this.shipDate+"\nShip Status: "+this.shipStatus+"\n";
		return s1;
	}
}

