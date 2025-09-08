package com.ongcl.urlshortener.controllers;

import com.ongcl.urlshortener.services.UrlShortenerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "redirect")
public class RedirectController {
    private final UrlShortenerService urlShortenerService;

    @GetMapping("/{shortCode}")
    @Operation(summary = "Redirect to the original long url using short code.")
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
