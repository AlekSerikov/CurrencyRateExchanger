package com.example.demo.service;

import com.example.demo.entity.Currency;

import java.util.List;

public interface CurrencyStorage {

    List<Currency> getCurrencies();

    Currency getCurrency(String name);

    List<Currency> updateCurrencies();
}