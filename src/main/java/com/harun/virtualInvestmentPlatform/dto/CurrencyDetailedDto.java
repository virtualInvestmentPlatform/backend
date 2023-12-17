package com.harun.virtualInvestmentPlatform.dto;

import java.util.Objects;

public class CurrencyDetailedDto {
    private String code;
    private String name;
    private double buying;
    private double selling;
    private double rate;
    private String time;

    public CurrencyDetailedDto(String code,String name,double buying, double selling, double rate, String time) {
        this.code = code;
        this.name = name;
        this.buying = buying;
        this.selling = selling;
        this.rate = rate;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBuying() {
        return buying;
    }

    public void setBuying(double buying) {
        this.buying = buying;
    }

    public double getSelling() {
        return selling;
    }

    public void setSelling(double selling) {
        this.selling = selling;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CurrencyDetailedDto{" +
                "code='" + code + '\'' +
                ", buying=" + buying +
                ", selling=" + selling +
                ", rate=" + rate +
                ", time='" + time + '\'' +
                '}';
    }
}

