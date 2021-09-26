package com.axon.bank.query.handler;

import java.util.List;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.axon.bank.apis.events.AddressUpdated;
import com.axon.bank.apis.events.AmountCredited;
import com.axon.bank.apis.events.AmountDebited;
import com.axon.bank.apis.events.BankAccountApproved;
import com.axon.bank.apis.events.BankAccountCreated;
import com.axon.bank.apis.queries.BankAccountEntity;
import com.axon.bank.apis.queries.FindAllAccounts;

@Component
@ProcessingGroup("account")
public class AccountQueryHandler {

	private AccountEntityRepository accountEntityRepository;
	
	public AccountQueryHandler(AccountEntityRepository accountEntityRepository) {
		this.accountEntityRepository = accountEntityRepository;
	}
	
	@EventHandler
	void on(BankAccountCreated event) {
		BankAccountEntity entity = new BankAccountEntity(
				event.getAccountId(),
				event.getUsername(),
				event.getFirstName(),
				event.getLastName(),
				event.getAddress(),
				event.getType(),
				event.getStatus(),
				event.getComment(),
				0);
		accountEntityRepository.save(entity);
	}
	
	@EventHandler
	void on(AmountCredited event) {
		BankAccountEntity entity = accountEntityRepository.getById(event.getAccountId());
		entity.setBalance(entity.getBalance() + event.getAmount());
	}
	
	@EventHandler
	void on(AmountDebited event) {
		BankAccountEntity entity = accountEntityRepository.getById(event.getAccountId());
		entity.setBalance(entity.getBalance() - event.getAmount());
	}
	
	@EventHandler
	void on(AddressUpdated event) {
		BankAccountEntity entity = accountEntityRepository.getById(event.getAccountId());
		entity.setAddress(event.getAddress());
	}
	
	@EventHandler
	void on(BankAccountApproved event) {
		BankAccountEntity entity = accountEntityRepository.getById(event.getAccountId());
		entity.setStatus(event.getStatus());
	}
	
    @QueryHandler
    public List<BankAccountEntity> handle(FindAllAccounts query) {
    	return accountEntityRepository.findAll();
    }
}
