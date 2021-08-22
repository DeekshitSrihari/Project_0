package com.revature.iba.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.iba.model.Customers;
import com.revature.iba.model.Employee;

public class EmployeeDAOTest {

	Employee employee; 
	EmployeeDAO employeeDAO=new EmployeeDAOImpl();
	CustomersDAO customersDAO=new CustomersDAOImpl();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		employee=new Employee();
	}
	@After
	public void tearDown() throws Exception {
		employee=null;
	}

	@Test
	public void testViewAccountBalance() {
		//added , get the balance and cross checked with actual balance
		Customers customers=new Customers((long) -999,"zyzf","lhyf","Deodj","klmn",-6465,560);
		customersDAO.addCustomer(customers);
		int actualBalance=employeeDAO.viewAccountBalance(-6465);
		assertEquals(actualBalance, 560);
		customersDAO.deleteCustomer(-6465);
	}

	@Test
	public void testGetCustomerDetails() {
		//added , customer details retrieved and cross checked with actual data
		Customers customers=new Customers((long) -999,"zyzf","lhyf","Deodj","klmn",-6465,560);
		customersDAO.addCustomer(customers);
		customers=employeeDAO.getCustomerDetails(-6465);
		assertEquals(customers.getAccountBalance(), 560);
		customersDAO.deleteCustomer(-6465);
	}

	@Test
	public void testValidateEmployee() {
		// get the employee data , validated (returns full name if validated )and cross checked with the full name
		Employee employee=new Employee(953761,"Bharath Sai","Maddela","BharathSaiM","Bharath789");
		String employeeName=employeeDAO.validateEmployee("BharathSaiM", "Bharath789");
		String expectedName="Bharath Sai"+" "+"Maddela";
		assertEquals(employeeName,expectedName);
	}

	@Test
	public void testIsCustomerExist() {
		//added , called isCustomerExist(returns firstName if customer exists) and and cross checked with actual firstName
		Customers customers=new Customers((long) -999,"zyzf","lhyf","Deodj","klmn",-6465,560);
		customersDAO.addCustomer(customers);
		String customerName=employeeDAO.isCustomerExist(-6465);
		assertEquals(customerName, "zyzf");
		customersDAO.deleteCustomer(-6465);
	}

}
