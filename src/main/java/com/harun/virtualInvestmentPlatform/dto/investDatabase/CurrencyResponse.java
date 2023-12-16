package com.harun.virtualInvestmentPlatform.dto.investDatabase;

import com.harun.virtualInvestmentPlatform.model.Currency;

import java.util.List;

public class CurrencyResponse {
    private boolean success;
    private List<Currency> result;

    public CurrencyResponse() {}

    public CurrencyResponse(boolean success, List<Currency> result) {
        this.success = success;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Currency> getResult() {
        return result;
    }

    public void setResult(List<Currency> result) {
        this.result = result;
    }
}

