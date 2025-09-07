package com.ongcl.urlshortener;

import com.ongcl.urlshortener.services.UrlShortenerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UrlShortenerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(UrlShortenerApplication.class, args);
        var service = context.getBean(UrlShortenerService.class);
        String code = service.generateUniqueShortCode();
        System.out.println(code);
    }

}
