package com.axon.bank.apis.events;

public class BankAccountApproved {

	private String accountId;
	private String status;
	public BankAccountApproved(String accountId, String status) {
		super();
		this.accountId = accountId;
		this.status = status;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
