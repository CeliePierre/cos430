/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project03;

/**
 *
 * @author kalala
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    private Portfolio portfolio;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        //String fileName = args[0];
        Main driver = new Main();
        driver.run(args[0]);
        //ArrayList<Stock> stocks = readDataFromFile(fileName);
        //Portfolio portfolio = new Portfolio();

        //for (Stock stock : stocks) {
        //    portfolio.addStock(stock);
        //}
        //portfolio.calculatePortfolioStatistics();
        //portfolio.displayPortfolioStatistics();
    }

    /**
     * Create a new Portfolio and then start readFile(fileName) this follows the
     * UML suggestion from announcements
     *
     * @param fileName
     */
    public void run(String fileName) {
        portfolio = new Portfolio();
        readDataFromFile(fileName);
    }

    private void readDataFromFile(String fileName) {
        int lineCount = 0; // Variable to count the number of lines read from the file
        //ArrayList<Stock> stocks = new ArrayList<>();

        try ( BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String companyName = null;
            ArrayList<Double> prices = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                lineCount++; // Increment line count for each line read
                //if (companyName == null) {
                companyName = line;
                //} else {
                String[] priceStrings = reader.readLine().split(" ");
                for (String priceString : priceStrings) {
                    try {
                        double price = Double.parseDouble(priceString);
                        prices.add(price);
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing price: " + priceString + ". Skipping this invalid data.");
                        // Optionally, you can log the error or perform any other necessary actions.
                    }
                }

                Stock stock = new Stock(companyName, prices);
                portfolio.add(stock);

                // Reset variables for the next stock
                //companyName = null;
                prices = new ArrayList<>();

            }

            if (lineCount == 0) {
                System.out.println("No data found in the input file.");
                // Add any additional handling or exit the program if needed.
            } else {
                System.out.println(portfolio.toString());
            }
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        }
        //return stocks;
    }
}
