package com.example.demo.controller;

import static org.mockito.Mockito.when;

import com.example.demo.entity.Currency;
import com.example.demo.service.CurrencyService;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ContextConfiguration(classes = {CurrencyController.class})
@WebMvcTest(controllers = {CurrencyController.class})
@ExtendWith(SpringExtension.class)
public class CurrencyControllerTest {

    @Autowired
    private CurrencyController currencyController;

    @MockBean
    private CurrencyService currencyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetCurrencies() throws Exception {
        List<Currency> currencies = List.of(
                new Currency("USD", 100.0),
                new Currency("EUR", 100.0)
        );

        when(this.currencyService.getCurrencies()).thenReturn(currencies);

        mockMvc.perform(get("/api/currency"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(objectMapper.writeValueAsString(currencies))));
    }

    @Test
    public void testGetCurrencyByName() throws Exception {
        Currency currency = new Currency("USD", 10);

        when(this.currencyService.getCurrency(any())).thenReturn(currency);

        mockMvc.perform(get("/api/currency/{name}", "value"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(objectMapper.writeValueAsString(currency))));

    }

    @Test
    public void testUpdateCurrency() throws Exception {
        List<Currency> currencies = List.of(
                new Currency("USD", 100.0),
                new Currency("EUR", 100.0)
        );

        when(this.currencyService.updateCurrency()).thenReturn(currencies);

        mockMvc.perform(put("/api/currency")
                .content(objectMapper.writeValueAsString(currencies)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(currencies)));
    }
}