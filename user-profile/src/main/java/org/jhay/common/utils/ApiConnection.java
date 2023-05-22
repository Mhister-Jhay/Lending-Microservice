package org.jhay.common.utils;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
@Component
public class ApiConnection {
    public <T, R> R connectAndPost(T requestBody,String requestPath, Class<R> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("sk_test_fb95dd7bf1d981b505baafd82f301cbec52cae28");
        headers.setContentType(MediaType.APPLICATION_JSON);
        /*
         *  Creating HttpEntity to hold the requestBody and the headers
         *  which will be exchanged for response via the rest template
         */
        HttpEntity<T> requestEntity = new HttpEntity<>(requestBody, headers);

        /*
         *  Setting the connection timeout for the rest template and read timeout, so as not to expose the endpoints and
         *  request parameters for a long time
         */
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(45))
                .build();

        try {
            /*
             *Making the HttpCall using the Spring's Rest Template
             */
            ResponseEntity<R> response = restTemplate.exchange(requestPath, HttpMethod.POST, requestEntity, responseType);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String connectAndGet(String requestPath) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("sk_test_fb95dd7bf1d981b505baafd82f301cbec52cae28");
        headers.setContentType(MediaType.APPLICATION_JSON);
        /*
         *  Creating HttpEntity to hold the requestBody and the headers
         *  which will be exchanged for response via the rest template
         */
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        /*
         *  Setting the connection timeout for the rest template and read timeout, so as not to expose the endpoints and
         *  request parameters for a long time
         */
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(45))
                .build();

        try {
            /*
             *Making the HttpCall using the Spring's Rest Template
             */
            ResponseEntity<String> response = restTemplate.exchange(requestPath, HttpMethod.GET, requestEntity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            e.printStackTrace();
            return null;
        }
    }
}
