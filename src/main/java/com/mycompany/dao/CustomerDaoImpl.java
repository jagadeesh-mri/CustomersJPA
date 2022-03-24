package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mycompany.dto.Customer;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public boolean save(Customer customer) {
		String sql = "insert into t_customers values(?,?,?,?,?)";
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getCustomerId());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getPhone());
			preparedStatement.setString(4, customer.getEmail());
			preparedStatement.setInt(5, customer.getAge());
			int result = preparedStatement.executeUpdate();
			closeConnection(connection);
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<Customer> getAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String sql = "select * from t_customers";
			
			ResultSet resultSet = statement.executeQuery(sql);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
				System.out.println(resultSetMetaData.getColumnLabel(i));
			}
			
			while (resultSet.next()) {
				Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5));
				customers.add(customer);
			}
			resultSet.close();
			statement.close();
			closeConnection(connection);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public synchronized boolean update(Customer customer) {
		String sql = "update t_customers set cname = ?, phone = ?, email = ?, age = ? where customerId = ?";
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(5, customer.getCustomerId());
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getPhone());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setInt(4, customer.getAge());
			int result = preparedStatement.executeUpdate();
			closeConnection(connection);
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int customerId) {
		String sql = "delete from t_customers where customerId = ?";
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			int result = preparedStatement.executeUpdate();
			closeConnection(connection);
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public synchronized Customer get(int customerId) {
		String sql = "select * from t_customers where customerId = ?";
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet  = preparedStatement.executeQuery();
			resultSet.next();
			Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getInt(5));
			resultSet.close();
			closeConnection(connection);
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6478281",
				"sql6478281", "A2xpty7RpI");
		return connection;
	}
	
	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
