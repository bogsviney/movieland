package com.nazarov.movieland.converter;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        converter.convert(100.0, "EUR");
    }
}
