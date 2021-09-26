package com.axon.bank.handler;

import org.axonframework.modelling.command.EntityId;

public class Transaction {
	
	@EntityId
	private String transactionId;
	private String accountId;
	private int amount;
	private String description;
	
	public Transaction() {
		
	}
	
	public Transaction(String transactionId, String accountId, int amount, String description) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.amount = amount;
		this.description = description;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
