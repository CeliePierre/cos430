/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project03;

import java.util.ArrayList;

/**
 *
 * @author joelkalala
 */
public class Stock {

    private String companyName;
    private ArrayList<Double> prices;
    private double lowPrice;
    private double highPrice;
    private double netChange;
    private double averagePrice;
    private double standardDeviation;
    private int longestUpwardTrend;
    private double bestGrowthRate;
    private int highPeriod;
    private double highmultiplier;

    // Constructor
    public Stock(String companyName, ArrayList<Double> prices) {
        this.companyName = companyName;
        this.prices = prices;
        calculateStatistics();
        if (bestGrowthRate < 1) {
            bestGrowthRate = Double.NaN;
        }
    }

    // Calculate statistics
    private void calculateStatistics() {
        if (prices.isEmpty()) {
            // Handle empty price list
            return;
        }

        // Initialize variables
        lowPrice = prices.get(0);
        highPrice = prices.get(0);
        double sum = 0;
        int n = prices.size();

        // Calculate low price, high price, and sum for average
        for (double price : prices) {
            lowPrice = Math.min(lowPrice, price);
            highPrice = Math.max(highPrice, price);
            sum += price;
        }

        // Calculate net change and average price
        netChange = highPrice - lowPrice;
        averagePrice = sum / n;

        // Calculate standard deviation
        double sumOfSquares = 0;
        for (double price : prices) {
            sumOfSquares += Math.pow(price - averagePrice, 2);
        }
        standardDeviation = Math.sqrt(sumOfSquares / n);

        // Calculate longest upward trend and best growth rate
        int currentTrend = 1;
        int longestTrend = 1;
        double currentRate = 0;
        double bestRate = 0;

        for (int i = 1; i < n; i++) {
            if (prices.get(i) >= prices.get(i - 1)) {
                currentTrend++;
                currentRate = (prices.get(i) - prices.get(i - 1)) / currentTrend;
                if (currentRate > bestRate) {
                    bestRate = currentRate;
                    longestTrend = currentTrend;
                }
            } else {
                currentTrend = 1;
            }
        }

        longestUpwardTrend = longestTrend - 1; // Length of trend is one less than the count of prices
        bestGrowthRate = bestRate;
    }

    // Getters for the calculated statistics
    public double getLowPrice() {
        return lowPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public double getNetChange() {
        return netChange;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public int getLongestUpwardTrend() {
        return longestUpwardTrend;
    }

    public double getBestGrowthRate() {
        return bestGrowthRate;
    }

    public ArrayList<Double> getPrices() {
        return prices;
    }

    // Getter for company name
    public String getCompanyName() {
        return companyName;
    }

    public double getPerformanceMetric() {
        double currentPrice = prices.get(prices.size() - 1);
        return currentPrice - averagePrice;
    }

    public int getNumberOfPeriods() {
        return prices.size() - 1;
    }
    public void setHighMultiplier(double highMultiplier) {
        this.highmultiplier = highMultiplier;
    }

    public void setHighPeriod(int highPeriod) {
        this.highPeriod = highPeriod;
    }


    public double getTrendAt(int period) {
        if (period >= 0 && period < prices.size() - 1) {
            double currentPrice = prices.get(period + 1); // Current period price
            double previousPrice = prices.get(period); // Previous period price
            return currentPrice/previousPrice; // Percentage change formula
        } else {
            return 0.0; // Return default value or handle invalid period as needed
        }
    }

    public String toString() {
        /*
    String val = String.format("%s\nLowest Price: %.2f\nHighest Price: %.2f\n", 
            companyName,
            lowPrice,
            highPrice);
         */
        String val = String.format("%-25s %-7.2f %-7.2f %-8.2f %-7.2f %-6.2f %-5d %-9.2f %-10.2f %-8d",
                companyName, lowPrice, highPrice,
                netChange, averagePrice, standardDeviation,
                longestUpwardTrend, bestGrowthRate,
                highmultiplier, highPeriod);
        return val;
    }
}
