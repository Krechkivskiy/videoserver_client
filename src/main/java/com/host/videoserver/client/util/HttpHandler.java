package com.host.videoserver.client.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class HttpHandler<T> {

    @Value("${videoserver.url}")
    private String serverUrl;

    @Value("${videoserver.username}")
    private String defaultServerUsername;

    @Value("${videoserver.password}")
    private String defaultServerPassword;

    public HttpHandler() {
    }

    public T executeRequest(String url, HttpMethod httpMethod, Class responseType,
                            MultiValueMap<String, Object> body, MediaType mediaType, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        if (token != null) {
            headers.set("Authorization", token);
        }
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        return (T) restTemplate.exchange(url, httpMethod, requestEntity, responseType).getBody();
    }

    public String registerUserAuthRequest(String login, String password) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("email", defaultServerUsername);
        map.put("password", defaultServerPassword);
       return restTemplate.postForEntity("http://" + serverUrl + "/login", map, String.class).getBody();
    }
}
