package com.example.demo.handlers.exceptions;

public class CurrencyRateExchangerException extends RuntimeException{

    public CurrencyRateExchangerException() {
    }

    public CurrencyRateExchangerException(String message) {
        super(message);
    }

    public CurrencyRateExchangerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyRateExchangerException(Throwable cause) {
        super(cause);
    }
}
