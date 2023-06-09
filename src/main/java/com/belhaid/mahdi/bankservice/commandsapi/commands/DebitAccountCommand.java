package com.belhaid.mahdi.bankservice.commandsapi.commands;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@EqualsAndHashCode
public class DebitAccountCommand {

    @TargetAggregateIdentifier @Getter private String accountId;

    @Getter private String currency;
    @Getter private Double amount;
}
