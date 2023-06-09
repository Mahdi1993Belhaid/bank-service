package com.belhaid.mahdi.bankservice.query.services;

import com.belhaid.mahdi.bankservice.commandsapi.enums.TransactionType;
import com.belhaid.mahdi.bankservice.commandsapi.events.AccountCreatedEvent;
import com.belhaid.mahdi.bankservice.commandsapi.events.AccountCreditedEvent;
import com.belhaid.mahdi.bankservice.commandsapi.events.AccountDebitedEvent;
import com.belhaid.mahdi.bankservice.commandsapi.exceptions.AccountNotFoundException;
import com.belhaid.mahdi.bankservice.query.entities.Account;
import com.belhaid.mahdi.bankservice.query.entities.Operation;
import com.belhaid.mahdi.bankservice.query.queries.GetAllAccounts;
import com.belhaid.mahdi.bankservice.query.queries.GetAllOperations;
import com.belhaid.mahdi.bankservice.query.repositories.AccountRepository;
import com.belhaid.mahdi.bankservice.query.repositories.OperationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountEventHandlerService {
    private final AccountRepository accountRepository;
    private final OperationRepository transactionRepository;


    @EventHandler
    public void on(AccountCreatedEvent event,EventMessage<AccountCreatedEvent> eventMessage){
        log.info("account is received");
        Account account = Account.builder()
                .accountId(eventMessage.getPayload().getAccountId())
                .currency(eventMessage.getPayload().getCurrency())
                .clientId(eventMessage.getPayload().getClientId())
                .status(eventMessage.getPayload().getStatus())
                .createdAt(eventMessage.getTimestamp())
                .build();
        accountRepository.save(account);
    }
    @EventHandler
    public void on(AccountCreditedEvent event,EventMessage<AccountCreditedEvent>eventMessage){
        Account account=accountRepository
                .findById(event.getAccountId())
                .orElseThrow(()->new AccountNotFoundException("the account is not exist"));
        Operation operation = Operation.builder()
                .account(account)
                .type(TransactionType.CREDIT)
                .transactionDate(eventMessage.getTimestamp())
                .build();
        transactionRepository.save(operation);
    }

    @EventHandler
    public void on(AccountDebitedEvent event, EventMessage<AccountDebitedEvent>eventMessage){
        Account account=accountRepository
                .findById(event.getAccountId())
                .orElseThrow(()->new AccountNotFoundException("the account is not exist"));
        Operation operation = Operation.builder()
                .account(account)
                .initiatorId(account.getClientId())
                .type(TransactionType.DEBIT)
                .transactionDate(eventMessage.getTimestamp())
                .build();
        transactionRepository.save(operation);
    }

    @QueryHandler
     public List<Account>on(GetAllAccounts accounts){
      return accountRepository.findAll();
     }

     @QueryHandler
    public List<Operation>on(GetAllOperations operations){
        Account account= accountRepository.findById(operations.getAccountId()).orElseThrow(()->new AccountNotFoundException("the account is not exist"));
       return transactionRepository.findByAccount(account);
     }

}
