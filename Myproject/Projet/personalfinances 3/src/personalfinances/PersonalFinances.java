/**
 *                   Revision History
 * ***********************************************************************
 * 12.28.2022 Added to the personalfinances project
 * 09.20.2021 Rewrote the compareTo() without the constants
 * 2019 Rewrote the compareTo() method to use a single return.
 * 2015 Written for the agility competition project to show how a class<br>
 * can be written for a special purpose.
 */
package personalfinances;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PersonalFinances {

    private int[] checkNum;
    private Date[] date;
    private String[] description;
    private double[] amount;
    private String[] type;
    private String[] category;
    private String[] columnHeadings;
    private int numberOfRecords;

    /**
     * Purpose: open, read and close the data file. Loads the data into an array
     * of book objects.
     *
     * @param fileName the name of the file to be read in.
     */
    public void readFile(String fileName) {

        try {
            Scanner inFile = new Scanner(new FileReader(fileName));
            if (!inFile.hasNext()) {
                System.err.println("File " + fileName + "is empty.");
                System.exit(3);
            }
            amount = new double[250];
            category = new String[250];
            checkNum = new int[250];
            columnHeadings = new String[6];
            date = new Date[250];
            description = new String[250];
            numberOfRecords = 250;
            type = new String[250];
            numberOfRecords = 0;
            for (int i = 0; i < 6; i++) {
                columnHeadings[i] = inFile.next();
            }

            int i = 0;

            while (inFile.hasNext()) {
                //read in date  Input File Format
                checkNum[i] = inFile.nextInt();
                int month = inFile.nextInt();
                int day = inFile.nextInt();
                int year = inFile.nextInt();
                date[i] = new Date(month, day, year);

                //read in description
                description[i] = inFile.next();

                //read in amount
                amount[i] = inFile.nextDouble();

                //read in type
                type[i] = inFile.next();

                //read in category
                category[i] = inFile.next();

                i++;
            }
            numberOfRecords = i;
            inFile.close();
        } catch (FileNotFoundException e) {
            System.err.println("File " + fileName + " not found.");
            System.exit(1);
        } catch (InputMismatchException e) {
            System.err.println("File " + fileName + " is not in the correct format.");
            System.exit(2);
        } catch (NoSuchElementException e) {
            System.err.println("File " + fileName + " is not in the correct format.");
            System.exit(2);
        }
    }

    public void swap(int i, int j) {
        String temp = category[i];
        category[i] = category[j];
        category[j] = temp;

        temp = description[i];
        description[i] = description[j];
        description[j] = temp;

        temp = type[i];
        type[i] = type[j];
        type[j] = temp;

        int tempint;
        tempint = checkNum[i];
        checkNum[i] = checkNum[j];
        checkNum[j] = tempint;

        Date tempdate;
        tempdate = date[i];
        date[i] = date[j];
        date[j] = tempdate;

        double tempdouble = amount[i];
        amount[i] = amount[j];
        amount[j] = tempdouble;
    }

    public void sortByCategory() {
        for (int i = 0; i < numberOfRecords - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numberOfRecords; j++) {
                if (category[j].compareTo(category[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(i, minIndex);
        }
    }

    /**
     * (f) Write a method that prints out the data in the arrays in a table
     * format. The table should have the following headings: Check Number, Date,
     * Description, Type, Category, Amount. The table should be sorted by
     * category.
     */
    public void printTable() {
        System.out.println("Category\tAmount"
                + "Check Number\tDate\tDescription\tType\t");
        for (int i = 0; i < numberOfRecords; i++) {
            System.out.println(checkNum[i] + "\t" + date[i] + "\t"
                    + description[i] + "\t" + type[i] + "\t" + category[i]
                    + "\t" + amount[i]);
        }
    }

    public void printByCategory(double beginningBalance) {
        sortByCategory();
        double total = 0;
        double subtotal = 0;
        String currentCategory = category[0];
        System.out.println("Category: " + currentCategory);
        for (int i = 0; i < numberOfRecords; i++) {
            if (category[i].equals(currentCategory)) {
                System.out.println(date[i] + " " + description[i] + " "
                        + amount[i]);
                subtotal += amount[i];
            } else {
                System.out.println("Subtotal: " + subtotal);
                total += subtotal;
                subtotal = 0;
                currentCategory = category[i];
                System.out.println("Category: " + currentCategory);
                System.out.println(date[i] + " "
                        + description[i] + " " + amount[i]);
                subtotal += amount[i];
            }
        }
        System.out.println("Subtotal: " + subtotal);
        total += subtotal;
        System.out.println("Total: " + total);
        System.out.println("Beginning Balance: " + beginningBalance);
        System.out.println("Ending Balance: " + (beginningBalance + total));
    }

    /**
     * Write a sort that sorts by check number. Since check number is an
     * integer, thecomparison will look like the original example code, but you
     * still can’t use thelength of the array. you will use the same swap method
     * that you already wrote.(b) Write a method that will print the
     * transactions using formatted Strings.
     */
    public void sortByCheckNum() {
        for (int i = 0; i < numberOfRecords - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numberOfRecords; j++) {
                if (checkNum[j] < checkNum[minIndex]) {
                    minIndex = j;
                }
            }
            swap(i, minIndex);
        }
    }

    public void printByCheckNum() {
        sortByCheckNum();
        for (int i = 0; i < numberOfRecords; i++) {
            System.out.printf("%d %s %s %.2f %s %s\n", checkNum[i], date[i],
                    description[i], amount[i],
                    type[i], category[i]);
        }

    }

    public void run(String[] args) {
        readFile(args[0]);
        printByCheckNum();

    }

    /**
     * Add calls to both methods to run()
     *
     * @param args.
     */

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage java PersonalFinances input");
            System.exit(1);
        }
        PersonalFinances pf = new PersonalFinances();
        pf.run(args);

    }

}
