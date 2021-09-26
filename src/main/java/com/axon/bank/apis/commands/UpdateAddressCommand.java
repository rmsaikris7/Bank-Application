package com.axon.bank.apis.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class UpdateAddressCommand {
	
	@TargetAggregateIdentifier
	private String accountId;
	private String firstName;
	private String lastName;
	private String address;
	public UpdateAddressCommand() {
		
	}
	public UpdateAddressCommand(String accountId, String address) {
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
