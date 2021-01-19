package com.psl.oms;

import java.util.Arrays;
import java.util.List;

import com.psl.oms.model.Customer;
import com.psl.oms.service.CustomerService;

/**
 * @author rancel
 *
 */

public class Tester {
public static void main(String[] args) {
	
	
	//fetch 1mp 101 details
	
	// ConnectionManager - resposible for making and closing connection
	// Dao - Resposible for direct interacting with database
	// service - responsible for intracting with Dao classes and sometimes managing transactions
	
	CustomerService service =new CustomerService();
	//System.out.println(service.getCustomer(101));
	
	// Testing transactions

	List<Customer> cuslist=Arrays.asList(
			new Customer(100, "Jamie", "9191919191","9190019191","9191779191","GD","Mumbai","Maharastra", "402020"),
			new Customer(101, "Bill", "9090909090","7190019191","7191779191","Silver","Panjim","Goa", "402220"),
			new Customer(102, "Joe", "9898989898","7790019191","9191712191","Bz","Pune","Maharastra", "407720")
			);
	
	service.insertCustomer(cuslist);
	
}
}