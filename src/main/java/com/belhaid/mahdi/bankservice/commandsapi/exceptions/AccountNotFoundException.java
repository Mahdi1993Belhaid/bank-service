package com.belhaid.mahdi.bankservice.commandsapi.exceptions;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(){
        super();
    }

    public AccountNotFoundException(String message){
        super(message);
    }
    public AccountNotFoundException(Throwable cause){
        super(cause);
    }

    public AccountNotFoundException(String message, Throwable cause){
        super(message,cause);
    }
}
