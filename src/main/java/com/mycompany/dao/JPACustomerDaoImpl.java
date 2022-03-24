package com.mycompany.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mycompany.dto.Customer;

public class JPACustomerDaoImpl implements CustomerDao {

	@Override
	public boolean save(Customer customer) {
		boolean result = false;
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(customer);
			entityManager.getTransaction().commit();
			result = true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}finally {
			entityManager.close();
		}
		return result;
	}

	@Override
	public ArrayList<Customer> getAll() {
		ArrayList<Customer> customers = null;
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createQuery("from Customer");
			customers = (ArrayList<Customer>) query.getResultList();
			entityManager.getTransaction().commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}finally {
			entityManager.close();
		}
		return customers;
	}

	@Override
	public boolean update(Customer customer) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Customer customer2 = entityManager.find(Customer.class, customer.getCustomerId());
		customer2.setName(customer.getName());
		customer2.setPhone(customer.getPhone());
		customer2.setEmail(customer.getEmail());
		customer2.setAge(customer.getAge());
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	@Override
	public boolean delete(int customerId) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Customer customer = entityManager.find(Customer.class, customerId);
		entityManager.remove(customer);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	@Override
	public Customer get(int customerId) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Customer customer = entityManager.find(Customer.class, customerId);
		entityManager.getTransaction().commit();
		entityManager.close();
		return customer;
	}

	
	private EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Training");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}
	
	
}
