package com.nazarov.movieland.utils;

import com.nazarov.movieland.currency_converter.service.CurrencyService;
import com.nazarov.movieland.service.GenreService;
import com.nazarov.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    private final MovieService movieService;
    private final CurrencyService currencyService;
    private final GenreService genreService;

    private static final String CRON_15_35_FROM_MONDAY_TO_FRIDAY = "0 35 15 * * MON-FRI";
    private static final String CRON_MIDNIGHT_EVERY_DAY = "0 00 00 * * *";
    private static final String CRON_MIDNIGHT_FROM_MONDAY_TO_FRIDAY = "0 0 00 * * MON-FRI";
    private static final String CRON_EVERY_FOUR_HOURS = "0 0 */4 * * *";

    @Scheduled(cron = CRON_EVERY_FOUR_HOURS)
    public void everyFourHourTask() {
        genreService.fillGenreCache();
    }

    @Scheduled(cron = CRON_15_35_FROM_MONDAY_TO_FRIDAY)  // (NBU changes rate for tommorow at 15:30 from MON to FRI)
    public void currencyRatesRefresh() {
        currencyService.updateRatesInDatabase();
    }

    @Scheduled(cron = CRON_MIDNIGHT_EVERY_DAY)
    public void midnightEveryDayTasks() {
        movieService.findAndDeleteMarkedItems();
    }

    @Scheduled(cron = CRON_MIDNIGHT_FROM_MONDAY_TO_FRIDAY)
    public void midnightFromMondayToFridayTasks() {
        currencyService.fillCurrencyCache();
    }
}
