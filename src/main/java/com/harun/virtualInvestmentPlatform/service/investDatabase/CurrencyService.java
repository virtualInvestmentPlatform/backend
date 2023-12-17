package com.harun.virtualInvestmentPlatform.service.investDatabase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harun.virtualInvestmentPlatform.dao.investDatabase.CurrencyRepository;
import com.harun.virtualInvestmentPlatform.dao.investDatabase.StockRepository;
import com.harun.virtualInvestmentPlatform.dto.CurrencyBasicDto;
import com.harun.virtualInvestmentPlatform.dto.CurrencyDetailedDto;
import com.harun.virtualInvestmentPlatform.dto.StockBasicDto;
import com.harun.virtualInvestmentPlatform.dto.StockDetailedDto;
import com.harun.virtualInvestmentPlatform.dto.investDatabase.CurrencyResponse;
import com.harun.virtualInvestmentPlatform.dto.investDatabase.StockResponse;
import com.harun.virtualInvestmentPlatform.global.GlobalVariables;
import com.harun.virtualInvestmentPlatform.model.Currency;
import com.harun.virtualInvestmentPlatform.model.Stock;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Value("${collectapi.apikey}")
    private String apiKey;

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> fetchCurrencyRates() {
        HttpResponse<String> response = Unirest.get("https://api.collectapi.com/economy/allCurrency")
                .header("content-type", "application/json")
                .header("authorization", "apikey " + apiKey)
                .asString();

        if (response.getStatus() == 200) {

            ObjectMapper mapper = new ObjectMapper();
            CurrencyResponse currencyResponse = null;
            try {
                currencyResponse = mapper.readValue(response.getBody(), CurrencyResponse.class);
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
                return new ArrayList<>();
            }

            return currencyResponse.getResult();
        }

        return new ArrayList<>();
    }

    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public List<CurrencyBasicDto> getAllCurrencies() {
        List<CurrencyBasicDto> currencies = new ArrayList<>();
        currencyRepository.findAll().forEach(currency -> {
            currencies.add(
                    new CurrencyBasicDto(
                            currency.getCode(),
                            currency.getBuying(),
                            currency.getSelling(),
                            currency.getRate(),
                            GlobalVariables.LAST_INVESTMENT_DATA_FETCH
                    )
            );
        });
        return currencies;
    }

    public CurrencyDetailedDto getCurrency(String code) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(code);
        if(optionalCurrency.isPresent()){
            Currency currency = optionalCurrency.get();
            return new CurrencyDetailedDto(currency.getCode(),
                    currency.getName(),
                    currency.getBuying(),
                    currency.getSelling(),
                    currency.getRate(),
                    GlobalVariables.LAST_INVESTMENT_DATA_FETCH);
        }

        return null;
    }
}
