package com.mycompany.dao;

import java.util.ArrayList;

import com.mycompany.dto.Customer;

public interface CustomerDao {

	public boolean save(Customer customer); //C
	public ArrayList<Customer> getAll(); //R
	public boolean update(Customer customer); //U
	public boolean delete(int customerId); //D
	public Customer get(int customerId); //R
	
}
