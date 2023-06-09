package com.belhaid.mahdi.bankservice.commands.aggregates;

import com.belhaid.mahdi.bankservice.commandsapi.commands.CreateAccountCommand;
import com.belhaid.mahdi.bankservice.commandsapi.commands.CreditAccountCommand;
import com.belhaid.mahdi.bankservice.commandsapi.commands.DebitAccountCommand;
import com.belhaid.mahdi.bankservice.commandsapi.enums.AccountStatus;
import com.belhaid.mahdi.bankservice.commandsapi.events.AccountCreatedEvent;
import com.belhaid.mahdi.bankservice.commandsapi.events.AccountCreditedEvent;
import com.belhaid.mahdi.bankservice.commandsapi.events.AccountDebitedEvent;
import com.belhaid.mahdi.bankservice.commandsapi.exceptions.InsufficientBalanceException;
import com.belhaid.mahdi.bankservice.commandsapi.exceptions.LimitExceededException;
import com.belhaid.mahdi.bankservice.commandsapi.exceptions.NegativeValueException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Value;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private  String accountId;
    private String currency;
    private Double balance;
    private AccountStatus status;
    @Value(value = "${rules.withdrawal.limit}")
    private double maxLimit;

    public  AccountAggregate(){}

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command){
        if(command.getInitialBalance()<0) throw new NegativeValueException("the balance is negative");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getAccountId(),
                 command.getInitiatorId(),
                command.getCurrency(),
                command.getInitialBalance(),
                AccountStatus.CREATED
        ));

    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
      this.accountId=event.getAccountId();
      this.balance=event.getBalance();
      this.currency=event.getCurrency();
      this.status=event.getStatus();
    }
    @CommandHandler
    public void handle(CreditAccountCommand command){
      if(command.getAmount()<0) throw new NegativeValueException("the amount is negative");
        if(command.getAmount()>maxLimit) throw new LimitExceededException("amount depassed limit");
      AggregateLifecycle.apply(new AccountCreditedEvent(
              command.getAccountId(),

              command.getCurrency(),
              command.getAmount()
      ));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.balance+=event.getAmount();
    }

    @CommandHandler
    public void handle(DebitAccountCommand command){
        if(command.getAmount()<0) throw new NegativeValueException("the amount is negative");
        if(this.balance<command.getAmount()) throw new InsufficientBalanceException("insufficient balance");
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getAccountId(),

                command.getCurrency(),
                command.getAmount()
        ));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.balance-=event.getAmount();
    }


}
