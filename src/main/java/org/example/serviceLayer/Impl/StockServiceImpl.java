package org.example.serviceLayer.Impl;

import org.example.model.stock.Stock;
import org.example.model.stockPriceHistory.StockPriceHistory;
import org.example.model.transaction.Transaction;
import org.example.repository.StockPriceHistoryRepository;
import org.example.repository.StockRepository;
import org.example.repository.TransactionRepository;
import org.example.serviceLayer.StockService;

import java.time.LocalDateTime;
import java.util.List;

public class StockServiceImpl implements StockService {
    StockRepository stockRepository;
    StockPriceHistoryRepository stockPriceHistoryRepository;
    TransactionRepository transactionRepository;
    public StockServiceImpl(StockRepository stockRepository, TransactionRepository transactionRepository, StockPriceHistoryRepository stockPriceHistoryRepository){
        this.transactionRepository = transactionRepository;
        this.stockRepository = stockRepository;
        this.stockPriceHistoryRepository = stockPriceHistoryRepository;
    }
    @Override
    public void addStock(Stock stock) {
        if(stock != null){
            stockRepository.createStock(stock);
        }
        else{
            System.out.println("Enter Correct Stock Values");
        }

    }

    @Override
    public Stock getStock(String symbol) {
       if(symbol == null){
           System.out.println("Enter valid Stock Symbol");
       }
       Stock stock = stockRepository.getStock(symbol);
       if(stock == null){
           System.out.println("Enter valid Stock Symbol");
       }
       return stock;
    }

    @Override
    public List<Stock> getAvailableStocks() {
        return stockRepository.getAllStocks();
    }

    @Override
    public void buyStock(String username, String stockSymbol, int quantity) {
        Stock stock = stockRepository.getStock(stockSymbol);

        if (stock.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock available.");
        }
        // Create transaction
        Transaction transaction = new Transaction(username, stockSymbol, quantity, LocalDateTime.now(), "BUY");
        transactionRepository.createTransaction(transaction);

        // Update stock quantity
        stock.setQuantity(stock.getQuantity() - quantity);
        stockRepository.updateStock(stock);
    }

    @Override
    public void sellStock(String username, String stockSymbol, int quantity) {
        List<Transaction> userTransactions = transactionRepository.getTransactionsByUsername(username);
        int userStockQuantity = 0;

        for (Transaction transaction : userTransactions) {
            if (transaction.getStockSymbol().equals(stockSymbol)) {
                if (transaction.getTransactionType().equals("BUY")) {
                    userStockQuantity += transaction.getQuantity();
                } else if (transaction.getTransactionType().equals("SELL")) {
                    userStockQuantity -= transaction.getQuantity();
                }
            }
        }

        if (userStockQuantity < quantity) {
            System.out.println("Not enough stock to sell.");
        }

        // Proceed with selling the stock and updating the database
        Transaction transaction = new Transaction(username, stockSymbol, quantity, LocalDateTime.now(), "SELL");
        transactionRepository.createTransaction(transaction);

        // Update stock quantity
        Stock stock = getStock(stockSymbol);
        stock.setQuantity(stock.getQuantity() + quantity);
        stockRepository.updateStock(stock);
    }


    @Override
    public double calculateAverageStockValue(String stockSymbol, int fromYear, int toYear) {
        List<StockPriceHistory> history = stockPriceHistoryRepository.getStockPriceHistory(stockSymbol, fromYear, toYear);
        return history.stream().mapToDouble(StockPriceHistory::getPrice).average().orElse(0);
    }

    @Override
    public void updateStockPrice(String symbol, double newPrice) {
        Stock stock = stockRepository.getStock(symbol);
        if (stock != null) {
            stock.setPrice(newPrice);
            stockRepository.updateStock(stock);
            updateStockPriceHistory(symbol, newPrice,2024);
        } else {
            System.out.println("Stock not found.");
        }
    }

    // New method to update stock price history
    @Override
    public void updateStockPriceHistory(String symbol, double newPrice, int year) {
        StockPriceHistory historyEntry = new StockPriceHistory(symbol, newPrice, year);
        stockPriceHistoryRepository.addStockPriceHistory(historyEntry);
    }

    @Override
    public void updateStockQuantity(String symbol, int quantity){
        Stock stock = stockRepository.getStock(symbol);
        if (stock != null) {
            stock.setPrice(quantity);
            stockRepository.updateStock(stock);
        } else {
            System.out.println("Stock not found.");
        }
    }
}
