package com.axon.bank.apis.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ApproveBankAccountCommand {
	
	@TargetAggregateIdentifier
	private String accountId;
	private String status;
	
	public ApproveBankAccountCommand() {
		
	}
	
	public ApproveBankAccountCommand(String accountId, String status) {
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
