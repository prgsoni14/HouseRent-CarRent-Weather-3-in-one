package com.production.hrow.helper;

public class JwtResponse {
    
    String token;

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getResponse() {
        return token;
    }

    public void setResponse(String token) {
        this.token = token;
    }
}
