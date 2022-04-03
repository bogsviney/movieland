package com.nazarov.movieland.utils;

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

    @Scheduled(cron = "@midnight")
    public void midnightTasks() {
        movieService.findAndDeleteMarkedItems();
        log.info("SCHEDULED TASKS: time is {} ---> all marked movies has been deleted!", LocalTime.now().toString());
    }
}
