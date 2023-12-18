package com.harun.virtualInvestmentPlatform.dto.request;

import com.harun.virtualInvestmentPlatform.enums.InvestmentType;

import java.util.Objects;

public class InvestmentItemCountRequest {
    private InvestmentType investmentType;
    private String investmentCode;

    public InvestmentItemCountRequest() {

    }

    public InvestmentItemCountRequest(InvestmentType investmentType,String investmentCode) {
        this.investmentType = investmentType;
        this.investmentCode = investmentCode;
    }

    public InvestmentType getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(InvestmentType investmentType) {
        this.investmentType = investmentType;
    }

    public String getInvestmentCode() {
        return investmentCode;
    }

    public void setInvestmentCode(String investmentCode) {
        this.investmentCode = investmentCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvestmentItemCountRequest that = (InvestmentItemCountRequest) o;
        return investmentType == that.investmentType && Objects.equals(investmentCode, that.investmentCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(investmentType, investmentCode);
    }

    @Override
    public String toString() {
        return "InvestmentItemCountRequest{" +
                "investmentType=" + investmentType +
                ", investmentCode='" + investmentCode + '\'' +
                '}';
    }
}
