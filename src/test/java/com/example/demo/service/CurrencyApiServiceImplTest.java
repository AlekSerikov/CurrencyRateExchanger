package com.example.demo.service;

import com.example.demo.entity.Currency;
import com.example.demo.entity.CurrencyInfo;
import com.example.demo.handlers.exceptions.CurrencyAPIException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CurrencyApiServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CurrencyApiServiceImplTest {

    @Autowired
    private CurrencyApiServiceImpl currencyApiService;

    @MockBean
    private CircuitBreaker circuitBreaker;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void getAllCurrency_success_test() {
        when(restTemplate.getForObject(any(), any()))
                .thenReturn(new CurrencyInfo(Map.of("USD", 2.5, "EUR", 3.1)));

        when(circuitBreaker.run(any())).thenReturn(new CurrencyInfo(Map.of("USD", 2.5, "EUR", 3.1)));

        List<Currency> currencies = currencyApiService.getCurrencies();

        assertEquals(currencies.size(), 2);
//        assertEquals(new Currency("USD", 0.4), currencies.get(0));
    }

    @Test
    void getAllCurrency_CurrencyAPIException_test() {
        when(restTemplate.getForObject(any(), any()))
                .thenReturn(new CurrencyInfo(Map.of("USD", 2.5, "EUR", 3.1)));

        when(circuitBreaker.run(any())).thenReturn(null);

        assertThrows(CurrencyAPIException.class, () -> currencyApiService.getCurrencies());
    }
}