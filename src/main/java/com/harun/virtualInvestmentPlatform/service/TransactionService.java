package com.harun.virtualInvestmentPlatform.service;

import com.harun.virtualInvestmentPlatform.dao.TransactionRepository;
import com.harun.virtualInvestmentPlatform.dao.UserRepository;
import com.harun.virtualInvestmentPlatform.dao.investDatabase.CommodityRepository;
import com.harun.virtualInvestmentPlatform.dao.investDatabase.CurrencyRepository;
import com.harun.virtualInvestmentPlatform.dao.investDatabase.StockRepository;
import com.harun.virtualInvestmentPlatform.dto.request.TransactionRequest;
import com.harun.virtualInvestmentPlatform.enums.InvestmentType;
import com.harun.virtualInvestmentPlatform.enums.TransactionType;
import com.harun.virtualInvestmentPlatform.exception.MissingInvestmentException;
import com.harun.virtualInvestmentPlatform.exception.NotEnoughBalanceException;
import com.harun.virtualInvestmentPlatform.exception.NotEnoughInvestItemCountException;
import com.harun.virtualInvestmentPlatform.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TransactionService {
    private CommodityRepository commodityRepository;
    private CurrencyRepository currencyRepository;
    private StockRepository stockRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              CommodityRepository commodityRepository,
                              StockRepository stockRepository,
                              CurrencyRepository currencyRepository,
                              UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.commodityRepository = commodityRepository;
        this.stockRepository = stockRepository;
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
    }

    private void setUserBalance(User user, long newBalance) {
        user.setBalance(newBalance);
        userRepository.save(user);
    }

    private double getUserInvestItemCount(User user, InvestmentType investmentType, String investCode) {
        double itemCount = 0;

        ArrayList<Transaction> buyTransactions =
                (ArrayList<Transaction>) transactionRepository.
                        findByUserIdAndTransactionTypeAndInvestmentTypeAndInvestmentCode(
                                user.getId(),
                                TransactionType.BUY,
                                investmentType,
                                investCode);

        ArrayList<Transaction> sellTransactions =
                (ArrayList<Transaction>) transactionRepository.
                        findByUserIdAndTransactionTypeAndInvestmentTypeAndInvestmentCode(
                                user.getId(),
                                TransactionType.SELL,
                                investmentType,
                                investCode);

        for (Transaction t : buyTransactions) {
            itemCount += t.getAmount();
        }

        for (Transaction t : sellTransactions) {
            itemCount -= t.getAmount();
        }

        return itemCount;
    }

    private double getInvestmentIndividualCost(InvestmentType investmentType , String investmentCode) {
        switch (investmentType) {
            case STOCK:
                Optional<Stock> optionalStock = stockRepository.findById(investmentCode);
                if (optionalStock.isPresent()){
                    Stock stock = optionalStock.get();
                    return stock.getLastprice();
                }
                break;
            case CURRENCY:
                Optional<Currency> optionalCurrency = currencyRepository.findById(investmentCode);
                if (optionalCurrency.isPresent()){
                    Currency currency = optionalCurrency.get();
                    return currency.getBuying();
                }
                break;
            case COMMODITY:
                Optional<Commodity> optionalCommodity = commodityRepository.findById(investmentCode);
                if (optionalCommodity.isPresent()){
                    Commodity commodity = optionalCommodity.get();
                    return commodity.getBuying();
                }
                break;
        }

        return -1;
    }

    private double isBuyTransactionAddable(TransactionRequest transactionRequest, long userBalance) {
        double investmentIndividualCost = getInvestmentIndividualCost(
                transactionRequest.getInvestmentType(),
                transactionRequest.getInvestmentCode()
        );

        if (investmentIndividualCost == -1)
            return -1;

        double cost = transactionRequest.getAmount() * investmentIndividualCost;

        if (userBalance >= cost)
            return investmentIndividualCost;

        return 0;
    }

    private double isSellTransactionAddable(TransactionRequest transactionRequest, double userInvestItemCount) {
        double investmentIndividualCost = getInvestmentIndividualCost(
                transactionRequest.getInvestmentType(),
                transactionRequest.getInvestmentCode()
        );

        if (investmentIndividualCost == -1)
            return -1;


        if (transactionRequest.getAmount() <= userInvestItemCount)
            return investmentIndividualCost;

        return 0;
    }

    public Transaction addBuyTransaction(TransactionRequest transactionRequest, User user)
            throws NotEnoughBalanceException, MissingInvestmentException {

        if (transactionRequest.getTransactionType() != TransactionType.BUY)
            throw new MissingInvestmentException();

        double investmentValue = isBuyTransactionAddable(transactionRequest, user.getBalance());

        if (investmentValue == -1)
            throw new MissingInvestmentException();
        else if (investmentValue == 0)
            throw new NotEnoughBalanceException();

        Transaction transaction = new Transaction(
                user,
                transactionRequest.getInvestmentType(),
                transactionRequest.getTransactionType(),
                transactionRequest.getInvestmentCode(),
                transactionRequest.getAmount(),
                investmentValue,
                LocalDateTime.now());

        long cost = (long) (investmentValue * transactionRequest.getAmount());
        setUserBalance(user , user.getBalance() - cost);

        return transactionRepository.save(transaction);
    }

    public Transaction addSellTransaction(TransactionRequest transactionRequest, User user)
            throws MissingInvestmentException, NotEnoughInvestItemCountException {

        if (transactionRequest.getTransactionType() != TransactionType.SELL)
            throw new MissingInvestmentException();

        double userInvestItemCount = getUserInvestItemCount(user,transactionRequest.getInvestmentType(),transactionRequest.getInvestmentCode());
        double investmentValue = isSellTransactionAddable(transactionRequest, userInvestItemCount);

        if (investmentValue == -1)
            throw new MissingInvestmentException();
        else if(investmentValue == 0)
            throw new NotEnoughInvestItemCountException();

        Transaction transaction = new Transaction(
                user,
                transactionRequest.getInvestmentType(),
                transactionRequest.getTransactionType(),
                transactionRequest.getInvestmentCode(),
                transactionRequest.getAmount(),
                investmentValue,
                LocalDateTime.now());

        long earning = (long) (investmentValue * transactionRequest.getAmount());
        setUserBalance(user , user.getBalance() + earning);

        return transactionRepository.save(transaction);
    }

}
