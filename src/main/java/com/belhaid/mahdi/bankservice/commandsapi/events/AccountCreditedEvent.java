package com.belhaid.mahdi.bankservice.commandsapi.events;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AccountCreditedEvent{
    @Getter private String accountId;

    @Getter  private String currency;
    @Getter private  Double amount ;
}
