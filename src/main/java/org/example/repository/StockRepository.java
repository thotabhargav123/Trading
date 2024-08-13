package org.example.repository;

import org.example.model.stock.Stock;

import java.util.List;
import java.util.Optional;

public interface StockRepository {
    public void createStock(Stock stock);
    Stock getStock(String symbol);
    List<Stock> getAllStocks();
    void updateStock(Stock stock);
    void deleteStock(String symbol);
}
