package com.belhaid.mahdi.bankservice.commands.controllers;

import com.belhaid.mahdi.bankservice.commandsapi.commands.CreditAccountCommand;
import com.belhaid.mahdi.bankservice.commandsapi.commands.DebitAccountCommand;
import com.belhaid.mahdi.bankservice.commandsapi.dto.CreateAccountRequest;
import com.belhaid.mahdi.bankservice.commandsapi.commands.CreateAccountCommand;
import com.belhaid.mahdi.bankservice.commandsapi.dto.CreditAccountRequestDTO;
import com.belhaid.mahdi.bankservice.commandsapi.dto.DebitAccountRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class AccountCommandController {



    private final CommandGateway commandGateway;

    @PostMapping("/command/account")
    public CompletableFuture<String> createNewAccount(@RequestBody  CreateAccountRequest request){
       return commandGateway.send(new CreateAccountCommand(
               UUID.randomUUID().toString(),
               request.initiatorId(),
               request.currency(),
               request.initialBalance()
       ));
    }

    @PostMapping("/account/debit")
    public CompletableFuture<String> debitAccount(@RequestBody  DebitAccountRequestDTO request){
     return commandGateway.send(new DebitAccountCommand(
         request.accountId(),

         request.currency(),
         request.amount()
     ));
    }

    @PostMapping("/account/credit")
    public CompletableFuture<String> creditAccount(@RequestBody  CreditAccountRequestDTO request){
        return commandGateway.send(new CreditAccountCommand(
                request.accountId(),

                request.currency(),
                request.amount()
        ));
    }

}
