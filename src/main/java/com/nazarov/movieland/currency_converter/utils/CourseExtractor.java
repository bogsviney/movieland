package com.nazarov.movieland.currency_converter.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CourseExtractor {

    private static final NBULinkBuilder NBU_LINK_BUILDER = new NBULinkBuilder();

    public static double extract(String currency) {

        ContentFromURLGrabber parser = new ContentFromURLGrabber();

        double currencyRate = 0;

        String link = NBU_LINK_BUILDER.createLink(currency);
        String json = parser.getJSONToStringFromURLRequest(link);
        String value = json.substring(json.indexOf("rate\":") + 6,
                json.indexOf(",\"cc\"", json.indexOf(".") + 1));

        currencyRate = Double.parseDouble(value);
        log.info("course of {} for today is equal to: {} ", currency, currencyRate);
        return currencyRate;
    }
}
