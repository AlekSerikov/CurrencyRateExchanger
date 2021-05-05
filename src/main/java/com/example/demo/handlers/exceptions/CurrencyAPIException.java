package com.example.demo.handlers.exceptions;

public class CurrencyAPIException extends CurrencyRateExchangerException{

    public CurrencyAPIException() {
    }

    public CurrencyAPIException(String message) {
        super(message);
    }

    public CurrencyAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyAPIException(Throwable cause) {
        super(cause);
    }
}
