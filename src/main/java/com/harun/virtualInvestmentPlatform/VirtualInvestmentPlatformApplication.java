package com.harun.virtualInvestmentPlatform;

import com.harun.virtualInvestmentPlatform.global.GlobalVariables;
import com.harun.virtualInvestmentPlatform.model.Commodity;
import com.harun.virtualInvestmentPlatform.model.Currency;
import com.harun.virtualInvestmentPlatform.model.Stock;
import com.harun.virtualInvestmentPlatform.service.investDatabase.CommodityService;
import com.harun.virtualInvestmentPlatform.service.investDatabase.CurrencyService;
import com.harun.virtualInvestmentPlatform.service.investDatabase.StockService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class VirtualInvestmentPlatformApplication {
	@Autowired
	private StockService stockService;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private CommodityService commodityService;

	public static void main(String[] args) {
		SpringApplication.run(VirtualInvestmentPlatformApplication.class, args);
	}

	@PostConstruct
	public void init() {
		fetchInvestmentRates();
	}

	@Scheduled(fixedRate = 1200000)
	public void fetchInvestmentRates() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM HH:mm:ss");
		String formattedDateTime = LocalDateTime.now().format(formatter);
		GlobalVariables.LAST_INVESTMENT_DATA_FETCH = formattedDateTime;

		System.out.println("Fetching Investment Rates...");
		List<Stock> stocks = stockService.fetchStockRates();
		if (!stocks.isEmpty())
			stocks.forEach(stock -> {stockService.saveStock(stock);});

		System.out.println("Fetching Currency Rates...");
		List<Currency> currencies = currencyService.fetchCurrencyRates();
		if (!currencies.isEmpty())
			currencies.forEach(currency -> {currencyService.saveCurrency(currency);});

		System.out.println("Fetching Commodity Rates...");
		List<Commodity> commodities = commodityService.fetchCommodityRates();
		if (!commodities.isEmpty())
			commodities.forEach(commodity -> {commodityService.saveCommodity(commodity);});
	}

}
