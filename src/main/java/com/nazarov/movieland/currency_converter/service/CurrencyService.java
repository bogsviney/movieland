package com.nazarov.movieland.currency_converter.service;

import com.nazarov.movieland.currency_converter.entity.Currency;
import com.nazarov.movieland.currency_converter.repository.CurrencyRepository;
import com.nazarov.movieland.currency_converter.utils.CourseExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor

public class CurrencyService {

    public static final String EUR = "EUR";
    public static final String USD = "USD";
    public static final Long EUR_ID = 978L;
    public static final Long USD_ID = 840L;

    private final CurrencyRepository currencyRepository;

    private CourseExtractor extractor = new CourseExtractor();

    public void refreshRate(double rate, Long id) {
        currencyRepository.refreshRate(rate, id);
    }

    double getRateFromNBU(String currency) {
        return extractor.extract(currency);
    }

    public void updateRatesInDatabase() {
        refreshRate(getRateFromNBU(EUR), EUR_ID);
        refreshRate(getRateFromNBU(USD), USD_ID);
    }

    public Currency getByName(String name) {
        return currencyRepository.getByName(name.toUpperCase());
    }
}
