package dtos;

import lombok.Data;

@Data
public class CreateShortUrlRequest {
    private String longUrl;
}
