package com.harun.virtualInvestmentPlatform.exception;

public class NotEnoughInvestItemCountException extends Exception{

    @Override
    public String getMessage() {
        return "Yeterli miktara sahip değilsiniz!";
    }
}
