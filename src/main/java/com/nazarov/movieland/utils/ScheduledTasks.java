package com.nazarov.movieland.utils;

import com.nazarov.movieland.converter.service.CurrencyService;
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



    @Scheduled(cron = "0 31 15 * * *")  // Every day at 15:31 (NBU changes rate for tommorow at 15:30)
    public void currencyRatesRefresh() {
        currencyService.updateRatesInDatabase();
        log.info("SCHEDULED TASKS: currency rates updated");
    }

    @Scheduled(cron = "@midnight")
    public void midnightTasks() {
        movieService.findAndDeleteMarkedItems();
        log.info("SCHEDULED TASKS: time is {} ---> all marked movies has been deleted!", LocalTime.now());
    }
}
