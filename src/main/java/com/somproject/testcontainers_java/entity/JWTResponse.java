package com.somproject.testcontainers_java.entity;

public class JWTResponse {

    private String jwt;

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public String toString() {
        return "JWTResponse{" +
                "jwt='" + jwt + '\'' +
                '}';
    }
}
