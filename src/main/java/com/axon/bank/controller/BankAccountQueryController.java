package com.axon.bank.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axon.bank.apis.queries.BankAccountEntity;
import com.axon.bank.apis.queries.FindAllAccounts;

@RestController
public class BankAccountQueryController {

	private final QueryGateway queryGateway;

    public BankAccountQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    
    @GetMapping(value="/accounts", produces = "application/json")
    public CompletableFuture<List<BankAccountEntity>> getAllAccounts() {
        
        return queryGateway.query(new FindAllAccounts(), ResponseTypes.multipleInstancesOf(BankAccountEntity.class));
    }
}
