package com.nazarov.movieland;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

    public class FileReader {

        public static List<String> readUnicodeJava11(String fileName) {

            List<String> rows = new ArrayList<>();
            try (java.io.FileReader fileReader = new java.io.FileReader(fileName, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(fileReader)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.equals("")) {
                        rows.add(line);
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return rows;
        }

        @SneakyThrows
        public static List<String> readUFromUrl(String fileName) {
            List<String> rows = new ArrayList<>();
            URL oracle = new URL(fileName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (!inputLine.equals("")) {
                    rows.add(inputLine);
                }
            }
            in.close();
            return rows;
        }
    }