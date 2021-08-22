package com.revature.iba.dao;

import java.util.Scanner;

import com.revature.iba.model.Customers;

// Customers Interface containing all the methods
public interface CustomersDAO {
	public boolean addCustomer(Customers customers);
	public Customers makeWithdrawal(Customers customers,int debitAmount);
	public Customers makeDeposit(Customers customers,int creditAmount);
	public Customers makeMoneyTransfer(Customers customers,Long customerAccountNumber,int amount);
	public void aboutApp();
	public Customers loadCustomerDetails(Customers customers,String customerUserName);
	public Customers validateCustomer(String customerUserName,String customerPassword);
	//for testing purpose
	public boolean deleteCustomer(int accountNumber);
}
