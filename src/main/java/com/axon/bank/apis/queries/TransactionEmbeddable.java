package com.axon.bank.apis.queries;

import javax.persistence.Embeddable;

@Embeddable
public class TransactionEmbeddable {

	private String transactionId;
	private String accountId;
	private String amount;
	private String description;
	public TransactionEmbeddable(String transactionId, String accountId, String amount, String description) {
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
