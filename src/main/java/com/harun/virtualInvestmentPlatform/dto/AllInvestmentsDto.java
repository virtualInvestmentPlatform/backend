package com.harun.virtualInvestmentPlatform.dto;

import java.util.ArrayList;
import java.util.List;

public class AllInvestmentsDto {
    private List<InvestmentDto> stockInvestments;
    private List<InvestmentDto> currencyInvestments;
    private List<InvestmentDto> commodityInvestments;

    public AllInvestmentsDto () {
        stockInvestments = new ArrayList<>();
        currencyInvestments = new ArrayList<>();
        commodityInvestments = new ArrayList<>();
    }

    public List<InvestmentDto> getStockInvestments() {
        return stockInvestments;
    }

    public List<InvestmentDto> getCurrencyInvestments() {
        return currencyInvestments;
    }

    public List<InvestmentDto> getCommodityInvestments() {
        return commodityInvestments;
    }

    public void setStockInvestments(List<InvestmentDto> stockInvestments) {
        this.stockInvestments = stockInvestments;
    }

    public void setCurrencyInvestments(List<InvestmentDto> currencyInvestments) {
        this.currencyInvestments = currencyInvestments;
    }

    public void setCommodityInvestments(List<InvestmentDto> commodityInvestments) {
        this.commodityInvestments = commodityInvestments;
    }

    @Override
    public String toString() {
        return "AllInvestmentsDto{" +
                "stockInvestments=" + stockInvestments +
                ", currencyInvestments=" + currencyInvestments +
                ", commodityInvestments=" + commodityInvestments +
                '}';
    }
}
