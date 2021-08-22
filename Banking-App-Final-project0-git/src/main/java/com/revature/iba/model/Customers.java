package com.revature.iba.model;

public class Customers {
	private Long customerCifId;
	private String customerFirstName;
	private String customerLastName;
	private String customerUserName;
	private String customerPassword;
	private long accountNumber;
	private long accountBalance;
	
	//Default Constructor
	public Customers() {
		// TODO Auto-generated constructor stub
	}
	
	//Parameterized Constructor
	public Customers(Long customerCifId, String customerFirstName, String customerLastName, String customerUserName,
			String customerPassword, long accountNumber, long accountBalance) {
		super();
		this.customerCifId = customerCifId;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerUserName = customerUserName;
		this.customerPassword = customerPassword;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}
	//Getter Setter Methods
	public Long getCustomerCifId() {
		return customerCifId;
	}

	public void setCustomerCifId(Long customerCifId) {
		this.customerCifId = customerCifId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerUserName() {
		return customerUserName;
	}

	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(long accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "Customers [customerCifId=" + customerCifId + ", customerFirstName=" + customerFirstName
				+ ", customerLastName=" + customerLastName + ", customerUserName=" + customerUserName
				+ ", customerPassword=" + customerPassword + ", accountNumber=" + accountNumber + ", accountBalance="
				+ accountBalance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountBalance ^ (accountBalance >>> 32));
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + ((customerCifId == null) ? 0 : customerCifId.hashCode());
		result = prime * result + ((customerFirstName == null) ? 0 : customerFirstName.hashCode());
		result = prime * result + ((customerLastName == null) ? 0 : customerLastName.hashCode());
		result = prime * result + ((customerPassword == null) ? 0 : customerPassword.hashCode());
		result = prime * result + ((customerUserName == null) ? 0 : customerUserName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customers other = (Customers) obj;
		if (accountBalance != other.accountBalance)
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (customerCifId == null) {
			if (other.customerCifId != null)
				return false;
		} else if (!customerCifId.equals(other.customerCifId))
			return false;
		if (customerFirstName == null) {
			if (other.customerFirstName != null)
				return false;
		} else if (!customerFirstName.equals(other.customerFirstName))
			return false;
		if (customerLastName == null) {
			if (other.customerLastName != null)
				return false;
		} else if (!customerLastName.equals(other.customerLastName))
			return false;
		if (customerPassword == null) {
			if (other.customerPassword != null)
				return false;
		} else if (!customerPassword.equals(other.customerPassword))
			return false;
		if (customerUserName == null) {
			if (other.customerUserName != null)
				return false;
		} else if (!customerUserName.equals(other.customerUserName))
			return false;
		return true;
	}
	

}
