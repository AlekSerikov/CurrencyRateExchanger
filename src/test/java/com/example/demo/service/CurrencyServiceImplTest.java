package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.entity.Currency;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CurrencyServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class CurrencyServiceImplTest {

    @Autowired
    private CurrencyServiceImpl currencyServiceImpl;

    @MockBean
    private CurrencyStorage currencyStorage;

    @Test
    public void getAllCurrency_success_test() {
        List<Currency> currencies = List.of(
                new Currency("USD", 100.0),
                new Currency("EUR", 100.0)
        );
        when(currencyStorage.getCurrencies()).thenReturn(currencies);

        List<Currency> receivedCurrencies = currencyStorage.getCurrencies();
        verify(currencyStorage).getCurrencies();

        assertSame(currencies, receivedCurrencies);
        assertFalse(receivedCurrencies.isEmpty());
    }

    @Test
    public void getCurrencyByName_success_test() {
        Currency currency = new Currency("USD", 100.0);
        when(currencyStorage.getCurrency(any())).thenReturn(currency);

        Currency receivedCurrency = currencyServiceImpl.getCurrency("USD");
        verify(currencyStorage).getCurrency("USD");

        assertSame(currency, receivedCurrency);
    }

    @Test
    public void testUpdateCurrency() {
        List<Currency> currencies = List.of(
                new Currency("USD", 100.0),
                new Currency("EUR", 100.0)
        );

        when(currencyStorage.updateCurrencies()).thenReturn(currencies);

        List<Currency> updatedCurrencyResult = currencyServiceImpl.updateCurrency();
        verify(currencyStorage).updateCurrencies();

        assertSame(updatedCurrencyResult, currencies);
    }
}