package com.nazarov.movieland.controller;


import com.nazarov.movieland.entity.Country;
import com.nazarov.movieland.service.CountryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/country")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<Country> findAll(){
        log.info("COUNTRY CONTROLLER: get all countries");
        return countryService.findAll();
    }
}
