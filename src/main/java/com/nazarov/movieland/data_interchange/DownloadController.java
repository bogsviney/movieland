package com.nazarov.movieland.data_interchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/export/download")
public class DownloadController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/json")
    public ResponseEntity<byte[]> downloadData() throws Exception {
        List<Movie> movies = movieService.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(movies);
        byte[] jsonBytes = json.getBytes();
        String fileName = "movies.json";
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(jsonBytes.length);
        respHeaders.setContentType(new MediaType("text", "json"));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<byte[]>(jsonBytes, respHeaders, HttpStatus.OK);
    }
}
