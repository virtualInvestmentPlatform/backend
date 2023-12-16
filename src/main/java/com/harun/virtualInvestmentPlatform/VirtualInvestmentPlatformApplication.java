package com.harun.virtualInvestmentPlatform;

import com.harun.virtualInvestmentPlatform.model.Stock;
import com.harun.virtualInvestmentPlatform.service.investDatabase.StockService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@SpringBootApplication
public class VirtualInvestmentPlatformApplication {
	@Autowired
	private StockService stockService;

	public static void main(String[] args) {
		SpringApplication.run(VirtualInvestmentPlatformApplication.class, args);
	}

	@PostConstruct
	public void init() {
		fetchInvestmentRates();
	}

	@Scheduled(fixedRate = 1200000)
	public void fetchInvestmentRates() {
		System.out.println("Fetching Investment Rates...");
		List<Stock> stocks = stockService.fetchStockRates();
		if (!stocks.isEmpty())
			stocks.forEach(stock -> {stockService.saveStock(stock);});
	}

}
