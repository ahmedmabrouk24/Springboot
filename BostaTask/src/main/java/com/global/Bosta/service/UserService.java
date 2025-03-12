package com.global.Bosta.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.Bosta.model.UserModel;

@Service
public class UserService {

    private final RestTemplate restTemplate;

    private final String apiUrl = "https://app.bosta.co/api/v2";

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> createUserAccount(UserModel request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.set("Authorization", "Bearer YOUR_ACCESS_TOKEN");  // Add auth token if required

        HttpEntity<UserModel> entity = new HttpEntity<>(request, headers);

        return restTemplate.exchange(apiUrl + "/users/business/admin", HttpMethod.POST, entity, String.class);
    }
}
