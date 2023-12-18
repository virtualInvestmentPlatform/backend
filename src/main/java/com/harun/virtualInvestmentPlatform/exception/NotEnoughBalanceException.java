package com.harun.virtualInvestmentPlatform.exception;

public class NotEnoughBalanceException extends Exception{
    @Override
    public String getMessage() {
        return "Yeterli miktarda paranız yok!";
    }
}
