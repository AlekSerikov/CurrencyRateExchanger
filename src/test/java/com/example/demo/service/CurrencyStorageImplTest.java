package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.dao.CurrencyRepository;
import com.example.demo.entity.Currency;
import com.example.demo.handlers.exceptions.NoSuchCurrencyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CurrencyStorageImpl.class})
@ExtendWith(SpringExtension.class)
public class CurrencyStorageImplTest {

    @MockBean
    private CurrencyApiService currencyApiService;

    @Autowired
    private CurrencyStorageImpl currencyStorageImpl;

    @MockBean
    private CurrencyRepository currencyRepository;

    @Test
    public void getAllCurrency_success_test() {
        List<Currency> currencies = List.of(
                new Currency("USD", 100.0),
                new Currency("EUR", 100.0)
        );
        when(currencyRepository.findAll()).thenReturn(currencies);

        List<Currency> receivedCurrencies = currencyStorageImpl.getCurrencies();
        verify(currencyRepository).findAll();

        assertSame(currencies, receivedCurrencies);
        assertFalse(receivedCurrencies.isEmpty());
    }

    @Test
    public void getCurrency_success_test() {
        Currency currency = new Currency("USD", 100.0);
        when(currencyRepository.findByName(any())).thenReturn(Optional.of(currency));

        Currency receivedCurrency = currencyStorageImpl.getCurrency("USD");
        verify(currencyRepository).findByName("USD");

        assertSame(currency, receivedCurrency);
    }

    @Test
    public void getCurrency_NoSuchCurrencyException_test() {

        when(currencyRepository.findByName(any())).thenReturn(Optional.empty());

        assertThrows(NoSuchCurrencyException.class,
                () -> currencyStorageImpl.getCurrency("noSuchCurrency"));

        verify(currencyRepository).findByName(anyString());
    }

    @Test
    public void testUpdateCurrency() {
        List<Currency> currencyListToUpdate = new ArrayList<>();

        List<Currency> currencies = List.of(
                new Currency("USD", 100.0),
                new Currency("EUR", 100.0)
        );

        when(currencyApiService.getCurrencies()).thenReturn(currencies);
        when(currencyRepository.saveAll(any())).thenReturn(currencyListToUpdate);

        List<Currency> updatedCurrencyResult = currencyStorageImpl.updateCurrencies();
        verify(currencyApiService).getCurrencies();
        verify(currencyRepository).saveAll(any());

        assertSame(updatedCurrencyResult, currencyListToUpdate); //????
        assertTrue(updatedCurrencyResult.isEmpty());

    }
}