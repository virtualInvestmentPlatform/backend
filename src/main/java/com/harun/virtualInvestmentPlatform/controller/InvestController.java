package com.harun.virtualInvestmentPlatform.controller;

import com.harun.virtualInvestmentPlatform.service.investDatabase.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvestController {
    private StockService stockService;

    @Autowired
    public InvestController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stock")
    public ResponseEntity<?> getAllStocks() {
        return  ResponseEntity.ok(stockService.getAllStocks());
    }
}
