package com.nazarov.movieland.utils;

import com.nazarov.movieland.currency_converter.service.CurrencyService;
import com.nazarov.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final MovieService movieService;
    private final CurrencyService currencyService;

    private static final String CRON_15_31_EVERY_DAY = "0 31 15 * * *";
    private static final String CRON_MIDNIGHT_EVERY_DAY = "0 00 00 * * *";

    @Scheduled(cron = CRON_15_31_EVERY_DAY)  // Every day at 15:31 (NBU changes rate for tommorow at 15:30)
    public void currencyRatesRefresh() {
        currencyService.updateRatesInDatabase();
        log.info("currency rates updated");
    }

    @Scheduled(cron = CRON_MIDNIGHT_EVERY_DAY)
    public void midnightTasks() {
        movieService.findAndDeleteMarkedItems();
        log.info("time is {} ---> all marked movies has been deleted!", LocalTime.now());
    }
}
