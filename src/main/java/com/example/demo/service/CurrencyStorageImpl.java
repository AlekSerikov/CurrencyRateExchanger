package com.example.demo.service;

import com.example.demo.dao.CurrencyRepository;
import com.example.demo.entity.Currency;
import com.example.demo.handlers.exceptions.NoSuchCurrencyException;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrencyStorageImpl implements CurrencyStorage {

    private @NonNull CurrencyRepository currencyRepository;

    @Resource(name="${application.apiService}")
//    @NonNull
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