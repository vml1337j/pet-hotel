package vml1337j.hotelapp.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ApiRestTemplateErrorHandler extends DefaultResponseErrorHandler {

    private final ObjectMapper mapper;

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()))) {
                String httpBodyResponse = reader.lines().collect(Collectors.joining(""));
                ErrorDto apiError = mapper.readValue(httpBodyResponse, ErrorDto.class);
                throw new PetHotelApiException(response.getStatusCode(), apiError);
            }
        }
    }
}