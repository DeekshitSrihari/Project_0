package com.revature.iba.dao;

import com.revature.iba.model.Customers;
//import com.revature.iba.model.Employee;

//Employee Interface containing all the methods
public interface EmployeeDAO {
	public int viewAccountBalance(int accountNumber);
	public void getCustomerBankAccounts();
	public Customers getCustomerDetails(int accountNumber);
	public String validateEmployee(String employeeUserName,String employeePassword);
	public String isCustomerExist(int accountNumber);
	public boolean approveAccount(int accountNumber);
}
