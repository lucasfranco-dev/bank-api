package com.luczin.bankapi.services;

import com.luczin.bankapi.exceptions.SystemOffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthorizationService {

    @Autowired
    RestTemplate restTemplate;

    public Boolean systemCheck(){
        String url = "https://util.devi.tools/api/v2/authorize";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();

        if (responseBody.equals(null) || responseBody.contains("\"authorization\" : false")){
            throw new SystemOffException("The system mock is down!");
        }

        return true;
    }
}
