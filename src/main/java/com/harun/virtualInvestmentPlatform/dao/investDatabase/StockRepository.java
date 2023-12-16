package com.harun.virtualInvestmentPlatform.dao.investDatabase;

import com.harun.virtualInvestmentPlatform.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,String> {
}
