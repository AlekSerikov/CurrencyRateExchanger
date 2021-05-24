package com.example.demo.service;

import com.example.demo.entity.Currency;
import com.example.demo.entity.CurrencyInfo;
import com.example.demo.handlers.exceptions.CurrencyAPIException;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CurrencyApiServiceImpl implements CurrencyApiService {

    @Value("${exchangeRateURL}")
    private String exchangeRateURL;
    private @NonNull RestTemplate restTemplate;
    private @NonNull CircuitBreaker circuitBreaker;

    @Override
    public List<Currency> getCurrencies() {
        System.out.println("api");
        return getCurrencyRatesFromUrl(exchangeRateURL).entrySet().stream()
                .map(entry -> new Currency(entry.getKey(), 1 / entry.getValue()))
                .collect(Collectors.toList());
    }

    private Map<String, Double> getCurrencyRatesFromUrl(String URL) {
        try {
            return circuitBreaker.run(()
                    -> restTemplate.getForObject(exchangeRateURL, CurrencyInfo.class)).getConversionRates();
        } catch (Exception e) {
            throw new CurrencyAPIException("Internal server error");
        }
    }
}