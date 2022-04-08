package com.nazarov.movieland.currency_converter.service;

import com.nazarov.movieland.currency_converter.entity.Currency;
import com.nazarov.movieland.currency_converter.repository.CurrencyRepository;
import com.nazarov.movieland.currency_converter.utils.CourseExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class CurrencyService {

    public static final String EUR = "EUR";
    public static final String USD = "USD";
    public static final Long EUR_ID = 978L;
    public static final Long USD_ID = 840L;
    public static final int INDEX_ZERO = 0;
    public static final int INDEX_ONE = 1;

    private List<Currency> cashedCurrencyList = Collections.synchronizedList(new ArrayList<>());

    @PostConstruct
    void init() {
        fillCurrencyCache();
        log.info("update currency cache");
    }

    public void fillCurrencyCache() {
        cashedCurrencyList = currencyRepository.findAll();
    }

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
        log.info("update currency rates in DB");
    }

    public Currency getByName(String name) {
        if (name.toUpperCase().equals(EUR)) {
            return cashedCurrencyList.get(INDEX_ZERO);
        } else if (name.toUpperCase().equals(USD)) {
            return cashedCurrencyList.get(INDEX_ONE);
        } else {
            return null;
        }
    }
}
