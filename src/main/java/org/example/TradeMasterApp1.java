package org.example;

import org.example.model.stock.Stock;
import org.example.model.user.User;
import org.example.repository.implementations.StockPriceHistoryRepoImpl;
import org.example.repository.implementations.StockRepositoryImpl;
import org.example.repository.implementations.TransactionRepoImpl;
import org.example.repository.implementations.UserRepositoryImpl;
import org.example.server.DatabaseSchema;
import org.example.serviceLayer.Impl.AccountServiceImpl;
import org.example.serviceLayer.Impl.StockServiceImpl;
import org.example.serviceLayer.Impl.UserServiceImpl;

import java.util.Map;
import java.util.Scanner;

public class TradeMasterApp1 {
    private static User loggedInUser = null;

    public static void main(String[] args) {
        // Create the database schema
        DatabaseSchema.createSchema();
        DatabaseSchema.createDatabase();

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        UserServiceImpl userService = new UserServiceImpl(userRepository);

        StockRepositoryImpl stockRepository = new StockRepositoryImpl();
        TransactionRepoImpl transactionRepository = new TransactionRepoImpl();
        StockPriceHistoryRepoImpl stockPriceHistoryRepository = new StockPriceHistoryRepoImpl();

        StockServiceImpl stockService = new StockServiceImpl(stockRepository, transactionRepository, stockPriceHistoryRepository);
        AccountServiceImpl accountService = new AccountServiceImpl(transactionRepository);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                User loggedInUser = handleLogin(scanner, userService);
                if (loggedInUser != null) {
                    if (userService.isAdmin(loggedInUser)) {
                        adminPanel(scanner, stockService);
                    } else {
                        userPanel(scanner, stockService, accountService, loggedInUser.getUsername());
                    }
                }
            } else if (choice == 2) {
                handleCreateUser(scanner, userService);
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static User handleLogin(Scanner scanner, UserServiceImpl userService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            return userService.login(username, password);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void handleCreateUser(Scanner scanner, UserServiceImpl userService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        String role = "user";

        User newUser = new User(username, password, email, role);
        userService.createUser(newUser);
        System.out.println("User created successfully.");
    }

    private static void adminPanel(Scanner scanner, StockServiceImpl stockService) {
        while (true) {
            System.out.println("1. Add Stock");
            System.out.println("2. Update Stock Price");
            System.out.println("3. Update Stock Quantity");
            System.out.println("4. Update Stock Price History");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter stock symbol: ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter stock price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter stock quantity: ");
                    int quantity = scanner.nextInt();
                    Stock newStock = new Stock(symbol, price, quantity);
                    stockService.addStock(newStock);
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    symbol = scanner.nextLine();
                    System.out.print("Enter new stock price: ");
                    price = scanner.nextDouble();
                    stockService.updateStockPrice(symbol, price);
                    break;
                case 3:
                    System.out.print("Enter stock symbol: ");
                    symbol = scanner.nextLine();
                    System.out.print("Enter new stock quantity: ");
                    quantity = scanner.nextInt();
                    stockService.updateStockQuantity(symbol, quantity);
                    break;
                case 4:
                    System.out.print("Enter stock symbol: ");
                    symbol = scanner.nextLine();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter price: ");
                    price = scanner.nextDouble();
                    stockService.updateStockPriceHistory(symbol, price, year);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void userPanel(Scanner scanner, StockServiceImpl stockService, AccountServiceImpl accountService, String username) {
        while (true) {
            System.out.println("1. Buy Stock");
            System.out.println("2. Sell Stock");
            System.out.println("3. View Available Stocks");
            System.out.println("4. View Transaction History");
            System.out.println("5. View Owned Stocks");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter stock symbol: ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    stockService.buyStock(username, symbol, quantity);
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    symbol = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    stockService.sellStock(username, symbol, quantity);
                    break;
                case 3:
                    int count = 0;
                    for (Stock s : stockService.getAvailableStocks()) {
                        System.out.println("Stock: " + s.getSymbol() + ", Price: " + s.getPrice() + ", Quantity: " + s.getQuantity());
                        count++;
                    }
                    if(count == 0){
                        System.out.println("No Stock is available right now");
                    }
                    break;
                case 4:
                    for (var transaction : accountService.getTransactionHistory(username)) {
                        System.out.println("Transaction: " + transaction.getUsername() + ", Symbol: " + transaction.getStockSymbol() + ", Quantity: " + transaction.getQuantity() + ", Date: " + transaction.getDate() + ", Type: " + transaction.getTransactionType());
                    }
                    break;
                case 5:
                    var userStocks = accountService.getUserStocks(username);
                    for (var entry : userStocks.entrySet()) {
                        System.out.println("Stock: " + entry.getKey() + ", Quantity: " + entry.getValue());
                    }
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}