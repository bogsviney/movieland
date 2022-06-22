package com.nazarov.movieland.currency_converter.controller;

import com.nazarov.movieland.currency_converter.entity.Currency;
import com.nazarov.movieland.currency_converter.service.CurrencyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/currency")
public class CurrencyController {

    public final CurrencyService currencyService;

    @GetMapping()
    public List<Currency> findAll() {
        return currencyService.findAll();
    }

    @GetMapping("refresh")
    public ModelAndView forcedRefreshRatesFromBankCite() {
        currencyService.updateRatesInDatabase();
        log.info("forced update currency rates from National Bank of Ukraine");
        return new ModelAndView("redirect:/api/v1/currency");
    }
}
