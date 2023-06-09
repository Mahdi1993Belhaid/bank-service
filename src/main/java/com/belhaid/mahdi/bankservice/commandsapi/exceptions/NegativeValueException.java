package com.belhaid.mahdi.bankservice.commandsapi.exceptions;

public class NegativeValueException extends  RuntimeException{

    public NegativeValueException(){
        super();
    }

    public NegativeValueException(String message){
        super(message);
    }
    public NegativeValueException(Throwable cause){
        super(cause);
    }

    public NegativeValueException(String message, Throwable cause){
        super(message,cause);
    }
}
