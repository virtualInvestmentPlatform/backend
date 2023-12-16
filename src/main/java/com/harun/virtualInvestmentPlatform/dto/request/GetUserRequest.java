package com.harun.virtualInvestmentPlatform.dto.request;

public class GetUserRequest {
    private String jwt;

    public GetUserRequest() {

    }

    public GetUserRequest(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
