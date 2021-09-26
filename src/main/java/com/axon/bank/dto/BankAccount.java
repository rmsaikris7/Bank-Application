package com.axon.bank.dto;

public class BankAccount {

	private String accountId;
	private String username;
	private String firstName;
	private String lastName;
	private String address;
	private String type;
	private String status;
	
	public BankAccount() {
		
	}
	
	public BankAccount(String accountId, String username, String firstName, String lastName,
			String address, String type, String status) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.type = type;
		this.status = status;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
