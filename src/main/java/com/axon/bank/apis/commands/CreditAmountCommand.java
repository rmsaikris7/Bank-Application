package com.axon.bank.apis.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreditAmountCommand {

	@TargetAggregateIdentifier
	private String accountId;
	private String transactionId;
	private int amount;
	private String description;
	
	public CreditAmountCommand() {
		
	}
	
	public CreditAmountCommand(String transactionId, String accountId, int amount, String description) {
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
