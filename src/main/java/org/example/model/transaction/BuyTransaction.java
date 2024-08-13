package org.example.model.transaction;

import java.time.LocalDateTime;
import java.util.Date;

public class BuyTransaction extends Transaction {
    public BuyTransaction(String username, String stockSymbol, int quantity, LocalDateTime date){
        super(username, stockSymbol, quantity, date,"Buy");
    }
}
