package com.harun.virtualInvestmentPlatform.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    private String code;
    @Column
    private String name;
    @Column
    private double buying;
    @Column
    private String buyingstr;
    @Column
    private double selling;
    @Column
    private String sellingstr;
    @Column
    private double rate;
    @Column
    private String time;
    @Column
    private String date;
    @Column
    private String datetime;
    @Column
    private int calculated;

    public Currency() {}

    public Currency(String code, String name, double buying, String buyingstr, double selling,
                      String sellingstr, double rate, String time, String date, String datetime, int calculated) {
        this.code = code;
        this.name = name;
        this.buying = buying;
        this.buyingstr = buyingstr;
        this.selling = selling;
        this.sellingstr = sellingstr;
        this.rate = rate;
        this.time = time;
        this.date = date;
        this.datetime = datetime;
        this.calculated = calculated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBuyingstr() {
        return buyingstr;
    }

    public void setBuyingstr(String buyingstr) {
        this.buyingstr = buyingstr;
    }

    public double getSelling() {
        return selling;
    }

    public void setSelling(double selling) {
        this.selling = selling;
    }

    public String getSellingstr() {
        return sellingstr;
    }

    public void setSellingstr(String sellingstr) {
        this.sellingstr = sellingstr;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getCalculated() {
        return calculated;
    }

    public void setCalculated(int calculated) {
        this.calculated = calculated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Double.compare(buying, currency.buying) == 0 && Double.compare(selling, currency.selling) == 0 && Double.compare(rate, currency.rate) == 0 && calculated == currency.calculated && Objects.equals(code, currency.code) && Objects.equals(name, currency.name) && Objects.equals(buyingstr, currency.buyingstr) && Objects.equals(sellingstr, currency.sellingstr) && Objects.equals(time, currency.time) && Objects.equals(date, currency.date) && Objects.equals(datetime, currency.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, buying, buyingstr, selling, sellingstr, rate, time, date, datetime, calculated);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", buying=" + buying +
                ", buyingstr='" + buyingstr + '\'' +
                ", selling=" + selling +
                ", sellingstr='" + sellingstr + '\'' +
                ", rate=" + rate +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", datetime='" + datetime + '\'' +
                ", calculated=" + calculated +
                '}';
    }
}
