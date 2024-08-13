package org.example.serviceLayer;

import org.example.model.stock.Stock;

import java.util.List;

public interface StockService {
  void addStock(Stock stock);
  Stock getStock(String symbol);
  List<Stock> getAvailableStocks();
  void buyStock(String username, String stockSymbol, int quantity);
  void sellStock(String username, String stockSymbol, int quantity);
  double calculateAverageStockValue(String stockSymbol, int fromYear, int toYear);
  void updateStockPrice(String symbol, double newPrice);
  void updateStockPriceHistory(String symbol, double newPrice, int year);
  void updateStockQuantity(String symbol, int newQuantity);
}

