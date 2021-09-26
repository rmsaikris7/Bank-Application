package com.axon.bank.query.handler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axon.bank.apis.queries.BankAccountEntity;

@Repository
public interface AccountEntityRepository extends JpaRepository<BankAccountEntity, String>{

}
