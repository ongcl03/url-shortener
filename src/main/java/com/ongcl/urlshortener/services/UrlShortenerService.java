package com.ongcl.urlshortener.services;

import com.ongcl.urlshortener.entities.UrlMapping;
import com.ongcl.urlshortener.exceptions.ResourceNotFoundException;
import com.ongcl.urlshortener.repositories.UrlMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UrlShortenerService {

    private UrlMappingRepository urlMappingRepository;
    private static final String BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int SHORT_CODE_LENGTH = 6;

    public String generateUniqueShortCode() {
        String shortCode = generateRandomString();

        while (urlMappingRepository.findByShortCode(shortCode).isPresent()) {
            // when short code already exists in db, generate again
            shortCode =  generateRandomString();
        }

        return shortCode;
    }

    public String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);

        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(BASE62_CHARS.length());
            sb.append(BASE62_CHARS.charAt(randomIndex));
        }

        return sb.toString();
    }

    public UrlMapping createShortUrl(String longUrl) {
        String shortCode = generateUniqueShortCode();

        // Build the urlMapping object
        var urlMapping = UrlMapping.builder()
                .shortCode(shortCode)
                .longUrl(longUrl)
                .createdAt(LocalDateTime.now())
                .build();

        // Save the urlMapping object
        return urlMappingRepository.save(urlMapping);
    }

    public String getLongUrl(String shortCode) {
        Optional<UrlMapping> urlMappingOptional = urlMappingRepository.findByShortCode(shortCode);

        if (urlMappingOptional.isPresent()) {
            UrlMapping urlMapping = urlMappingOptional.get();
            return urlMapping.getLongUrl();
        } else {
            throw new ResourceNotFoundException("Short code not found: " + shortCode);
        }
    }


}
