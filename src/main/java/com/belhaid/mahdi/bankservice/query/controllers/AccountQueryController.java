package com.belhaid.mahdi.bankservice.query.controllers;

import com.belhaid.mahdi.bankservice.query.entities.Account;
import com.belhaid.mahdi.bankservice.query.entities.Operation;
import com.belhaid.mahdi.bankservice.query.queries.GetAllAccounts;
import com.belhaid.mahdi.bankservice.query.queries.GetAllOperations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("query/accounts")
@RequiredArgsConstructor
public class AccountQueryController {

private final QueryGateway query ;
    @GetMapping
    public CompletableFuture<List<Account>> getAllAccounts(){
        return query.query(new GetAllAccounts(), ResponseTypes.multipleInstancesOf(Account.class));
    }

    @GetMapping("/operation/{accountId}")
    public CompletableFuture<List<Operation>> getAllOperationsByAccount(@Valid  @PathVariable String accountId){
        return query.query(new GetAllOperations(accountId) , ResponseTypes.multipleInstancesOf(Operation.class));
    }

}
