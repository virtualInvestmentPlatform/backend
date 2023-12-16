package com.harun.virtualInvestmentPlatform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "commodities")
public class Commodity {
    @Id
    private String name;
    @Column
    private String text;
    @Column
    private double buying;
    @Column
    private String buyingstr;
    @Column
    private double selling;
    @Column
    private String sellingstr;
    @Column
    private String time;
    @Column
    private String date;
    @Column
    private String datetime;
    @Column
    private double rate;


    public Commodity() {
    }

    public Commodity(String name, String text, double buying, String buyingstr, double selling, String sellingstr,
                     String time, String date, String datetime, double rate) {
        this.name = name;
        this.text = text;
        this.buying = buying;
        this.buyingstr = buyingstr;
        this.selling = selling;
        this.sellingstr = sellingstr;
        this.time = time;
        this.date = date;
        this.datetime = datetime;
        this.rate = rate;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity commodity = (Commodity) o;
        return Double.compare(buying, commodity.buying) == 0 && Double.compare(selling, commodity.selling) == 0 && Double.compare(rate, commodity.rate) == 0 && Objects.equals(name, commodity.name) && Objects.equals(text, commodity.text) && Objects.equals(buyingstr, commodity.buyingstr) && Objects.equals(sellingstr, commodity.sellingstr) && Objects.equals(time, commodity.time) && Objects.equals(date, commodity.date) && Objects.equals(datetime, commodity.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text, buying, buyingstr, selling, sellingstr, time, date, datetime, rate);
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", buying=" + buying +
                ", buyingstr='" + buyingstr + '\'' +
                ", selling=" + selling +
                ", sellingstr='" + sellingstr + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", datetime='" + datetime + '\'' +
                ", rate=" + rate +
                '}';
    }
}


