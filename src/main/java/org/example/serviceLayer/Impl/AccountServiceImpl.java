package org.example.serviceLayer.Impl;

import org.example.model.transaction.Transaction;
import org.example.repository.TransactionRepository;
import org.example.serviceLayer.AccountService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountServiceImpl implements AccountService {
    TransactionRepository transactionRepository;
    public AccountServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }
    @Override
    public List<Transaction> getTransactionHistory(String username) {
        if(username == null){
            System.out.println("Enter valid Username");
            return null;
        }
        return  transactionRepository.getTransactionsByUsername(username);
    }

    @Override
    public Map<String, Integer> getUserStocks(String username) {
        if (username == null || username.isEmpty()) {
            System.out.println("Enter a valid username.");
            return new HashMap<>();
        }
        List<Transaction> transactions = transactionRepository.getTransactionsByUsername(username);
        Map<String, Integer> userStocks = new HashMap<>();

        for (Transaction transaction : transactions) {
            String stockSymbol = transaction.getStockSymbol();
            int quantity = transaction.getQuantity();
            String transactionType = transaction.getTransactionType();

            if ("SELL".equals(transactionType)) {
                quantity = -quantity; // Subtract quantity for sell transactions
            }

            userStocks.put(stockSymbol, userStocks.getOrDefault(stockSymbol, 0) + quantity);
        }

        return userStocks;
    }

}
