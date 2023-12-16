package com.harun.virtualInvestmentPlatform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.util.Objects;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    private String code;
    @Column
    private String text;
    @Column
    private long hacim;
    @Column
    private String hacimstr;
    @Column
    private double lastprice;
    @Column
    private String lastpricestr;
    @Column
    private double rate;
    @Column
    private double min;
    @Column
    private String minstr;
    @Column
    private double max;
    @Column
    private String maxstr;
    @Column
    private String time;

    public Stock(){}

    public Stock(String text, String code, long hacim, String hacimstr, double lastprice, String lastpricestr,
                 double rate, double min, String minstr, double max, String maxstr, String time) {
        this.text = text;
        this.code = code;
        this.hacim = hacim;
        this.hacimstr = hacimstr;
        this.lastprice = lastprice;
        this.lastpricestr = lastpricestr;
        this.rate = rate;
        this.min = min;
        this.minstr = minstr;
        this.max = max;
        this.maxstr = maxstr;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getHacim() {
        return hacim;
    }

    public void setHacim(long hacim) {
        this.hacim = hacim;
    }

    public String getHacimstr() {
        return hacimstr;
    }

    public void setHacimstr(String hacimStr) {
        this.hacimstr = hacimStr;
    }

    public double getLastprice() {
        return lastprice;
    }

    public void setLastprice(double lastprice) {
        this.lastprice = lastprice;
    }

    public String getLastpricestr() {
        return lastpricestr;
    }

    public void setLastpricestr(String lastpricestr) {
        this.lastpricestr = lastpricestr;
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

    public void setMin(double min) {
        this.min = min;
    }

    public String getMinstr() {
        return minstr;
    }

    public void setMinstr(String minstr) {
        this.minstr = minstr;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public String getMaxstr() {
        return maxstr;
    }

    public void setMaxstr(String maxstr) {
        this.maxstr = maxstr;
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
        Stock stock = (Stock) o;
        return hacim == stock.hacim && Double.compare(lastprice, stock.lastprice) == 0 && Double.compare(rate, stock.rate) == 0 && Double.compare(min, stock.min) == 0 && Double.compare(max, stock.max) == 0 && Objects.equals(code, stock.code) && Objects.equals(text, stock.text) && Objects.equals(hacimstr, stock.hacimstr) && Objects.equals(lastpricestr, stock.lastpricestr) && Objects.equals(minstr, stock.minstr) && Objects.equals(maxstr, stock.maxstr) && Objects.equals(time, stock.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, text, hacim, hacimstr, lastprice, lastpricestr, rate, min, minstr, max, maxstr, time);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "code='" + code + '\'' +
                ", text='" + text + '\'' +
                ", hacim=" + hacim +
                ", hacimStr='" + hacimstr + '\'' +
                ", lastprice=" + lastprice +
                ", lastpricestr='" + lastpricestr + '\'' +
                ", rate=" + rate +
                ", min=" + min +
                ", minstr='" + minstr + '\'' +
                ", max=" + max +
                ", maxstr='" + maxstr + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
