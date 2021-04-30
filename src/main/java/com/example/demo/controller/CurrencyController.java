package com.example.demo.controller;

import com.example.demo.entity.Currency;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/currency")
    public List<Currency> getCurrencies() {
        return currencyService.getAllCurrency();
    }

    @GetMapping("/currency/{name}")
    public Currency getCurrencyByName(@PathVariable String name) {
        return currencyService.getCurrencyByName(name);
    }

    @PutMapping("/currency")
    public List<Currency> updateCurrency() {
        return currencyService.updateCurrency();
    }
}