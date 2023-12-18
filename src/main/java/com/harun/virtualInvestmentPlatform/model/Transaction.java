package com.harun.virtualInvestmentPlatform.model;

import com.harun.virtualInvestmentPlatform.enums.TransactionType;
import com.harun.virtualInvestmentPlatform.enums.InvestmentType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private InvestmentType investmentType;

    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private String investmentCode;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private double investmentValue;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Transaction() {

    }

    public Transaction(User user,
                       InvestmentType investmentType,
                       TransactionType transactionType,
                       String investmentCode,
                       double amount,
                       double investmentValue,
                       LocalDateTime timestamp) {
        this.id = id;
        this.user = user;
        this.investmentType = investmentType;
        this.transactionType = transactionType;
        this.investmentCode = investmentCode;
        this.amount = amount;
        this.investmentValue = investmentValue;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InvestmentType getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(InvestmentType investmentType) {
        this.investmentType = investmentType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getInvestmentCode() {
        return investmentCode;
    }

    public void setInvestmentCode(String investmentCode) {
        this.investmentCode = investmentCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInvestmentValue() {
        return investmentValue;
    }

    public void setInvestmentValue(double investmentValue) {
        this.investmentValue = investmentValue;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(amount, that.amount) == 0 && Double.compare(investmentValue, that.investmentValue) == 0 && Objects.equals(id, that.id) && Objects.equals(user, that.user) && investmentType == that.investmentType && transactionType == that.transactionType && Objects.equals(investmentCode, that.investmentCode) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, investmentType, transactionType, investmentCode, amount, investmentValue, timestamp);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user=" + user +
                ", investmentType=" + investmentType +
                ", transactionType=" + transactionType +
                ", investmentCode='" + investmentCode + '\'' +
                ", amount=" + amount +
                ", investmentValue=" + investmentValue +
                ", timestamp=" + timestamp +
                '}';
    }
}
