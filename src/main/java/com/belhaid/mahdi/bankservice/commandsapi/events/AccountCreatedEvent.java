package com.belhaid.mahdi.bankservice.commandsapi.events;

import com.belhaid.mahdi.bankservice.commandsapi.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AccountCreatedEvent{
    @Getter private String accountId;
    @Getter private String ClientId;
    @Getter private String currency;
    @Getter private Double balance;
    @Getter private AccountStatus status;
}
