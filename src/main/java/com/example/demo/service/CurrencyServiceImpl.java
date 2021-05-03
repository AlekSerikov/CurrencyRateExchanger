package com.example.demo.service;

import com.example.demo.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyStorage currencyStorage;

    @Override
    public List<Currency> getCurrencies() {
        return currencyStorage.getCurrencies();
    }

    @Override
    public Currency getCurrency(String name) {
        return currencyStorage.getCurrency(name);
    }

    @Override
    public List<Currency> updateCurrency() {
        return currencyStorage.updateCurrencies();
    }
}
