package org.example.repository;

import org.example.model.stockPriceHistory.StockPriceHistory;

import java.util.List;

public interface StockPriceHistoryRepository {
    void addStockPriceHistory(StockPriceHistory stockPriceHistory);
    List<StockPriceHistory> getStockPriceHistory(String stockSymbol, int fromYear, int toYear);
}
