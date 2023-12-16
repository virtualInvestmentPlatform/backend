package com.harun.virtualInvestmentPlatform.dto;

import java.util.Objects;

public class CurrencyBasicDto {
    private String code;
    private double buying;
    private double selling;
    private double rate;
    private String time;

    public CurrencyBasicDto(String code, double buying, double selling, double rate, String time) {
        this.code = code;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyBasicDto that = (CurrencyBasicDto) o;
        return Double.compare(buying, that.buying) == 0 && Double.compare(selling, that.selling) == 0 && Double.compare(rate, that.rate) == 0 && Objects.equals(code, that.code) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, buying, selling, rate, time);
    }

    @Override
    public String toString() {
        return "CurrencyBasicDto{" +
                "code='" + code + '\'' +
                ", buying=" + buying +
                ", selling=" + selling +
                ", rate=" + rate +
                ", time='" + time + '\'' +
                '}';
    }
}

