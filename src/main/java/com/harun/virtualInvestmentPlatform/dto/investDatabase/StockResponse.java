package com.harun.virtualInvestmentPlatform.dto.investDatabase;

import com.harun.virtualInvestmentPlatform.model.Stock;

import java.util.List;
import java.util.Objects;

public class StockResponse {
    private boolean success;
    private List<Stock> result;

    public StockResponse() {

    }

    public StockResponse(boolean success, List<Stock> result) {
        this.success = success;
        this.result = result;
    }

    public List<Stock> getResult() {
        return result;
    }

    public void setResult(List<Stock> result) {
        this.result = result;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockResponse that = (StockResponse) o;
        return success == that.success && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, result);
    }

    @Override
    public String toString() {
        return "StockResponse{" +
                "success=" + success +
                ", result=" + result +
                '}';
    }
}
