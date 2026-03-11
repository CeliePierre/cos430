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

import java.util.Arrays;
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
     *
     * PersonalFinances comment
     *
     * The readFile method takes a single parameter fileName, 1. which is the
     * name of the file to be read. The code then declares several arrays for
     * storing data from the file: amount, category, checkNum, columnHeadings,
     * date, description, type. The numberOfRecords variable is also declared
     * and set to 250. The next line reads the first line of the file using
     * inFile.nextLine() and stores it in the data variable. This code only
     * reads the first line of the file, so additional code would be needed to
     * read the rest of the data.
     *
     * . The i variable is declared and set to 0. The while loop continues until
     * the inFile.hasNextLine() method returns false, meaning that there are no
     * more lines to read in the file. Inside the loop, a line of the file is
     * read using inFile.nextLine() and stored in the input variable. The line
     * is then split on the comma using the split method and stored in the my
     * data array.
     *
     * 2.try-catch block. The numberOfRecords variable is updated with the value
     * of i, which was incremented in the while loop to keep track of the number
     * of records read from the file.
     *
     * 3.the method swaps the values of several arrays. The method takes two
     * integer arguments i and j, which are the indices of the elements to be
     * swapped.
     *
     * 4 The method first iterates over the category array from index 0 to
     * numberOfRecords - 1. In each iteration, the method finds the index of the
     * minimum value in the sub-array from i + 1 to numberOfRecords using a
     * nested loop and a comparison between the values of the category array.
     *
     * 5. This method printTable() is used to display the contents of the
     * checkbook in a tabular form. read on the below
     *
     * 6. The method takes the beginningBalance as a parameter and prints the
     * beginning balance, total and ending balance (beginning balance + total)
     *
     * 7 The method first initializes a variable minIndex to i and then iterates
     * through the rest of the elements (from i + 1 to numberOfRecords) to find
     * the smallest check number.
     *
     * 8. The print method is used to format the output, where the format
     * specifiers such as %d and %.2f
     *
     * 9. This run method Reads the input file specified in the first command
     * line argument using the readFile method
     *
     * 10 This is the complete PersonalFinances class in Java, which can be used
     * to process personal financial records. The class has several methods for
     * reading data from a file, sorting and printing the records based on check
     * number and category. The run method reads the input file and sorts the
     * data in two ways, first by category, and then by check number, and prints
     * the records in both sorted formats. The main method starts the program by
     * checking that the input file name has been passed as a command line
     * argument. If the input file name is not provided, it displays an error
     * message and terminates the program.
     *
     */
    public void readFile(String fileName) {
        //System.out.println("start read");
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

            type = new String[250];
            numberOfRecords = 0;
            String data = inFile.nextLine();
            //read all line
            columnHeadings = data.split(",");
            //System.out.println(Arrays.toString(columnHeadings));         

            /**
             * The i variable is declared and set to 0. The while loop continues
             * until the i and j, which are the indices of the elements to be
             * swapped. inFile.hasNextLine() method returns false, meaning that
             * there are no more lines to read in the file.
             *
             */
            int i = 0;
            //System.out.println("start while loop");
            inFile.useDelimiter("[/, \t\n\r]+");
            while (inFile.hasNext()) {
                // read the line and split on the comma
                //String input = inFile.nextLine();
                // String[] mydata = input.split(",");

                //System.out.println(Arrays.toString(mydata));
                // convert the string int
                checkNum[i] = inFile.nextInt();
                // split the date to month and year
                //tring[] mydate = mydata[1].split("/");
                int month = inFile.nextInt();
                int day = inFile.nextInt();
                int year = inFile.nextInt();
                date[i] = new Date(month, day, year);
                description[i] = inFile.next().replaceAll("_", " ");
                // convert string on the double 
                amount[i] = inFile.nextDouble();
                type[i] = inFile.next();
                category[i] = inFile.next().replaceAll("_", " ");
                //System.out.println(checkNum[i]);

                i++;
            }
            numberOfRecords = i;
            inFile.close();

            /**
             * 2
             * try-catch block. The numberOfRecords variable is updated with the
             * value of i, which was incremented in the while loop to keep track
             * of the number of records read from the file.
             *
             */
        } catch (FileNotFoundException e) {
            System.err.println("File " + fileName + " not found.");
            System.exit(1);
        } catch (InputMismatchException e) {
            System.err.println("File " + fileName + " is not in the correct format.");
            System.exit(2);
        } catch (NoSuchElementException e) {
            System.err.println("File " + fileName + " is not in the correct format.");
            System.exit(3);
        }
    }

    /**
     * 3
     * that swaps the values of several arrays. The method takes two integer
     * arguments i and j, which are the indices of the elements to be swapped.
     *
     */
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

    /**
     * 4
     *
     * The method first iterates over the category array from index 0 to
     * numberOfRecords - 1. In each iteration, the method finds the index of the
     * minimum value in the sub-array from i + 1 to numberOfRecords using a
     * nested loop and a comparison between the values of the category array. If
     * the minimum index is different from i, the method calls the swap method
     * to swap the values of the arrays at indices i and minIndex.
     *
     */
    public void sortByCategory() {
        for (int i = 0; i < numberOfRecords - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numberOfRecords; j++) {
                if (category[j].compareTo(category[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(i, minIndex);
            }
        }
    }

    /**
     * 5
     * This method printTable() is used to display the contents of the checkbook
     * in a tabular form. It first prints the headings of each column, and then
     * for each record in the checkbook, it prints its details in a formatted
     * manner using the System.out.print statement. The String. format method is
     * used to format the output string that represents a record. The output
     * string consists of the check number, date, description, type, category,
     * and amount of the transaction, separated by tabs
     */
    public void printTable() {
        System.out.println("Category\tAmount"
                + "Check Number\tDate\tDescription\tType\t");
        for (int i = 0; i < numberOfRecords; i++) {

            /*
            System.out.println(checkNum[i] + "\t" + date[i] + "\t"
                    + description[i] + "\t" + type[i] + "\t" + category[i]
                    + "\t" + amount[i]);*/
            String outStr = String.format("%d %-10s %10s %-10s %10s %10.2f\n", checkNum[i],
                    date[i], description[i], type[i], category[i], amount[i]);
            System.out.print(outStr);

        }
    }

    /**
     * 6
     * The method takes the beginningBalance as a parameter and prints the
     * beginning balance, total and ending balance (beginning balance + total).
     */
    public void printByCategory(double beginningBalance) {

        sortByCategory();
        double totalcredit = 0;
        double totaldebit = 0;
        double total = 0;
        double subtotal = 0;

        String currentCategory = category[0];

        System.out.printf("", "Category", currentCategory);
        System.out.printf("%5s %10s %20s   %30s\n", "Check#",
                "Date", "Description", "Amount", "type", "category");
        for (int i = 0; i < numberOfRecords; i++) {

            if (type[i].equals("debit")) {
                totaldebit += amount[i];
            }
            if (type[i].equals("credit")) {
                totalcredit += amount[i];
            }

            if (category[i].equals(currentCategory)) {

                String outStr = String.format("%5d %15s %20s   %26.2f\n", checkNum[i],
                        date[i], description[i], amount[i]);

                System.out.print(outStr);

                subtotal += amount[i];
            } else {
                System.out.printf("Subtotal:  %.2f\n", +subtotal);

                subtotal = 0;
                currentCategory = category[i];
                String outStr = String.format("%5d %15s %20s   %26s.2f\n", checkNum[i],
                        date[i], description[i], amount[i]);
                

                currentCategory = category[i];

                System.out.printf("%5s:%10s\n", "Category", currentCategory);
                System.out.println(date[i] + " "
                        + description[i] + " " + amount[i]);
                subtotal += amount[i];

            }
        }

      total += subtotal;

    System.out.println("Total: " + String.format("%.2f", total)); 
    System.out.println("totalcredit" + String.format("%.2f", totalcredit)); 
    System.out.println("totaldebut" + String.format("%.2f", totaldebit)); 
    System.out.println("Beginning Balance: " + String.format("%.2f", beginningBalance)); 
    System.out.println("Ending Balance:  " + String.format("%.2f", beginningBalance + total)); 
}

    /**
     * 7
     * The method first initializes a variable minIndex to i and then iterates
     * through the rest of the elements (from i + 1 to numberOfRecords) to find
     * the smallest check number. If it finds a smaller check number, it updates
     * the minIndex to the index of the smaller number. After finding the
     * smallest check number, the method calls the swap method to swap the
     * element at minIndex with the element at i. This places the smallest check
     * number in its correct position in the sorted array.
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

    /**
     * 8
     * the printByCheckNum method will sort the records by check number and then
     * print each record in the sorted order. The print method is used to format
     * the output, where the format specifiers such as %d and %.2f are used to
     * specify the type and format of the output.
     */
    public void printByCheckNum(double balance) {
        sortByCheckNum();

        //print column headings
        //print beginning balance
        for (int i = 0; i < numberOfRecords; i++) {
            //calculate new balance
            //adjust column positions
            System.out.printf("%d %10s %20s %10s %10s %18s\n", checkNum[i], date[i],
                    description[i], amount[i],
                    type[i], category[i]);

        }

    }

    /**
     * This run method reads a file specified in the first command line argument
     * and performs the following operations: Reads the input file specified in
     * the first command line argument using the readFile method. Sorts the
     * records based on the category by calling the sortByCategory method.
     * Prints the sorted records by category by calling the printByCategory
     * method and passing a beginning balance of 1000. Sorts the records based
     * on the check number by calling the sortByCheckNum method. Prints the
     * sorted records by check number by calling the printByCheckNum method.
     *
     */
    public void run(String[] args) {
        readFile(args[0]);

        //sortByCategory();
        printByCategory(0);
        //sortByCheckNum();
        printByCheckNum(Double.parseDouble(args[1]));

    }

    /**
     * 10 This is the complete PersonalFinances class in Java, which can be used
     * to process personal financial records. The class has several methods for
     * reading data from a file, sorting and printing the records based on check
     * number and category. The run method reads the input file and sorts the
     * data in two ways, first by category, and then by check number, and prints
     * the records in both sorted formats. The main method starts the program by
     * checking that the input file name has been passed as a command line
     * argument. If the input file name is not provided, it displays an error
     * message and terminates the program.
     *
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage java PersonalFinances input");
            System.exit(1);
        }
        //System.out.println("start program");

        PersonalFinances pf = new PersonalFinances();
        pf.run(args);

    }

}
