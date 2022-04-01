package com.nazarov.movieland.converter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CourseExtractor {

    public static double extract(String currency) {
        NBULinkBuilder linkBuilder = new NBULinkBuilder();
        JSONFromURLGrabber parser = new JSONFromURLGrabber();

        double course = 0;

        String link = linkBuilder.createLink(currency);
        String json = parser.getJSONToStringFromURLRequest(link);
        String value = json.substring(json.indexOf("rate\":") + 6,
                json.indexOf(",\"cc\"", json.indexOf(".") +1));

        course = Double.parseDouble(value);
        log.info("course of {} for today is equal to: {} ", currency,course);
        return course;
    }
}
