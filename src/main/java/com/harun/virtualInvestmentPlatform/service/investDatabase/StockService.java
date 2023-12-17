package com.harun.virtualInvestmentPlatform.service.investDatabase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harun.virtualInvestmentPlatform.dao.investDatabase.StockRepository;
import com.harun.virtualInvestmentPlatform.dto.StockBasicDto;
import com.harun.virtualInvestmentPlatform.dto.StockDetailedDto;
import com.harun.virtualInvestmentPlatform.dto.investDatabase.StockResponse;
import com.harun.virtualInvestmentPlatform.global.GlobalVariables;
import com.harun.virtualInvestmentPlatform.model.Stock;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public StockDetailedDto getStock(String code) {
        Optional<Stock> optionalStock = stockRepository.findById(code);
        if(optionalStock.isPresent()){
            Stock stock = optionalStock.get();
            return new StockDetailedDto(stock.getCode(),
                    stock.getText(),
                    stock.getHacim(),
                    stock.getLastprice(),
                    stock.getRate(),
                    stock.getMin(),
                    stock.getMax(),GlobalVariables.LAST_INVESTMENT_DATA_FETCH);
        }

        return null;
    }

    public List<StockBasicDto> getAllStocks() {
        List<StockBasicDto> stocks = new ArrayList<>();
        stockRepository.findAll().forEach(stock -> {
            stocks.add(
                    new StockBasicDto(
                            stock.getCode(),
                            stock.getLastprice(),
                            stock.getRate(),
                            stock.getMin(),
                            stock.getMax(),
                            GlobalVariables.LAST_INVESTMENT_DATA_FETCH
                    )
            );
        });
        return stocks;
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
