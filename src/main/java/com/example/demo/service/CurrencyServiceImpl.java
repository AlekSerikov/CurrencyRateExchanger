package com.example.demo.service;

import com.example.demo.entity.Currency;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrencyServiceImpl implements CurrencyService {

    private @NonNull CurrencyStorage currencyStorage;

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
