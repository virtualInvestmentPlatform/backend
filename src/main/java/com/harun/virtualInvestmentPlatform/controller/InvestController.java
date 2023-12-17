package com.harun.virtualInvestmentPlatform.controller;

import com.harun.virtualInvestmentPlatform.dto.StockDetailedDto;
import com.harun.virtualInvestmentPlatform.service.investDatabase.CommodityService;
import com.harun.virtualInvestmentPlatform.service.investDatabase.CurrencyService;
import com.harun.virtualInvestmentPlatform.service.investDatabase.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvestController {
    private StockService stockService;
    private CurrencyService currencyService;
    private CommodityService commodityService;

    @Autowired
    public InvestController(StockService stockService, CurrencyService currencyService, CommodityService commodityService) {
        this.stockService = stockService;
        this.currencyService = currencyService;
        this.commodityService = commodityService;
    }

    @GetMapping("/stock")
    public ResponseEntity<?> getAllStocks() {
        return  ResponseEntity.ok(stockService.getAllStocks());
    }

    @GetMapping("/currency")
    public ResponseEntity<?> getAllCurrencies() {
        return  ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @GetMapping("/commodity")
    public ResponseEntity<?> getAllCommodities() {
        return  ResponseEntity.ok(commodityService.getAllCommodities());
    }

    @GetMapping("/stock/{code}")
    public ResponseEntity<?> getStock(@PathVariable String code) {
        StockDetailedDto stockDetailedDto = stockService.getStock(code);
        if (stockDetailedDto != null)
            return  ResponseEntity.ok(stockDetailedDto);
        return ResponseEntity.badRequest().build();
    }
}
