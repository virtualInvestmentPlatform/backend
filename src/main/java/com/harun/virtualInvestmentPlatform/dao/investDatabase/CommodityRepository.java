package com.harun.virtualInvestmentPlatform.dao.investDatabase;

import com.harun.virtualInvestmentPlatform.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository extends JpaRepository<Commodity,String> {
}
