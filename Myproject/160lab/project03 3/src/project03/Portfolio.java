/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project03;

/**
 *
 * @author kalala
 */
import java.util.ArrayList;

public class Portfolio {

    String[] bestStockByPeriod;
    double multiplier;
    public double highmultiplier;
    public int highPeriod;

    private static Iterable<Stock> getStocks() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private ArrayList<Stock> stocks;
    private double bestRate;
    private int periodOfBestRate;

    // Constructor
    public Portfolio() {
        stocks = new ArrayList<>();
    }

    // Method to add a stock to the portfolio
    public void add(Stock stock) {
        stocks.add(stock);
    }

    // ... other methods and fields ...
    // Method to calculate the total value of the portfolio
    public double calculateTotalValue() {
        double totalValue = 0.0;
        for (Stock stock : stocks) {
            ArrayList<Double> prices = stock.getPrices();
            double lastPrice = prices.get(prices.size() - 1);
            totalValue += lastPrice * stock.getNetChange();
        }
        return totalValue;
    }

    // Method to calculate the average price of stocks in the portfolio
    public double calculateAveragePrice() {
        if (stocks.isEmpty()) {
            return 0.0; // Handle the case where there are no stocks in the portfolio
        }

        double totalPrices = 0.0;
        int totalStocks = 0;

        for (Stock stock : stocks) {
            ArrayList<Double> prices = stock.getPrices();
            if (!prices.isEmpty()) {
                totalPrices += prices.get(prices.size() - 1); // Assuming the last price is the current price
                totalStocks++;
            }
        }

        if (totalStocks == 0) {
            return 0.0; // Handle the case where there are no valid stock prices
        }

        return totalPrices / totalStocks;
    }
    // Method to find the best stock for a specific period
    /*
    public Stock findBestStockForPeriod(int period) {
        if (period < 0 || period >= prices.size() - 1) {
            // Invalid period, handle this case accordingly (throw an exception, return null, etc.)
            return null;
        }

        Stock bestStock = stocks.get(0); // Initialize with the first stock
        double maxPercentageChange = calculatePercentageChange(bestStock, period);

        // Iterate through other stocks and find the one with the highest percentage change
        for (int i = 1; i < stocks.size(); i++) {
            Stock currentStock = stocks.get(i);
            double percentageChange = calculatePercentageChange(currentStock, period);
            if (percentageChange > maxPercentageChange) {
                maxPercentageChange = percentageChange;
                bestStock = currentStock;
            }
        }

        return bestStock;
    }
    */

    // Method to calculate percentage change for a specific period
    private double calculatePercentageChange(Stock stock, int period) {
        double currentPrice = stock.getPrices().get(period + 1); // Current period price
        double previousPrice = stock.getPrices().get(period); // Previous period price
        return ((currentPrice - previousPrice) / previousPrice) * 100; // Percentage change formula
    }

    // ... other methods ...
    // Method to calculate the maximum price among stocks in the portfolio
    public double calculateMaxPrice() {
        double maxPrice = Double.MIN_VALUE;
        for (Stock stock : stocks) {
            ArrayList<Double> prices = stock.getPrices();
            if (!prices.isEmpty()) {
                double lastPrice = prices.get(prices.size() - 1); // Assuming the last price is the current price
                maxPrice = Math.max(maxPrice, lastPrice);
            }
        }
        return maxPrice;
    }

    // Method to calculate the minimum price among stocks in the portfolio
    public double calculateMinPrice() {
        double minPrice = Double.MAX_VALUE;
        for (Stock stock : stocks) {
            ArrayList<Double> prices = stock.getPrices();
            if (!prices.isEmpty()) {
                double lastPrice = prices.get(prices.size() - 1); // Assuming the last price is the current price
                minPrice = Math.min(minPrice, lastPrice);
            }
        }
        return minPrice;
    }

    // ... other methods ...
    // Method to calculate best rate and period of best rate for the entire portfolio
    public void calculatePortfolioStatistics() {
        bestRate = Double.MIN_VALUE;
        periodOfBestRate = -1;
        highmultiplier = Double.MIN_VALUE;
        highPeriod  = -1;
       

        for (Stock stock : stocks) {
            ArrayList<Double> prices = stock.getPrices();
            for (int i = 0; i < prices.size() - 1; i++) {
                double rate = prices.get(i + 1) / prices.get(i);
                if (rate > bestRate) {
                    bestRate = rate;
                    periodOfBestRate = i;
                }
                if(rate > highmultiplier){
                    highmultiplier = rate;
                    highPeriod = i;
                }
            }
            //stock.setHighMultiplier(highmultiplier);
            //stock.setHighPeriod(highPeriod);
        }
    }

