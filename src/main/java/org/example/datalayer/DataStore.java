package org.example.datalayer;

import org.example.model.stock.Stock;
import org.example.model.stockPriceHistory.StockPriceHistory;
import org.example.model.transaction.Transaction;
import org.example.model.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// singleton class
public class DataStore {
//    private static DataStore instance;
//    private final Map<String, User> users;
//    private final Map<String, Stock> stocks;
//    private final Map<String, List<Transaction>> transactions;
//    private final Map<String, Map<String, Integer>> userStocks;
//    private final Map<String, List<StockPriceHistory>> stockPriceHistory;
//    private DataStore() {
//        System.out.println("In constructor");
//        users = new HashMap<>();
//        stocks = new HashMap<>();
//        transactions = new HashMap<>();
//        userStocks = new HashMap<>();
//        stockPriceHistory = new HashMap<>();
//        System.out.println();
//        stocks.put("mrf", new Stock("mrf", 1500.00));
//        stocks.put("sony", new Stock("sony", 9650.00));
//        stocks.put("sun", new Stock("sun", 4525.00));
//        stocks.put("dukh", new Stock("dukh", 68420.00));
//        stocks.put("sukh", new Stock("sukh", 10000.00));
//        stocks.put("ewww", new Stock("ewww", 10.428));
//        stocks.put("lalala", new Stock("lalala", 9.00));
//        stocks.put("annaw", new Stock("annaw", 1000.557));
//
//        // Initialize stockPriceHistory with the 2022 value being the same as the current stock value
//        stockPriceHistory.put("mrf", List.of(
//                new StockPriceHistory("mrf", 1500.00, 2022),
//                new StockPriceHistory("mrf", 2500.00, 2023),
//                new StockPriceHistory("mrf", 5000.00, 2024)
//        ));
//
//        stockPriceHistory.put("sony", List.of(
//                new StockPriceHistory("sony", 9650.00, 2022),
//                new StockPriceHistory("sony", 10000.00, 2023),
//                new StockPriceHistory("sony", 11000.00, 2024)
//        ));
//
//        stockPriceHistory.put("sun", List.of(
//                new StockPriceHistory("sun", 4525.00, 2022),
//                new StockPriceHistory("sun", 5000.00, 2023),
//                new StockPriceHistory("sun", 5500.00, 2024)
//        ));
//
//        stockPriceHistory.put("dukh", List.of(
//                new StockPriceHistory("dukh", 68420.00, 2022),
//                new StockPriceHistory("dukh", 70000.00, 2023),
//                new StockPriceHistory("dukh", 75000.00, 2024)
//        ));
//
//        stockPriceHistory.put("sukh", List.of(
//                new StockPriceHistory("sukh", 10000.00, 2022),
//                new StockPriceHistory("sukh", 10500.00, 2023),
//                new StockPriceHistory("sukh", 11000.00, 2024)
//        ));
//
//        stockPriceHistory.put("ewww", List.of(
//                new StockPriceHistory("ewww", 10.428, 2022),
//                new StockPriceHistory("ewww", 12.00, 2023),
//                new StockPriceHistory("ewww", 15.00, 2024)
//        ));
//
//        stockPriceHistory.put("lalala", List.of(
//                new StockPriceHistory("lalala", 9.00, 2022),
//                new StockPriceHistory("lalala", 10.00, 2023),
//                new StockPriceHistory("lalala", 12.00, 2024)
//        ));
//
//        stockPriceHistory.put("annaw", List.of(
//                new StockPriceHistory("annaw", 1000.557, 2022),
//                new StockPriceHistory("annaw", 1200.00, 2023),
//                new StockPriceHistory("annaw", 1500.00, 2024)
//        ));
//    }
//    // making sure singleton class present anytime.
//    public static synchronized DataStore getInstance() {
//        if (instance == null) {
//            System.out.println("New Instance created");
//            instance = new DataStore();
//        }
//        System.out.println("Instance sent to you");
//        return instance;
//    }
//
//    public Map<String, User> getUsers() {
//        return users;
//    }
//
//    public Map<String, Stock> getStocks() {
//        return stocks;
//    }
//
//    public Map<String, List<Transaction>> getTransactions() {
//        return transactions;
//    }
//
//    public Map<String, List<StockPriceHistory>> getStockPriceHistory() {
//        return stockPriceHistory;
//    }
//
//    public Map<String, Map<String, Integer>> getUserStocks() {
//        return userStocks;
//    }
//
//        public void addNewUser(User userObj) {
//        String username = userObj.getUsername();
//
//        // Check if user already exists
//        if (users.containsKey(username)) {
//            System.out.println("User with username '" + username + "' already exists.");
//            return; // Exit the method without adding the user
//        }
//
//        // Add new user
//        System.out.println("New user added to database");
//        users.put(username, userObj);
//    }
//}
}
