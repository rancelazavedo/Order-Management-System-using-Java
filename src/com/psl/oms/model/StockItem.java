package com.psl.oms.model;

/**
 * @author rancel
 *
 */

public class StockItem {
	private static int count=0;
	private int itemNumber;
	private String itemDescription;
	private double itemPrice;
	private String unit;
	private int quantity;
	public StockItem()
	{
		itemNumber=0;
		itemDescription=null;
		itemPrice=0;
	}
	public StockItem(int a,String b,double c)
	{
		itemNumber=a;
		itemDescription=b;
		itemPrice=c;
	}
	public StockItem(int a,String b,double c,String unit,int quantiy)
	{
		this.itemNumber=a;
		this.itemDescription=b;
		this.itemPrice=c;
		this.unit=unit;
		this.quantity=quantity;
	}
	public void setItemId(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public int getItemNumber()
	{
		return itemNumber;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public double getItemPrice()
	{
		return itemPrice;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription=itemDescription;
	}
	public String getItemDescription()
	{
		return itemDescription;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	public int getQuantity()
	{
		return quantity;	
	}
	public void setUnit(String unit) {
		this.unit=unit;
	}
	public String getUnit()
	{
		return unit;	
	}
	enum Unit
	{
		Kg(1),Gallons(2),Numbers(3),Grams(4);
		private final int LC;
		Unit(int LC){
			this.LC=LC;
		}
		public int getLC() {
			return this.LC;
		}
	}
	public void displayStockItem()
	{
		System.out.printf("%-5s%-20s%-20s%s %s\n",this.itemNumber,this.itemDescription,this.itemPrice,
				this.quantity,this.unit);
	}
	

}
