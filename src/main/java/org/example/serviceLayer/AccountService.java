package org.example.serviceLayer;

import org.example.model.transaction.Transaction;

import java.util.List;
import java.util.Map;

public interface AccountService {
    List<Transaction> getTransactionHistory(String username);
    Map<String, Integer> getUserStocks(String username);
}