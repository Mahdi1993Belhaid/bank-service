package com.belhaid.mahdi.bankservice.query.repositories;

import com.belhaid.mahdi.bankservice.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
}
