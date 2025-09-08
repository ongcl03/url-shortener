package com.ongcl.urlshortener.controllers;

import com.ongcl.urlshortener.services.UrlShortenerService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
public class RedirectController {
    private final UrlShortenerService urlShortenerService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToLongUrl(
            @PathVariable
            @Pattern(regexp = "[a-zA-Z0-9]{6}", message = "Short code must be 6 alphanumeric characters")
            String shortCode
    ) {
        String longUrl = urlShortenerService.getLongUrl(shortCode);

        // If the service call was successful, build the redirect response.
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", longUrl)
                .build();
    }
}
