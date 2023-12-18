package com.harun.virtualInvestmentPlatform.exception;

public class MissingInvestmentException extends Exception{
    @Override
    public String getMessage() {
        return "Yatırım aracı veritabanında bulunamadı";
    }
}
