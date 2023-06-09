package com.belhaid.mahdi.bankservice.query.repositories;

import com.belhaid.mahdi.bankservice.query.entities.Account;
import com.belhaid.mahdi.bankservice.query.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {

    public List<Operation>findByAccount(Account account);
}
