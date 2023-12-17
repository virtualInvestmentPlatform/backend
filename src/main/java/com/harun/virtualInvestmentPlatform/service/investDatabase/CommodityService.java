package com.harun.virtualInvestmentPlatform.service.investDatabase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harun.virtualInvestmentPlatform.dao.investDatabase.CommodityRepository;
import com.harun.virtualInvestmentPlatform.dao.investDatabase.CurrencyRepository;
import com.harun.virtualInvestmentPlatform.dto.CommodityBasicDto;
import com.harun.virtualInvestmentPlatform.dto.CommodityDetailedDto;
import com.harun.virtualInvestmentPlatform.dto.CurrencyBasicDto;
import com.harun.virtualInvestmentPlatform.dto.CurrencyDetailedDto;
import com.harun.virtualInvestmentPlatform.dto.investDatabase.CommodityResponse;
import com.harun.virtualInvestmentPlatform.dto.investDatabase.CurrencyResponse;
import com.harun.virtualInvestmentPlatform.global.GlobalVariables;
import com.harun.virtualInvestmentPlatform.model.Commodity;
import com.harun.virtualInvestmentPlatform.model.Currency;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommodityService {
    @Value("${collectapi.apikey}")
    private String apiKey;

    private final CommodityRepository commodityRepository;

    @Autowired
    public CommodityService(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    public List<Commodity> fetchCommodityRates() {
        HttpResponse<String> response = Unirest.get("https://api.collectapi.com/economy/emtia")
                .header("content-type", "application/json")
                .header("authorization", "apikey " + apiKey)
                .asString();

        if (response.getStatus() == 200) {

            ObjectMapper mapper = new ObjectMapper();
            CommodityResponse commodityResponse = null;
            try {
                commodityResponse = mapper.readValue(response.getBody(), CommodityResponse.class);
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
                return new ArrayList<>();
            }

            return commodityResponse.getResult();
        }

        return new ArrayList<>();
    }

    public Commodity saveCommodity(Commodity commodity) {
        return commodityRepository.save(commodity);
    }

    public List<CommodityBasicDto> getAllCommodities() {
        List<CommodityBasicDto> commodities = new ArrayList<>();
        commodityRepository.findAll().forEach(commodity -> {
            commodities.add(
                    new CommodityBasicDto(
                            commodity.getName(),
                            commodity.getBuying(),
                            commodity.getSelling(),
                            commodity.getRate(),
                            GlobalVariables.LAST_INVESTMENT_DATA_FETCH
                    )
            );
        });
        return commodities;
    }

    public CommodityDetailedDto getCommodity(String code) {
        Optional<Commodity> optionalCommodity = commodityRepository.findById(code);
        if(optionalCommodity.isPresent()){
            Commodity commodity = optionalCommodity.get();
            return new CommodityDetailedDto(
                    commodity.getName(),
                    commodity.getText(),
                    commodity.getBuying(),
                    commodity.getSelling(),
                    commodity.getRate(),
                    GlobalVariables.LAST_INVESTMENT_DATA_FETCH);
        }

        return null;
    }
}
