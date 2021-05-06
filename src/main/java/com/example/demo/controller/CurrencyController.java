package com.example.demo.controller;

import com.example.demo.entity.Currency;
import com.example.demo.service.CurrencyService;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrencyController {

    private @NonNull CurrencyService currencyService;

    @GetMapping("/currency")
    public List<Currency> getCurrencies() {
        return currencyService.getCurrencies();
    }

    @GetMapping("/currency/{name}")
    public Currency getCurrencyByName(@PathVariable String name) {
        return currencyService.getCurrency(name);
    }

    @PutMapping("/currency")
    public List<Currency> updateCurrency() {
        return currencyService.updateCurrency();
    }
}