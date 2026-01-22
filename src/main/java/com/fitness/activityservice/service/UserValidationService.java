package com.fitness.activityservice.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@AllArgsConstructor
@Service
public class UserValidationService {

    private final WebClient userServiceWebClient;

    public boolean validateUser(String userId) {
        try{
            return Boolean.TRUE.equals(
                    userServiceWebClient.get()
                            .uri("/api/users/validate/{userId}", userId)
                            .retrieve()
                            .bodyToMono(Boolean.class)
                            .block()
            );
        }catch (WebClientResponseException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new RuntimeException("user not found " + userId);
            }
            else if(e.getStatusCode() == HttpStatus.BAD_REQUEST){
                throw new RuntimeException("bad request");
            }
        }
        return false;
    }
}
