package com.mycompany.service;

import java.util.ArrayList;

import com.mycompany.dto.Customer;

public interface CustomerService {
	public boolean save(Customer customer); 
	public ArrayList<Customer> getAll(); 
	public boolean update(Customer customer); 
	public boolean delete(int customerId); 
	public Customer get(int customerId); 
}
