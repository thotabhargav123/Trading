package org.example.model.stockPriceHistory;

public class StockPriceHistory {
    private String stockSymbol;
    private double price;
    private int year;
    public StockPriceHistory(String stockSymbol, double price, int year){
        this.stockSymbol = stockSymbol;
        this.price = price;
        this.year = year;
    }

    public String getStockSymbol(){
        return  this.stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return ("The Price of the Stock " + this.stockSymbol + " in year " + this.year + " is " + this.price);
    }
}
