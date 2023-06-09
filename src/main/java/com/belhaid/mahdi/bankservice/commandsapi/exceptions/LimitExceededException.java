package com.belhaid.mahdi.bankservice.commandsapi.exceptions;

public class LimitExceededException extends RuntimeException{
    public LimitExceededException(){
        super();
    }

    public LimitExceededException(String message){
        super(message);
    }
    public LimitExceededException(Throwable cause){
        super(cause);
    }

    public LimitExceededException(String message, Throwable cause){
        super(message,cause);
    }
}
