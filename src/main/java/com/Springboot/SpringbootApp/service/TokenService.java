package com.Springboot.SpringbootApp.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class TokenService {

    public String usernameFromToken(String token){
        String[] tokenParts = token.split("\\.");
        JSONObject payload = new JSONObject(new String(Base64.getUrlDecoder().decode(tokenParts[1])));
        String payLoadUsername= payload.getString("sub");

        return payLoadUsername;
    }
}
