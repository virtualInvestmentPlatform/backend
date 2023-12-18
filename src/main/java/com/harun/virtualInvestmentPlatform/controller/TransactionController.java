package com.harun.virtualInvestmentPlatform.controller;

import com.harun.virtualInvestmentPlatform.dto.request.InvestmentItemCountRequest;
import com.harun.virtualInvestmentPlatform.dto.request.TransactionRequest;
import com.harun.virtualInvestmentPlatform.enums.TransactionType;
import com.harun.virtualInvestmentPlatform.exception.MissingInvestmentException;
import com.harun.virtualInvestmentPlatform.exception.NotEnoughBalanceException;
import com.harun.virtualInvestmentPlatform.exception.NotEnoughInvestItemCountException;
import com.harun.virtualInvestmentPlatform.model.User;
import com.harun.virtualInvestmentPlatform.service.TransactionService;
import com.harun.virtualInvestmentPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class TransactionController {
    private TransactionService transactionService;
    private UserService userService;

    @Autowired
    public TransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest transactionRequest,
                                            @RequestHeader("Authorization") String jwtToken) {
        User user = userService.getUser(jwtToken);
        if(user == null)
            return new ResponseEntity<>("Kullanıcı bulunamadı!",HttpStatus.NOT_FOUND);

        if (transactionRequest.getTransactionType() == TransactionType.BUY) {
            try {
                transactionService.addBuyTransaction(transactionRequest,user);
                return new ResponseEntity<>("Alım işlemi başarı ile gerçekleşti",HttpStatus.OK);
            } catch (NotEnoughBalanceException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            } catch (MissingInvestmentException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        } else if (transactionRequest.getTransactionType() == TransactionType.SELL) {
            try {
                transactionService.addSellTransaction(transactionRequest,user);
                return new ResponseEntity<>("Satış işlemi başarı ile gerçekleşti",HttpStatus.OK);
            } catch (MissingInvestmentException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            } catch (NotEnoughInvestItemCountException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("Bir hata meydana geldi!",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/investmentItemCount")
    public ResponseEntity<?> getInvestmentItemCount(@RequestBody InvestmentItemCountRequest investmentItemCountRequest,
                                                    @RequestHeader("Authorization") String jwtToken) {

        User user = userService.getUser(jwtToken);
        if(user == null)
            return new ResponseEntity<>("Kullanıcı bulunamadı!",HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(transactionService.getUserInvestItemCount(
                user,
                investmentItemCountRequest.getInvestmentType(),
                investmentItemCountRequest.getInvestmentCode()));
    }
}
