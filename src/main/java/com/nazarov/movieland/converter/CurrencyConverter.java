package com.nazarov.movieland.converter;

import com.nazarov.movieland.converter.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@RequiredArgsConstructor
@Component
public class CurrencyConverter {

    private final CurrencyService currencyService;

    public double convertNativeToForeign(double uah, String currency) {
        double result = uah / currencyService.getByName(currency).getRate();
        return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
