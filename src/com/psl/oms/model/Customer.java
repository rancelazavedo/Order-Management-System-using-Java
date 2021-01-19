package com.psl.oms.model;

import java.sql.Date;

/**
 * @author rancel
 *
 */

public class Customer {
	private int id;
	private String name;
	private String homePhone;
	private String cellPhone;
	private String workPhone;
	private String street;
	private String city;
	private String state;
	private String zip;
	public Customer()
	{
		id=1;
		name="Rancel";
	}
	public Customer(int id,String name,String homePhone,String cellPhone,String workPhone,String street,String city,String state,String zip)
	{
		this.id=id;
		this.name=name;
		this.homePhone=homePhone;
		this.cellPhone=cellPhone;
		this.workPhone=workPhone;
		this.street=street;
		this.city=city;
		this.state=state;
		this.zip=zip;
	}
	public int getCustomerId() {
		return id;
	}
	public void setCustomerId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return name;
	}
	public void setCustomerName(String name) {
		this.name = name;
	}
	public String getHomePhoneNo() {
		return homePhone;
	}
	public void setHomePhoneNo(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getCellPhoneNo() {
		return cellPhone;
	}
	public void setCellPhoneNo(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getWorkPhoneNo() {
		return workPhone;
	}
	public void setWorkPhoneNo(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getStreet() {
		return street;
	}
	public void setCity(String city) {
		this.city = city;
	}	
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name
				+ ", homephone=" + homePhone + ", cellphone=" + cellPhone + ", workphone=" + workPhone + ", street="
				+ street +  ", city=" + city +  ", state=" + state  +", zip=" + zip +"]";
	}
	
	public String customerName()
    {
    	return this.name;
    }
	public void printPhoneNumbers()
	{
		System.out.println("Home Phone Number: "+homePhone);
		System.out.println("Personal Phone Number: "+cellPhone);
		System.out.println("Work Phone Number: "+workPhone);
	}
	public void printShippingAddress()
	{
		System.out.println("Shipping Address: "+street+" "+city+" "+state+" "+zip);
	}
	public void setPhoneNumbers(String a,String b,String c)
	{
		homePhone=a;
		cellPhone=b;
		workPhone=c;
	}
	public void setPrintingAddress(String a,String b,String c,String d)
	{
		street=a;
		city=b;
		state=c;
		zip=d;
	}
	public void print()
	{
		System.out.println("\n---------------------");
		System.out.println("id: "+id);
		System.out.println("Name: "+name);
		printPhoneNumbers();
		printShippingAddress();
		System.out.println("\n--------------------");
	}
	public void getPurchaseOrder(PurchaseOrder PO)
	{
		
	}
}