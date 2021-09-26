package com.axon.bank.handler;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

import com.axon.bank.apis.commands.ApproveBankAccountCommand;
import com.axon.bank.apis.commands.CreateBankAccountCommand;
import com.axon.bank.apis.commands.CreditAmountCommand;
import com.axon.bank.apis.commands.DebitAmountCommand;
import com.axon.bank.apis.commands.UpdateAddressCommand;
import com.axon.bank.apis.events.AddressUpdated;
import com.axon.bank.apis.events.AmountCredited;
import com.axon.bank.apis.events.AmountDebited;
import com.axon.bank.apis.events.BankAccountApproved;
import com.axon.bank.apis.events.BankAccountCreated;
import com.axon.bank.apis.exceptions.LowBalanceException;

@Profile("command")
@Aggregate(snapshotTriggerDefinition = "bankAccountSnapshotTrigger")
@SuppressWarnings("unused")
public class BankAccountAggregate {

	@AggregateIdentifier
	private String accountId;
	private String address;
	private String status;
	private int balance;
	
	@AggregateMember
	List<Transaction> transactions = new ArrayList<Transaction>();
	
	@CommandHandler
	public BankAccountAggregate(CreateBankAccountCommand command) {
		if(command.getFirstName() == null 
				|| command.getLastName() == null
				|| command.getUsername() == null) {
			throw new IllegalArgumentException("Missing details.");
		}

		apply(new BankAccountCreated(command.getAccountId(),
				command.getUsername(),
				command.getFirstName(),
				command.getLastName(),
				command.getAddress(),
				command.getType(),
				command.getStatus()));
	}
	
	@CommandHandler
	public void handle(CreditAmountCommand command) {
		if(command.getAmount() <= 0) {
			throw new IllegalArgumentException("Transaction amount should be greater than 0");
		}
		apply(new AmountCredited(command.getTransactionId(), command.getAccountId(), command.getAmount(), command.getDescription()));
	}
	
	@CommandHandler
	public void handle(DebitAmountCommand command) {
		System.out.println(calculateBalance());
		if(calculateBalance() < Math.abs(command.getAmount()) ) {
			throw new LowBalanceException(command.getAccountId());
		}
		apply(new AmountDebited(command.getTransactionId(), command.getAccountId(), command.getAmount(), command.getDescription()));
	}
	
	@CommandHandler
	public void handle(UpdateAddressCommand command) {
		apply(new AddressUpdated(command.getAccountId(),
				command.getAddress()));
	}
	
	@CommandHandler
	public void handle(ApproveBankAccountCommand command) {
		apply(new BankAccountApproved(command.getAccountId(),
				command.getStatus()));
	}

    @EventSourcingHandler
    public void on(BankAccountCreated event) {
    	this.accountId = event.getAccountId();
    	this.address = event.getAddress();
    }
    
    @EventSourcingHandler
    public void on(AmountCredited event) {
    	this.accountId = event.getAccountId();
    	this.transactions.add(new Transaction(event.getTransactionId(),
    			event.getAccountId(), event.getAmount(), event.getDescription()));
    	this.balance += event.getAmount();
    }
    
    @EventSourcingHandler
    public void on(AmountDebited event) {
    	this.accountId = event.getAccountId();
    	this.transactions.add(new Transaction(event.getTransactionId(),
    			event.getAccountId(), event.getAmount(), event.getDescription()));
    	this.balance += event.getAmount();
    }
    
    @EventSourcingHandler
    public void on(AddressUpdated event) {
    	this.accountId = event.getAccountId();
    	this.address = event.getAddress();
    }
    
    @EventSourcingHandler
    public void on(BankAccountApproved event) {
    	this.accountId = event.getAccountId();
    	this.status = event.getStatus();
    }
    public BankAccountAggregate() {
		
	}
    
    private int calculateBalance() {
    	return transactions.stream().map(t-> t.getAmount()).reduce(0, Integer::sum);
    }
}
