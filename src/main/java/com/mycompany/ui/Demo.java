package com.mycompany.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mycompany.dao.CustomerDao;
//import com.mycompany.dao.CustomerDao;
import com.mycompany.dao.CustomerDaoImpl;
import com.mycompany.dto.Customer;
import com.mycompany.service.CustomerService;
import com.mycompany.service.CustomerServiceImpl;

public class Demo {

	public static void main(String[] args) throws SQLException {
		
		Scanner scanner = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.println("Choose an option");
			System.out.println("1. Save Customer");
			System.out.println("2. Get All Customers");
			System.out.println("3. Get Customer By Customer ID");
			System.out.println("4. Update Customer By Customer ID");
			System.out.println("5. Delete Customer By Customer ID");
			System.out.println("6. Exit program");
			
			choice = scanner.nextInt();
			
			CustomerService customerService = new CustomerServiceImpl();
			String data = null;
			Customer customer = null;
			String[] elements = null;
			switch (choice) {
				case 1:
					System.out.println("Enter customer id, name, phone, email, age (enter in a single line as comma separated values)");
					data = scanner.next()+scanner.nextLine();//123,Jagadeesh M,+91-9988998899,aaa@gmail.com,34
					elements = data.split(",");
					customer = new Customer(Integer.parseInt(elements[0]), elements[1], elements[2], elements[3], Integer.parseInt(elements[4]));
					System.out.println("Is customer saved: "+customerService.save(customer));
					break;
				case 2:
					ArrayList<Customer> customers = customerService.getAll();
					for (Customer customer2 : customers) {
						System.out.println(customer2);
					}
					break;
				case 3:
					System.out.println("Enter customer Id");
					customer = customerService.get(scanner.nextInt());
					System.out.println(customer);
					break;
				case 4:
					System.out.println("Enter customer id, name, phone, email, age (enter in a single line as comma separated values)");
					data = scanner.next()+scanner.nextLine();//123,Jagadeesh M,+91-9988998899,aaa@gmail.com,34
					elements = data.split(",");
					customer = new Customer(Integer.parseInt(elements[0]), elements[1], elements[2], elements[3], Integer.parseInt(elements[4]));
					System.out.println("Is customer updated: "+customerService.update(customer));
					break;
				case 5:
					System.out.println("Enter customer Id");
					System.out.println("Is customer deleted: "+customerService.delete(scanner.nextInt()));
					break;
				case 6:
					System.out.println("Exiting program");
					break;
				default:
					break;
			}
			
			
		} while (choice != 6);
		
		
		scanner.close();
		
		
	}

}