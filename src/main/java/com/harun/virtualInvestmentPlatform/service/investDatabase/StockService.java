package com.harun.virtualInvestmentPlatform.service.investDatabase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harun.virtualInvestmentPlatform.dao.investDatabase.StockRepository;
import com.harun.virtualInvestmentPlatform.dto.investDatabase.StockResponse;
import com.harun.virtualInvestmentPlatform.model.Stock;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import kong.unirest.HttpResponse;

@Service
public class StockService {
    @Value("${collectapi.apikey}")
    private String apiKey;

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public List<Stock> fetchStockRates() {
        HttpResponse<String> response = Unirest.get("https://api.collectapi.com/economy/hisseSenedi")
                .header("content-type", "application/json")
                .header("authorization", "apikey " + apiKey)
                .asString();

        if (response.getStatus() == 200) {
            // Using Jackson for JSON parsing
            ObjectMapper mapper = new ObjectMapper();
            StockResponse stockResponse = null;
            try {
                stockResponse = mapper.readValue(response.getBody(), StockResponse.class);
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
                return new ArrayList<>();
            }
            return stockResponse.getResult();
        }

        return new ArrayList<>();
    }
}
