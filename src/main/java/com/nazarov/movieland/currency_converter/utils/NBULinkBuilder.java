package com.nazarov.movieland.currency_converter.utils;

import java.time.ZonedDateTime;
import java.util.StringJoiner;

public class NBULinkBuilder {

    public static final String NBU_LINK_BODY =
            "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=";
    public static final String DATE_YEAR = String.valueOf(ZonedDateTime.now().getYear());
    public static final String DATE_MONTH = String.valueOf(ZonedDateTime.now().getMonthValue());
    public static final String DATE_DAY = String.valueOf(ZonedDateTime.now().getDayOfMonth());

    public String createLink(String currency) {
        StringJoiner joiner = new StringJoiner("");
        joiner.add(NBU_LINK_BODY);
        joiner.add(currency);
        joiner.add("&date");
        joiner.add(DATE_YEAR);
        joiner.add(DATE_MONTH);
        joiner.add(DATE_DAY);
        joiner.add("&json");
        String applyLink = joiner.toString();
        return applyLink;
    }
}
