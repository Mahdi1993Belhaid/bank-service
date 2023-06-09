package com.belhaid.mahdi.bankservice.commandsapi.exceptions;

public class InsufficientBalanceException extends  RuntimeException {
    public InsufficientBalanceException(){
        super();
    }

    public InsufficientBalanceException(String message){
        super(message);
    }
    public InsufficientBalanceException(Throwable cause){
        super(cause);
    }

    public InsufficientBalanceException(String message, Throwable cause){
        super(message,cause);
    }
}
