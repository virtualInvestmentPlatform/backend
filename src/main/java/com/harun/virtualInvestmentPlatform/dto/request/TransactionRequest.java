package com.harun.virtualInvestmentPlatform.dto.request;

import com.harun.virtualInvestmentPlatform.enums.InvestmentType;
import com.harun.virtualInvestmentPlatform.enums.TransactionType;

import java.util.Objects;

public class TransactionRequest {
    private String investmentCode;
    private InvestmentType investmentType;
    private TransactionType transactionType;
    private double amount;

    public TransactionRequest() {

    }

    public TransactionRequest(String investmentCode, InvestmentType investmentType, TransactionType transactionType, double amount) {
        this.investmentCode = investmentCode;
        this.investmentType = investmentType;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getInvestmentCode() {
        return investmentCode;
    }

    public void setInvestmentCode(String investmentCode) {
        this.investmentCode = investmentCode;
    }

    public InvestmentType getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(InvestmentType investmentType) {
        this.investmentType = investmentType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionRequest that = (TransactionRequest) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(investmentCode, that.investmentCode) && investmentType == that.investmentType && transactionType == that.transactionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(investmentCode, investmentType, transactionType, amount);
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "investmentCode='" + investmentCode + '\'' +
                ", investmentType=" + investmentType +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                '}';
    }
}
