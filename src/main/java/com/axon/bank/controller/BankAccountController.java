package com.axon.bank.controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.axon.bank.apis.commands.ApproveBankAccountCommand;
import com.axon.bank.apis.commands.CreateBankAccountCommand;
import com.axon.bank.apis.commands.CreditAmountCommand;
import com.axon.bank.apis.commands.DebitAmountCommand;
import com.axon.bank.apis.commands.UpdateAddressCommand;
import com.axon.bank.dto.BankAccount;
import com.axon.bank.handler.Transaction;

@RestController
public class BankAccountController {

    private final CommandGateway commandGateway;

    public BankAccountController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(value="/account", consumes = "application/json")
    public CompletableFuture<Void> createAccount(@RequestBody BankAccount account) {
        String accountId = UUID.randomUUID().toString();
        
        return commandGateway.send(new CreateBankAccountCommand(accountId,
        		account.getUsername(),
        		account.getFirstName(),
        		account.getLastName(),
        		account.getAddress(),
        		account.getType(), account.getStatus()));
    }
    
    @PutMapping(value="/account/{id}", consumes = "application/json")
    public CompletableFuture<Void> updateAccount(@PathVariable("id") String accountId, @RequestBody BankAccount account) {
        
        return commandGateway.send(new UpdateAddressCommand(accountId,
        		account.getAddress()));
    }
    
    @PutMapping(value="/account/{id}/approve", consumes = "application/json")
    public CompletableFuture<Void> approveAccount(@PathVariable("id") String accountId) {
        String status = "approved";
        String transactionId = UUID.randomUUID().toString();
        return commandGateway.send(new ApproveBankAccountCommand(accountId,
        		status))
        		.thenCompose(result -> commandGateway.send(new CreditAmountCommand(transactionId, accountId, 100, "sign up money")));
    }
    
    @PostMapping(value="/account/{id}/credit", consumes = "application/json")
    public CompletableFuture<Void> creditMoney(@PathVariable("id") String accountId, @RequestBody Transaction transaction) {
        String transactionId = UUID.randomUUID().toString();
        
        return commandGateway.send(new CreditAmountCommand(transactionId, accountId, transaction.getAmount(), "something"));
    }
    
    @PostMapping(value="/account/{id}/debit", consumes = "application/json")
    public CompletableFuture<Void> debitMoney(@PathVariable("id") String accountId, @RequestBody Transaction transaction) {
        String transactionId = UUID.randomUUID().toString();
        
        return commandGateway.send(new DebitAmountCommand(transactionId, accountId, Math.negateExact(transaction.getAmount()), "sample description"));
    }

   
}
