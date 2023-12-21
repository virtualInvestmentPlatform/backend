package com.harun.virtualInvestmentPlatform.dao;

import com.harun.virtualInvestmentPlatform.enums.InvestmentType;
import com.harun.virtualInvestmentPlatform.enums.TransactionType;
import com.harun.virtualInvestmentPlatform.model.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByUserId(Long userId);

    List<Transaction> findByUserIdAndTransactionTypeAndInvestmentTypeAndInvestmentCode(
            Long userId, TransactionType transactionType, InvestmentType investmentType, String investmentCode);

    List<Transaction> findByUserIdAndInvestmentType(Long userId, InvestmentType investmentType, Sort sort);
}
