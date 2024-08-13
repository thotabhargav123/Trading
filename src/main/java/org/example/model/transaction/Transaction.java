package org.example.model.transaction;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Transaction {
    private String username;
    private String stockSymbol;
    private int quantity;
    private LocalDateTime date;
    private String transactionType;

    public Transaction(String username, String stockSymbol, int quantity, LocalDateTime date, String transactionType) {
        this.username = username;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.date = date;
        this.transactionType = transactionType;
    }

    // Getters and Setters

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%s %s %s %d shares on %s",
                username, transactionType, stockSymbol, quantity, sdf.format(date));
    }
}
