package vml1337j.hotelapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import vml1337j.hotelapp.exception.ApiRestTemplateErrorHandler;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder,
                                     ObjectMapper mapper) {
        return builder
                .errorHandler(new ApiRestTemplateErrorHandler(mapper))
                .build();
    }
}