    // Method to set the calculated best rate
    public void setBestRate(double bestRate) {
        this.bestRate = bestRate;
    }

    // Method to set the period of best rate
    public void setPeriodOfBestRate(int periodOfBestRate) {
        this.periodOfBestRate = periodOfBestRate;
    }

    // Method to display statistics for the entire portfolio
    public void calculateBestStocksByPeriod() {
        int maxNumPeriods = -1;
        // for each stock
        for (int i = 0; i < stocks.size(); i++) {
            // Find the maximum number of periods
            if (maxNumPeriods < stocks.get(i).getNumberOfPeriods()) {
                maxNumPeriods = stocks.get(i).getNumberOfPeriods();
            }
        }

        // For each period
        // for each stock
        // if the period index is not greater than numberOfPeriods
        // get the best trend and put the company name in an array of size maxNumPeriods
        bestStockByPeriod = new String[maxNumPeriods];
        multiplier = 1;
        for (int period = 0; period < maxNumPeriods; period++) {
            double bestTrend = 0;
            // Iterate over stocks
            for (Stock stock : stocks) {
                // Check if the period index is valid for this stock
                if (period < stock.getNumberOfPeriods()) {
                    // Get the best trend for the current period for this stock
                    // Compare and store the company name if this trend is the best so far

                    if (bestTrend < stock.getTrendAt(period)) {
                        bestTrend = stock.getTrendAt(period);
                        //stock.setBestTrendForPeriod(period, bestTrend);
                        bestStockByPeriod[period] = stock.getCompanyName();
                    }
                }
            }
            multiplier *= bestTrend;
        }
        
        // Concatenate the array of best stocks line by line
        /*
    System.out.printf("%-25s%-7s%-7s%-8s%-7s%-6s%-5s%-9s%-10s%-8s\n", 
            "Company", "Low", "Hi", "Net", "Ave", "Dev", "Lng", "BestTrRt", "BestPerRt", "BestPer");

    // Display statistics for each stock in the portfolio
    for (Stock stock : stocks) {
        System.out.printf("%-25s%-7.2f%-7.2f%-8.2f%-7.2f%-6.2f%-5d%-9.2f%-10.2f%-8d\n",
                stock.getCompanyName(), stock.getLowPrice(), stock.getHighPrice(),
                stock.getNetChange(), stock.getAveragePrice(), stock.getStandardDeviation(),
                stock.getLongestUpwardTrend(), stock.getBestGrowthRate(),
                stock.getLongestUpwardTrend(), stock.getLongestUpwardTrend());
    }
         */
    }

    /**
     * System.out.println("\nStock-wise Statistics:"); // Display statistics for
     * each stock in the portfolio for (Stock stock : stocks) {
     * System.out.println("Company Name: " + stock.getCompanyName());
     * System.out.println("Low Price: " + stock.getLowPrice());
     * System.out.println("High Price: " + stock.getHighPrice());
     * System.out.println("Net Change: " + stock.getNetChange());
     * System.out.println("Average Price: " + stock.getAveragePrice());
     * System.out.println("Standard Deviation: " +
     * stock.getStandardDeviation()); System.out.println("Longest Upward Trend:
     * " + stock.getLongestUpwardTrend()); System.out.println("Best Growth Rate:
     * " + stock.getBestGrowthRate()); System.out.println(); } }
     *
     */
    
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%-25s %-7s %-7s %-8s %-7s %-6s %-5s %-9s %-10s\n",
                                        "Company","Low","High", "Average",
                                        "StdDev", "Long", "BestTrRt", "BestPerRt",
                                        "BestPer"));
        // Concatenate toString() representation of each stock in the portfolio
        for (Stock stock : stocks) {
            result.append(stock.toString()).append("\n");
        }
        /*
        // Calculate and concatenate portfolio statistics
        double totalValue = calculateTotalValue(); // You need to implement this method
        double averagePrice = calculateAveragePrice(); // You need to implement this method
        double maxPrice = calculateMaxPrice(); // You need to implement this method
        double minPrice = calculateMinPrice(); // You need to implement this method

        result.append("Portfolio Statistics:\n");
        result.append("Total Value: ").append(totalValue).append("\n");
        result.append("Average Price: ").append(averagePrice).append("\n");
        result.append("Maximum Price: ").append(maxPrice).append("\n");
        result.append("Minimum Price: ").append(minPrice).append("\n");
        */
        calculateBestStocksByPeriod();
        result.append(String.format("Picking the best stocks, the multiplier would be %.2f\n", multiplier));

        result.append("The best companies to pick, period by period, were as follows\n");
        for (int i = 0; i < bestStockByPeriod.length; i++) {
            result.append(bestStockByPeriod[i] + "\n");
        }
        
        return result.toString();
    }

}
