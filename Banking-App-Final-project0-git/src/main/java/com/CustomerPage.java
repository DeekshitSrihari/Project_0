package com;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.iba.dao.CustomersDAO;
import com.revature.iba.dao.CustomersDAOImpl;
import com.revature.iba.model.Customers;

public class CustomerPage {

	Logger logger=Logger.getLogger("Customer Dashboard Page");

	Customers customers=new Customers();
	Scanner sc=new Scanner(System.in);
	CustomersDAO customerDAO=new CustomersDAOImpl();
	
	public void customerDashboard(Customers customers) {
		String customerUserName=customers.getCustomerUserName();

		logger.info("customer entered into his dashboard with Details: "+customers);

		System.out.println("Dear " + customers.getCustomerFirstName() + " " + customers.getCustomerLastName()
		+ " You Successfully LoggedIn");
		while (true) {
			System.out.println("Insight Banking App");
			System.out.println("1.Check Account Balance");
			System.out.println("2.Deposit money");
			System.out.println("3.Withdrawal money");
			System.out.println("4.Transfer money");
			System.out.println("9.Logout");
			int choice = 0;
			choice =sc.nextInt();
			switch (choice) {
			case 1:

				logger.info("customer entered check account balance section");

								customers=customerDAO.loadCustomerDetails(customers, customerUserName);
				System.out.println(customers.getCustomerFirstName()+" Your account balance is :"+customers.getAccountBalance());
				break;
			case 2:

				logger.info("customer entered deposit money section");

				customers=customerDAO.loadCustomerDetails(customers, customerUserName);
				System.out.println("Enter the amount you want to deposit"); int creditAmount=sc.nextInt();
				if(creditAmount<=0) {	

					logger.fatal("customer entered negative value money or 0 i.e., "+creditAmount);

					System.out.println(", You Entered Invalid amount...Please try again");
					break;
				}
				else if(creditAmount<500) {

					logger.fatal("customer entred less than 500(can deposit from 500) i.e., "+creditAmount);

					System.out.println(", Minimum deposit is INR 500, Please try again");
					break;
				}
				else if(creditAmount>50000) {

					logger.fatal("customer entred greater than 50000(can deposit only less than 50000) i.e., "+creditAmount);

					System.out.println(", You can only deposit INR 50000 per day, Please try again");
					break;
				}
				else {
					customers = customerDAO.makeDeposit(customers, creditAmount);
										if (customers != null) {

						logger.info("customer deposited "+creditAmount + " successfully and new balance is "+ customers.getAccountBalance());

						System.out.println("your account deposited with amount INR " + creditAmount
								+ " and Your Account Account balance now is " + customers.getAccountBalance());
						break;
					} else

						logger.error("deposit failed ");

					System.out.println("Deposit failed...please try again");
					break;
				}
			case 3:

				logger.info("customer entered withdraw money section");

				customers=customerDAO.loadCustomerDetails(customers, customerUserName);
				System.out.println("Enter the amount you want to Withdraw"); int debitAmount=sc.nextInt();
				if(debitAmount<=0) {

					logger.fatal("customer entered negative value money or 0 i.e., "+debitAmount);

					System.out.println("You Entered Invalid amount, Please try again");
					break;
				}
				else if(debitAmount<500) {

					logger.fatal("customer entred less than 500(can withdraw from 500) i.e., "+debitAmount);

					System.out.println("Minimum withdrawal is INR 500, Please withdraw 500, Please try again");
					break;
				}
				else if(debitAmount>25000) {

					logger.fatal("customer entred greater than 25000(can withdraw only less than 25000) i.e., "+debitAmount);

					System.out.println(" You can only withdraw 25000 per Transaction, Please try again");
					break;
				}
				else {
					customers =customerDAO.makeWithdrawal(customers,debitAmount);
					if(customers!=null) {

						logger.info("customer withdrawn "+debitAmount + " successfully and new balance is "+ customers.getAccountBalance());

						System.out.println("INR "+debitAmount+" withdrawn successfully and your current Balance is "+customers.getAccountBalance());
						break;
					}
					else {

						logger.error("Withdraw failed ");

						System.out.println("Withdraw failed...");
					break;
					}
				}
				
			case 4:

				logger.info("Customer entered TRANSFER MONEY section ");

				customers=customerDAO.loadCustomerDetails(customers, customerUserName);
				System.out.println("Enter the account number you want to send money"); Long customerAccountNumber=sc.nextLong();
				System.out.println("Enter the amount you want to transfer"); int amount=sc.nextInt();
				if(amount<0) {

					logger.fatal("customer entred negative amount i.e., "+amount);

					System.out.println(", you entered Invalid Amount, Please try again");
					break;
				}
				else if(amount>30000) {

					logger.fatal("customer entred amount greater than 30000 (can't transfer >30000)i.e., "+amount);

					System.out.println(", you can only transfer upto INR 30000, Please try again");
					break;
				}
				else {
					customers=customerDAO.makeMoneyTransfer(customers,customerAccountNumber,amount);
					if(customers!=null) {

						logger.info("customer transfered INR  "+amount + " to "+customerAccountNumber+"  successfully and new balance is "+ customers.getAccountBalance());

						System.out.println("INR "+amount+" transfered to "+customerAccountNumber+" successfully and yout current Account balance is "+customers.getAccountBalance());
					}
					else {

						logger.error("transfer failed ");

						System.out.println("Transfer failed, Please enter a valid account Number to transfer...please try again");
					}
					break;
				}
				
			case 9:
				customers=customerDAO.loadCustomerDetails(customers, customerUserName);
				System.out.println(customers.getCustomerFirstName()+" You successfully logged out");

				logger.info("customer click on logout and logged out successfully ");

				InsightApp insightApp=new InsightApp();
				insightApp.startInsightApp();
				break;
			default:

				logger.fatal("customer chosen wrong option");

				System.out.println("Invalid Option..");

			}
		}
		
	}
}
