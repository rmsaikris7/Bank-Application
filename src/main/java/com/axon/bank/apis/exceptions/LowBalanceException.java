package com.axon.bank.apis.exceptions;

public class LowBalanceException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8250282371276797862L;

	public LowBalanceException(String accountId) {
		super("Account balance is too low to debit for account [" + accountId + "]");
	}
}
