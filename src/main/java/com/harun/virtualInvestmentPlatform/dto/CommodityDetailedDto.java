package com.harun.virtualInvestmentPlatform.dto;

public class CommodityDetailedDto {
    private String name;
    private String text;
    private double buying;
    private double selling;
    private double rate;
    private String time;

    public CommodityDetailedDto(String name, String text, double buying, double selling, double rate, String time) {
        this.name = name;
        this.text = text;
        this.buying = buying;
        this.selling = selling;
        this.rate = rate;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
}

