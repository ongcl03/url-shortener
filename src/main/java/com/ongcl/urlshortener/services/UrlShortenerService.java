package com.ongcl.urlshortener.services;

import com.ongcl.urlshortener.repositories.UrlMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

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

    private String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);

        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(BASE62_CHARS.length());
            sb.append(BASE62_CHARS.charAt(randomIndex));
        }

        return sb.toString();
    }

}
