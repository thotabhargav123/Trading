package org.example.repository;

import org.example.model.transaction.Transaction;

import java.util.List;

public interface TransactionRepository {
    void createTransaction(Transaction transaction);
    List<Transaction> getTransactionsByUsername(String username);
}
