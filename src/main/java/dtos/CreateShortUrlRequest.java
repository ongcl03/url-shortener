package dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateShortUrlRequest {
    @NotBlank(message = "Long url is required")
    @URL(message = "A valid url is required")
    private String longUrl;
}
