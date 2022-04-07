package com.nazarov.movieland.data_interchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nazarov.movieland.entity.Movie;
import com.nazarov.movieland.service.MovieService;
import lombok.SneakyThrows;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    private static final String JSON_CONTENT_SUB_TYPE_NAME = "json";
    private static final String XML_CONTENT_SUB_TYPE_NAME = "xml";
    private static final String JSON_FILE_NAME = "movies.json";
    private static final String XML_FILE_NAME = "movies.xml";
    private static final String XML_TEMPLATE = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n";

    @Autowired
    private MovieService movieService;

    @SneakyThrows
    public String getMoviesJSON() {
        List<Movie> movies = movieService.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(movies);
        return jsonData;
    }

    public String convertJsonToXml(String json, String root) {
        json = json.substring(1, json.length() - 1);
        JSONObject jsonObject = new JSONObject(json);
        String xml = XML_TEMPLATE + "<" + root + ">" + XML.toString(jsonObject) + "</" + root + ">";
        return xml;
    }

    public ResponseEntity<byte[]> downloadFileWithData(String fileName, String dataContent, String contentSubType) {
        byte[] dataBytes = dataContent.getBytes();
        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.setContentLength(dataBytes.length);
        respHeaders.setContentType(new MediaType("text", contentSubType));
        respHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        respHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        return new ResponseEntity<byte[]>(dataBytes, respHeaders, HttpStatus.OK);
    }

    public ResponseEntity<byte[]> downloadJSON() {
        return downloadFileWithData(JSON_FILE_NAME, getMoviesJSON(), JSON_CONTENT_SUB_TYPE_NAME);
    }

    public ResponseEntity<byte[]> downloadXML() {
        String json = getMoviesJSON();
        String xml = convertJsonToXml(json, "root");
        return downloadFileWithData(XML_FILE_NAME, xml, XML_CONTENT_SUB_TYPE_NAME);
    }
}
