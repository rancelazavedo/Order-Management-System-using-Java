# ORDER MANAGEMENT SYSTEM

# SETUP DATABASE

METHOD 1(Create and populate DB) :
1. Create an empty database with the name 'order-management_db'.
2. Import the order-management_db.sql file from the location 'OrderManagementSystem/resources/'.

OR

METHOD 2(Create DB):
1. Run the SQL Commands in the console from 'OMSqueries.txt' located in 'OrderManagementSystem/resources'.

# SETUP APPLICATION
1. Import the 'OrderManagementSystem' project to Eclipse or NetBeans IDE.
2. Add the JUnit and MySql Connector '.jar' files located in 'OrderManagementSystem/Libraries' to the project.
3. Modify the 'config.properties' file as per your Database properties.
4. Ensure that the MySQL Server is runnning.

# FUNCTIONS PERFORMED
1.Add New Customer
2.Add new Purchase Order
3.Add new Stock Item
4.Fetch customer based on id
5.Fetch Orders placed by specific customers
6.Fetch orders placed for specific duration
a.fetch all orders placed between from and to date inclusive of both date
b.fetch all the orders placed for given date
7.Update order status to ship and update ship date based on order id.
8.Fetch delayed orders 
a.By default, every order placed by customer should get dispatched within the 4 days after placing the order (inclusive placed date). if order is not dispatched within the 4 days after placing date it should be considered as delayed order
9.Fetch all stock Items
10.Find month-wise total orders shipped.  
11.Find the total amount collected based on months
12.Find the customer who has made maximum orders.
13.Generate bill for customer for specific order. (create file under bills/customerid directory )

# PREREQUISTE
-Ensure the the path is correct to the Files Folder in BillGenerator
-Ensure XAMPP is running at all times during execution
-Run OMSTester to run the main code

# APPLICATION TESTED ON
- JDK version 1.8.0_202
- NetBeans IDE 8.2
- XAMPP version 3.2.2
