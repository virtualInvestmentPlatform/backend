package com.harun.virtualInvestmentPlatform.dto;

public class StockDetailedDto {
    private String code;
    private String text;
    private long hacim;
    private double lastprice;
    private double rate;
    private double min;
    private double max;
    private String time;

    public StockDetailedDto(String code, String text, long hacim, double lastprice, double rate, double min, double max, String time) {
        this.code = code;
        this.text = text;
        this.hacim = hacim;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getHacim() {
        return hacim;
    }

    public void setHacim(long hacim) {
        this.hacim = hacim;
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
