package com.axon.bank.apis.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DebitAmountCommand {

	private String transactionId;
	
	@TargetAggregateIdentifier
	private String accountId;
	private int amount;
	private String description;
	
	public DebitAmountCommand() {
		
	}
	
	public DebitAmountCommand(String transactionId, String accountId, int amount, String description) {
		super();
		this.accountId = accountId;
		this.transactionId = transactionId;
		this.amount = amount;
		this.description = description;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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