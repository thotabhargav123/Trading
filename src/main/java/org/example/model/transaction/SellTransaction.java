package org.example.model.transaction;

import java.time.LocalDateTime;
import java.util.Date;

public class SellTransaction extends Transaction {
    public SellTransaction(String username, String stockSymbol, int quantity, LocalDateTime date) {
        super(username, stockSymbol, quantity, date, "Sell");
    }
}
