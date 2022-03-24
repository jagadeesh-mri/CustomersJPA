package com.mycompany.service;

import java.util.ArrayList;

import com.mycompany.dao.CustomerDao;
import com.mycompany.dao.JPACustomerDaoImpl;
import com.mycompany.dto.Customer;

public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao;
	
	public CustomerServiceImpl() {
		customerDao = new JPACustomerDaoImpl();
	}
	
	@Override
	public boolean save(Customer customer) {
		boolean result = false;
		if(customer.getCustomerId() <= 10000 && customer.getName().length() <= 100 && customer.getAge() <= 200 && customer.getPhone().length() <= 20) {
			result = customerDao.save(customer);
		}else {
			throw new IllegalArgumentException("Customer data is not valid");
		}
		return result;
	}

	@Override
	public ArrayList<Customer> getAll() {
		ArrayList<Customer> customers = customerDao.getAll();
		for (Customer customer : customers) {
			//validations
		}
		return customers;
	}

	@Override
	public boolean update(Customer customer) {
		
		return customerDao.update(customer);
	}

	@Override
	public boolean delete(int customerId) {
		return customerDao.delete(customerId);
	}

	@Override
	public Customer get(int customerId) {
		return customerDao.get(customerId);
	}

	
	
	
}
