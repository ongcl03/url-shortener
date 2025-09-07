package com.ongcl.urlshortener.controllers;

import com.ongcl.urlshortener.entities.UrlMapping;
import com.ongcl.urlshortener.services.UrlShortenerService;
import dtos.CreateShortUrlRequest;
import dtos.CreateShortUrlResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;


    @PostMapping("create-url")
    public ResponseEntity<CreateShortUrlResponse> createUrl(@RequestBody CreateShortUrlRequest request) {
        // get the long url from the request dto
        String longUrl = request.getLongUrl();
        UrlMapping urlMapping = urlShortenerService.createShortUrl(longUrl);
        String shortCode = urlMapping.getShortCode();

        String fullShortUrl = "http://localhost:8080/" + shortCode;

        // map it to response dto, so it shows the short url only
        CreateShortUrlResponse response = new CreateShortUrlResponse(fullShortUrl);

        // Status code 201 for CREATED
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }






}
