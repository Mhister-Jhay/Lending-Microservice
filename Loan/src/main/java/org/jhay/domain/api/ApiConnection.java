package org.jhay.domain.api;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
@Component
public class ApiConnection {

    public <T, R> R connectAndPost(T requestBody, String url, Class<R> responseClass){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("sk_test_fb95dd7bf1d981b505baafd82f301cbec52cae28");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> httpEntity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(45))
                .build();
        try{
            ResponseEntity<R> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseClass);
            return response.getBody();
        }catch(HttpClientErrorException | HttpServerErrorException e){
            e.printStackTrace();
            return null;
        }
    }
    public <R> R connectAndGet(String url, Class<R> responseClass){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("sk_test_fb95dd7bf1d981b505baafd82f301cbec52cae28");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(30))
                .setReadTimeout(Duration.ofSeconds(45))
                .build();
        try{
            ResponseEntity<R> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseClass);
            return response.getBody();
        }catch(HttpClientErrorException | HttpServerErrorException e){
            e.printStackTrace();
            return null;
        }
    }
}
