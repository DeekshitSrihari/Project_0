package com;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.iba.dao.CustomersDAO;
import com.revature.iba.dao.CustomersDAOImpl;
import com.revature.iba.dao.EmployeeDAO;
import com.revature.iba.dao.EmployeeDAOImpl;
import com.revature.iba.model.Customers;

public class EmployeePage {
	Logger logger=Logger.getLogger("Employee Dashboard Page");
	EmployeeDAO employeeDAO=new EmployeeDAOImpl();
	Customers customers=new Customers();
	CustomersDAO customersDAO=new CustomersDAOImpl();
	
	public void employeeDashboard(String employeeFirstName) {
		Scanner sc=new Scanner(System.in);

		logger.info("Employee "+employeeFirstName+" logged in successfully");

		System.out.println(employeeFirstName+" you successfully Logged in");
		while(true) {
			System.out.println(" Insight Banking App ");
			System.out.println("Please Enter Your Choice");
			System.out.println("1.Check Account Balance for customer");
			System.out.println("2.Approve or Deny account");
			System.out.println("3.View Customer Bank Accounts");
			System.out.println("4.Get Customer Details");
			System.out.println("5.View log of all accounts");
			System.out.println("9.Logout");
			int choice = 0;
			choice =sc.nextInt();
			switch(choice) {
			case 1:

				logger.info("Employee Entered check Balance section");

				System.out.println("Enter the account number of customer");int customerAccountNumber=sc.nextInt();
				String customerName=employeeDAO.isCustomerExist(customerAccountNumber);
				
				if (customerName!=null) {
					customers=customersDAO.loadCustomerDetails(customers, employeeFirstName);
					int accountBalance = employeeDAO.viewAccountBalance(customerAccountNumber);
					if (accountBalance != -1) {

						logger.info(customerName+" balance retrieved i.e., "+accountBalance);

						System.out.println(customerName+" Your Balance is " + accountBalance);
					}
					break;
				}
				else{

					logger.error("Customer does not exist i.e., Don't have an account yet");

					
					System.out.println("Customer with "+customerAccountNumber+" does not exist");
					break;
				}
			case 2:

				logger.info("Employee Entered Approve or Deny Account section");

				System.out.println("Enter customer account Number "); int accountNumberr=sc.nextInt();
				customers=employeeDAO.getCustomerDetails(accountNumberr);
				if(customers!=null) {
					Long cifId=customers.getCustomerCifId();
					if (cifId>=10000 && cifId<=99999 ) {
	
						logger.info("Employee Retrieved customer details i.e.," + customers);
	
						System.out.println("The customer Details are :");
						System.out.println(customers);
						System.out.println("Approve or not(1 for Yes/ 0 for No)");
						int value = sc.nextInt();
						if (value == 1) {
							boolean res = employeeDAO.approveAccount(accountNumberr);
							if (res) {
			
								logger.info("Employee Approved the customer with account Number"+accountNumberr);
			
								System.out.println("customer with account Number " + accountNumberr + " Approved successfully");
							}
						} else {
			
							logger.info("Employee Denied the customer with account Number"+accountNumberr);
			
							System.out.println("customer with accountNumber " + accountNumberr + " denied successfully");
						}
						break;
					}
					else {
	
						logger.fatal("customer already approved with account number"+accountNumberr);
	
						System.out.println("customer with account Number "+ accountNumberr+" Already Approved");
						break;
					}
				}
				else {

					logger.info("account number does not exist");

					System.out.println("please enter valid account Number");
					break;
				}
			case 3:

				logger.info("Employee viewed customer bank accounts");

				System.out.println("All customer Bank  Account Details are");
				employeeDAO.getCustomerBankAccounts();
				break;
			case 4:

				logger.info("Employee entered Get customer Details section ");

				System.out.println("Please enter the customer account Number"); int accountNumber=sc.nextInt();
				customers=employeeDAO.getCustomerDetails(accountNumber);
				if(customers!=null) {

					logger.info("Employee Retrieved customer details i.e.,"+customers);

					System.out.println(customers);
					break;
				}
				else {

					logger.info("account number does not exist");

					System.out.println("please enter valid account Number");
					break;
				}
				
				
			case 5:
				try{
					   FileInputStream fstream = new FileInputStream("InsighBankingLog.log");
					   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
					   String strLine;
					   /* read log line by line */
					   while ((strLine = br.readLine()) != null)   {
					     /* parse strLine to obtain what you want */
					     System.out.println (strLine);
					   }
					   fstream.close();
					} catch (Exception e) {
					     System.err.println("Error: " + e.getMessage());
					}
				break;
			case 9:

				logger.info("Employee entered Logged out successfully ");

				System.out.println(employeeFirstName+" You successfully Logged out");
				InsightApp insightApp=new InsightApp();
				insightApp.startInsightApp();
				break;
			default:

				logger.info("Employee chosen invalid option ");

				System.out.println("you entered Invalid choice,  Please try again");
				break;
			}
		}
	}
}
