package com.harun.virtualInvestmentPlatform.dto.investDatabase;

import com.harun.virtualInvestmentPlatform.model.Commodity;

import java.util.List;

public class CommodityResponse {
    private boolean success;
    private List<Commodity> result;

    // Constructors
    public CommodityResponse() {
    }

    public CommodityResponse(boolean success, List<Commodity> result) {
        this.success = success;
        this.result = result;
    }

    // Getter and Setter methods
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Commodity> getResult() {
        return result;
    }

    public void setResult(List<Commodity> result) {
        this.result = result;
    }
}
