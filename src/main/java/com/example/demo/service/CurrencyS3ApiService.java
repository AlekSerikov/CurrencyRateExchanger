package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.demo.entity.Currency;
import com.example.demo.entity.CurrencyInfo;
import com.example.demo.handlers.exceptions.CurrencyAPIException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Primary
public class CurrencyS3ApiService implements CurrencyApiService {

    @Autowired
    AmazonS3 amazonS3BucketClient;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<Currency> getCurrencies() {
        return getCurrencyRatesFromS3().entrySet().stream()
                .map(entry -> new Currency(entry.getKey(), 1 / entry.getValue()))
                .collect(Collectors.toList());
    }

    private Map<String, Double> getCurrencyRatesFromS3() {
        S3Object s3object = amazonS3BucketClient.getObject("my-first-bucket-a", "currencies.json");
        try (S3ObjectInputStream inputStream = s3object.getObjectContent()) {
            System.out.println("!!!!!!!!s3");
            return objectMapper.readValue(inputStream, CurrencyInfo.class).getConversionRates();
        } catch (Exception e) {
            throw new CurrencyAPIException("Internal server error");
        }
    }
}