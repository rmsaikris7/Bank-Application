package com.axon.bank.apis.events;

public class AddressUpdated {

	private String accountId;
	private String address;
	public AddressUpdated(String accountId, String address) {
		super();
		this.accountId = accountId;
		this.address = address;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
