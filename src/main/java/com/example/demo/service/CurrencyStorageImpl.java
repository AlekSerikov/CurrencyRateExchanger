package com.example.demo.service;

import com.example.demo.dao.CurrencyRepository;
import com.example.demo.entity.Currency;
import com.example.demo.handlers.exceptions.NoSuchCurrencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyStorageImpl implements CurrencyStorage {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyApiService currencyApiService;

    @Override
    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency getCurrency(String name) {
        return currencyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchCurrencyException("There is no currency with name: " + name));
    }

    @Override
    public List<Currency> updateCurrencies() {
        return currencyRepository.saveAll(currencyApiService.getCurrencies());
    }
}