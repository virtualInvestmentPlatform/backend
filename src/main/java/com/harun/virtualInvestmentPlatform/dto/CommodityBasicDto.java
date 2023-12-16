package com.harun.virtualInvestmentPlatform.dto;

import java.util.Objects;

public class CommodityBasicDto {
    private String name;
    private double buying;
    private double selling;
    private double rate;
    private String time;

    public CommodityBasicDto(String name, double buying, double selling, double rate, String time) {
        this.name = name;
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
        CommodityBasicDto that = (CommodityBasicDto) o;
        return Double.compare(buying, that.buying) == 0 && Double.compare(selling, that.selling) == 0 && Double.compare(rate, that.rate) == 0 && Objects.equals(name, that.name) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, buying, selling, rate, time);
    }

    @Override
    public String toString() {
        return "CommodityBasicDto{" +
                "name='" + name + '\'' +
                ", buying=" + buying +
                ", selling=" + selling +
                ", rate=" + rate +
                ", time='" + time + '\'' +
                '}';
    }
}
