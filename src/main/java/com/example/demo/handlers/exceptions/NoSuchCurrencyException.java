package com.example.demo.handlers.exceptions;

public class NoSuchCurrencyException extends CurrencyRateExchangerException{

    public NoSuchCurrencyException() {
    }

    public NoSuchCurrencyException(String message) {
        super(message);
    }

    public NoSuchCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCurrencyException(Throwable cause) {
        super(cause);
    }
}