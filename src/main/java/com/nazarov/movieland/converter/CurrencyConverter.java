package com.nazarov.movieland.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CurrencyConverter {

    private CourseExtractor extractor = new CourseExtractor();

    public double convert(double uah, String currency) {
        double result = uah / extractor.extract(currency);
        log.info("CURRENCY CONVERTER: convert {} UAH to {} = {} ", uah, currency, result);
        return result;
    }
}
