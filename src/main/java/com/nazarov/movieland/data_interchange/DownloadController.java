package com.nazarov.movieland.data_interchange;

import com.nazarov.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/export/download")
public class DownloadController {

    @Autowired
    private MovieService movieService;
    private final DataService dataService;

    @SneakyThrows
    @GetMapping("/json")
    public ResponseEntity<byte[]> downloadJsonData() {
        return dataService.downloadJSON();
    }

//    @SneakyThrows
//    @GetMapping("/xml")
//    public ResponseEntity<byte[]> downloadXMLData() {
//        return dataService.downloadXML();
//    }

    @SneakyThrows
    @GetMapping("/xml")
    public ResponseEntity<byte[]> downloadXMLData() {
        return dataService.downloadXML();
    }
}
