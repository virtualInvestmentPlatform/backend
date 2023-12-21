package com.harun.virtualInvestmentPlatform.dto;

import java.util.Objects;

public class InvestmentDto {
    private String code;
    private double lastprice;
    private double rate;
    private double amount;
    private long tl;
    private long profitLoss;
    private String time;

    public InvestmentDto() {

    }

    public InvestmentDto(String code , double lastprice, double rate , double amount, long tl, long profitLoss, String time) {
        this.code = code;
        this.lastprice = lastprice;
        this.rate = rate;
        this.amount = amount;
        this.tl = tl;
        this.profitLoss = profitLoss;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTl() {
        return tl;
    }

    public void setTl(long tl) {
        this.tl = tl;
    }

    public long getProfitLoss() {
        return profitLoss;
    }

    public void setProfitLoss(long profitLoss) {
        this.profitLoss = profitLoss;
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
        InvestmentDto that = (InvestmentDto) o;
        return Double.compare(lastprice, that.lastprice) == 0 && Double.compare(rate, that.rate) == 0 && Double.compare(amount, that.amount) == 0 && tl == that.tl && profitLoss == that.profitLoss && Objects.equals(code, that.code) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, lastprice, rate, amount, tl, profitLoss, time);
    }

    @Override
    public String toString() {
        return "InvestmentDto{" +
                "code='" + code + '\'' +
                ", lastprice=" + lastprice +
                ", rate=" + rate +
                ", amount=" + amount +
                ", tl=" + tl +
                ", profitLoss=" + profitLoss +
                ", time='" + time + '\'' +
                '}';
    }
}
