package com.harun.virtualInvestmentPlatform.dto;

public class StockBasicDto {
    private String code;
    private double lastprice;
    private double rate;
    private double min;
    private double max;
    private String time;

    public StockBasicDto(String code, double lastprice, double rate, double min, double max, String time) {
        this.code = code;
        this.lastprice = lastprice;
        this.rate = rate;
        this.min = min;
        this.max = max;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getLastprice() {
        return lastprice;
    }

    public void setLastprice(double lastprice) {
        this.lastprice = lastprice;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
