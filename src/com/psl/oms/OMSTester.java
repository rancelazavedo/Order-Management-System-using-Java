package com.psl.oms;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.psl.oms.model.Customer;
import com.psl.oms.model.PurchaseOrder;
import com.psl.oms.model.StockItem;
import com.psl.oms.service.CustomerService;
import com.psl.oms.service.OrderItemService;
import com.psl.oms.service.PurchaseOrderService;
import com.psl.oms.service.StockItemService;

/**
 * @author rancel
 *
 */

public class OMSTester {
	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		int flag=0,choice;
		do
		{
			System.out.println("Choose any one option");
			System.out.println("1:Add new customer");
			System.out.println("2:Add new Purchase Order");
			System.out.println("3:Add new Stock item");
			System.out.println("4:Fetch Customer based on id");
			System.out.println("5:Fetch Orders placed by specific Customer");
			System.out.println("6:Fetch Orders using Dates");
			System.out.println("7:Ship order and Update shipping status ");
			System.out.println("8:Fetch Delayed orders");
			System.out.println("9:Fetch all Stock items");
			System.out.println("10:Fetch month wise total orders shipped");
			System.out.println("11:Total amount collected based on month");
			System.out.println("12:Find Customer who has made maximum orders");
			System.out.println("13:Generate bill for specific customers order");
			System.out.println("14:Exit");
			choice=s.nextInt();
			switch(choice)
			{
			case 1:
			{
				CustomerService service =new CustomerService();
				System.out.println("Enter the customer details");
				System.out.println("Enter the customer id");
				int id=s.nextInt();
				System.out.println("Enter the customer name");
				String name=s.nextLine();
				name+=s.nextLine();
				System.out.println("Enter the customer home phone number");
				String homeno=s.next();
				System.out.println("Enter the customer cell phone number");
				String cellno=s.next();
				System.out.println("Enter the customer work phone number");
				String workno=s.next();
				System.out.println("Enter the customer street");
				String street=s.next();
				System.out.println("Enter the customer city");
				String city=s.next();
				System.out.println("Enter the customer state");
				String state=s.next();
				System.out.println("Enter the customer zipcode");
				String zip=s.next();
				Customer c=new Customer(id,name,homeno,cellno,workno,street,city,state,zip);
				service.insertCustomer(c);
				break;
			}
			case 2:
			{
				PurchaseOrderService service=new PurchaseOrderService();
				CustomerService cservice =new CustomerService();
				OrderItemService oservice=new OrderItemService();
				System.out.println("Enter the Purchased Orders details");
				System.out.println("Enter the Order number");
				int pid=s.nextInt();
				System.out.println("Enter the Customer number");
				int cid=s.nextInt();
				Customer c=cservice.getCustomer(cid);
				System.out.println("Enter dates in formate yyyy-mm-dd");
				System.out.println("Enter the Order Date");
				//String d="2020-09-19";
				String d1=s.next();
				System.out.println("Enter the Shipping Date");
				String d2=s.next();
				PurchaseOrder p=new PurchaseOrder(pid,c,Date.valueOf(d1),Date.valueOf(d2), false);
				service.insertPurchaseOrder(p);
				p.setOrderItems();
				break;
			}
			case 3:
			{
				StockItemService service=new StockItemService();
				System.out.println("Enter the StockItem details");
				System.out.println("Enter the Stock id");
				int id=s.nextInt();
				System.out.println("Enter the Stock name");
				String name=s.next();
				System.out.println("Enter the Stock price");
				double price=s.nextDouble();
				System.out.println("Enter the Stock unit");
				String unit=s.next();
				System.out.println("Enter the Stock quantity");
				int quantity=s.nextInt();
				StockItem stock=new StockItem(id,name,price,unit,quantity);
				service.insertStockItem(stock);
				break;
			}
			case 4:
			{
				CustomerService service =new CustomerService();
				System.out.println("Enter the customer id");
				int id=s.nextInt();
				Customer c=service.getCustomer(id);
				System.out.println("Customer details are ");
				c.print();
				break;
			}
			case 5:
			{
				CustomerService cservice =new CustomerService();
				PurchaseOrderService pservice=new PurchaseOrderService();
				System.out.println("Enter the customer id");
				int id=s.nextInt();
				Customer c=cservice.getCustomer(id);
				System.out.println("Customer details for customer:"+c.getCustomerName()+"");
				c.print();
				List<PurchaseOrder> p=pservice.getAllPurchaseOrderForCustomer(c);
				System.out.printf("%-10s%-20s%-20s%-20s%-20s\n","PO No","Customer id","Order date","Ship Date","Status");
				for(PurchaseOrder k:p)
				{
					k.displayPurchaseOrder();
				}
				break;
			}
			case 6:
			{
				PurchaseOrderService pservice=new PurchaseOrderService();
				System.out.println("==>1:Find orders inbetween dates");
				System.out.println("==>2:Find orders on certain dates");
				int choice2=s.nextInt();
				switch(choice2)
				{
				case 1:
				{
					System.out.println("Enter the dates");
					String date1=s.next();
					String date2=s.next();
					List<PurchaseOrder> p=pservice.getAllPurchaseOrderForDate(Date.valueOf(date1),Date.valueOf(date2));
					System.out.printf("%-10s%-20s%-20s%-20s%-20s\n","PO No","Customer id","Order date","Ship Date","Status");
					for(PurchaseOrder k:p)
					{
						k.displayPurchaseOrder();
					}
					break;
				}
				case 2:
				{
					System.out.println("Enter the dates");
					String date=s.next();
					List<PurchaseOrder> p=pservice.getAllPurchaseOrderForDate(Date.valueOf(date));
					System.out.printf("%-10s%-20s%-20s%-20s%-20s\n","PO No","Customer id","Order date","Ship Date","Status");
					for(PurchaseOrder k:p)
					{
						k.displayPurchaseOrder();
					}
					break;
				}
				default:
					System.out.println("Wrong option chosen");
				}
				break;
			}
			case 7:
			{
				PurchaseOrderService pservice=new PurchaseOrderService();
				int id;
				System.out.println("Choose the order to be shipped");
				id=s.nextInt();
				pservice.updatePurchaseOrder(id);
				break;
			}
			case 8:
			{
				PurchaseOrderService pservice=new PurchaseOrderService();
				List<PurchaseOrder> p=pservice.delayedPurchaseOrder();
				System.out.println("Delayed Orders are:\n");
				System.out.printf("%-10s%-20s%-20s%-20s%-20s\n","PO No","Customer id","Order date","Ship Date","Status");
				for(PurchaseOrder k:p)
				{
					k.displayPurchaseOrder();
				}
				break;
			}
			case 9:
			{
				StockItemService service=new StockItemService();
				List<StockItem> list=service.getAllStockItem();
				System.out.println("All StockItem details");
				System.out.printf("%-5s%-20s%-20s%-20s\n","ID","Description","Price","Quantity");
				for(StockItem k:list)
				{
					k.displayStockItem();
				}
				break;
			}
			case 10:
			{
				PurchaseOrderService service=new PurchaseOrderService();
				service.findTotalMonthlyOrders();
				break;
			}
			case 11:
			{
				PurchaseOrderService service=new PurchaseOrderService();
				service.findMonthlyProfits();
				break;
			}
			case 12:
			{
				CustomerService cservice =new CustomerService();
				PurchaseOrderService pservice=new PurchaseOrderService();
				int val=pservice.getMaxCustomer();
				Customer c=cservice.getCustomer(val);
				System.out.println("Details of customer who has done maximum purchase ");
				c.print();
				break;
			}
			case 13:
			{
				System.out.println("Enter Specific customers id and order id");
				int cid=s.nextInt();
				System.out.println("Enter Specific customers product id");
				int pid=s.nextInt();
				PurchaseOrderService pservice=new PurchaseOrderService();
				OrderItemService oservice=new OrderItemService();
				PurchaseOrder p=pservice.getPurchaseOrder(pid,cid);
				String s1=p.getStringPurchaseOrder();
				s1+=oservice.getOrderItemString(pid);
				System.out.println(s1);
				BillGenerator bill=new BillGenerator();
				bill.writeFile(s1, pid);
				break;
			}
			case 14:
			{
				flag=1;
				break;
			}
			default:
			{
				System.out.println("Choose correct option");
			}
			}
		}while(flag==0);
	}

}
