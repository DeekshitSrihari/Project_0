package com.revature.iba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.iba.model.Customers;
import com.revature.iba.util.DbConnection;

public class CustomersDAOImpl implements CustomersDAO{
	
		Connection connection=DbConnection.getDbConnection();
	Customers customers=new Customers();
	
		private String ADD_CUSTOMER_DETAILS="insert into hr.customers values(?,?,?,?,?,?,?)";
	private String MAKE_DEPOSIT="update hr.customers set accountBalance=accountBalance+? where accountNumber=? ";
	private String MAKE_WITHDRAWAL="update hr.customers set accountBalance=accountBalance-? where accountNumber=? ";
	private String VALIDATE_CUSTOMER="select customerUserName,customerPassword from hr.customers where customerUserName=? and customerPassword=? ";
	private String LOAD_ALL_CUSTOMER_DETAILS="select * from hr.customers where customerUserName=?";
	
		public boolean addCustomer(Customers customers) {
				int res=0;
		try {
			PreparedStatement statement=connection.prepareStatement(ADD_CUSTOMER_DETAILS);
			statement.setLong(1, customers.getCustomerCifId());
			statement.setString(2, customers.getCustomerFirstName());
			statement.setString(3, customers.getCustomerLastName());
			statement.setString(4, customers.getCustomerUserName());
			statement.setString(5, customers.getCustomerPassword());
			statement.setLong(6, customers.getAccountNumber());
			statement.setLong(7, customers.getAccountBalance());
			 res=statement.executeUpdate();
		} catch (SQLException e) {
						System.out.println("You entered wrong Details(UserName already exist), Please try again");
		}
		if(res==0)
			return false;
		else
			return true;
	}

		public Customers makeWithdrawal(Customers customers,int debitAmount) {
			int res=0;
		try {
			PreparedStatement statement=connection.prepareStatement(MAKE_WITHDRAWAL);
			statement.setInt(1, debitAmount);
			statement.setLong(2, customers.getAccountNumber());
			res=statement.executeUpdate();
		} catch (SQLException e) {
						e.printStackTrace();
		}
		if(res==0)
			return null;
		else {
			customers=loadCustomerDetails(customers, customers.getCustomerUserName());
			return customers;
		}
			
	}

		public Customers makeDeposit(Customers customers,int creditAmount) {
				int res=0;
		try {
			PreparedStatement statement=connection.prepareStatement(MAKE_DEPOSIT);
			statement.setInt(1, creditAmount);
			statement.setLong(2, customers.getAccountNumber());
			res=statement.executeUpdate();
		} catch (SQLException e) {
						System.out.println("You Entered Invalid amount, Please try again");
		}
		if(res==0)
			return null;
		else 
			customers=loadCustomerDetails(customers, customers.getCustomerUserName());
			return customers;
	}
		public Customers makeMoneyTransfer(Customers customers,Long customerAccountNumber,int amount) {
				
		int credit=0;
		try {
			PreparedStatement statement=connection.prepareStatement(MAKE_DEPOSIT);
			statement.setInt(1, amount);
			statement.setLong(2, customerAccountNumber);
			credit = statement.executeUpdate();
			
			customers = makeWithdrawal(customers,amount);
		} catch (SQLException e) {
			
			System.out.println("Please enter a valid account Number to transfer");
		}
		
		if(credit !=0 && customers!=null)
		{
			customers=loadCustomerDetails(customers, customers.getCustomerUserName());
			return customers;
		}
		else {
			customers=makeDeposit(customers, amount);
			return null;
		}
	}

		public void aboutApp() {
				System.out.println("Will be updated soon");
		
	}

		public Customers validateCustomer(String customerUserName,String cutomerPassword) {
				ResultSet res;
		int res1=1;
		try {
			PreparedStatement statement=connection.prepareStatement(VALIDATE_CUSTOMER);
			statement.setString(1, customerUserName);
			statement.setString(2, cutomerPassword);
			res=statement.executeQuery();
			if(res.next())
				res1=1;
			else 
				res1=0;
		} catch (SQLException e) {
						System.out.println("You entered Wrong UserName or Password, Please try again");
		}
		if(res1==1) {
			customers=loadCustomerDetails(customers,customerUserName);
			return customers;
		}
		else {
			return null;
		}
	}
		public Customers loadCustomerDetails(Customers customers,String customerUserName) {
				ResultSet res=null;
		try {
			PreparedStatement statement=connection.prepareStatement(LOAD_ALL_CUSTOMER_DETAILS);
			statement.setString(1, customerUserName);
			res=statement.executeQuery();
			if(res.next()) {
			 customers=new Customers(res.getLong(1),res.getString(2),
					res.getString(3),res.getString(4),res.getString(5),res.getLong(6),res.getInt(7));
			}
		} catch (SQLException e) {
						e.printStackTrace();
		}
		
		return customers;
		
	}

		public boolean deleteCustomer(int accountNumber) {
				int res=0;
		try {
			PreparedStatement statement = connection.prepareStatement("delete from iba.customers where accountNumber=?");
			statement.setInt(1, accountNumber);
			res=statement.executeUpdate();
		} catch (SQLException e) {
						e.printStackTrace();
		}
		if(res==0)
			return false;
		else
			return true;
	}


}
