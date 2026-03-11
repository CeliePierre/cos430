 package climatdata;

import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author kalalajoel
 */
public class ClimatData {

    private DailyReading[] readings;
    private double averageMaxTemp;
    private double averageMinTemp;
    private int[] yearStartingIndexes;

    public void readFile(String fileName) {
        try {
            Scanner inFile = new Scanner(new FileReader(fileName));
            if (!inFile.hasNext()) {
                System.err.println("File" + fileName + "is empty.");
                System.exit(3);

            }
            int number = inFile.nextInt();
            readings = new DailyReading[number];
            inFile.nextLine();
            inFile.nextLine();
            inFile.nextLine();
            inFile.useDelimiter("[/ \t\n\r]+");
            int i = 0;
            while (inFile.hasNext()) {
                // read the line and split on the comma
                int month = inFile.nextInt();
                int day = inFile.nextInt();
                int years = inFile.nextInt();
                int maxTemp = inFile.nextInt();
                int minTemp = inFile.nextInt();
                Date dat = new Date(month, day, years);
                readings[i] = new DailyReading(dat, maxTemp, minTemp);

                i++;
            }
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
//metheode get maxTemp

    public int findMaxIndex() {
        int maxTemp = 0;
        int index = 0;
        for (int i = 0; i < readings.length; i++) {
            if (readings[i].getmaxTemp() >= maxTemp) {
                maxTemp = readings[i].getmaxTemp();
                index = i;
            }
        }
        return index;
    }
//metheode get minTemp

    public int findMinIndex() {
        int minTemp = 0;
        int index = 0;
        for (int i = 0; i < readings.length; i++) {
            if (readings[i].getminTemp() <= minTemp) {
                minTemp = readings[i].getminTemp();
                index = i;
            }
        }
        return index;
    }
//metheode get averageMaxTemp

    public double getAverageMaxTemp() {
        double averageMaxTemp = 0;
        double sum = 0;
        int count = 0;

        for (int i = 0; i < readings.length; i++) {

            sum += readings[i].getmaxTemp();
            count++;
        }

        averageMaxTemp = sum / count;
        return averageMaxTemp;
    }

    public double getAverageMinTemp() {
        double averageMinTemp = 0;
        double sum = 0;
        int count = 0;
        for (int i = 0; i < readings.length; i++) {

            sum += readings[i].getminTemp();
            count++;
        }
        averageMinTemp = sum / count;
        return averageMinTemp;
    }

//metheode get yearStartingIndexes
    public int[] getYearStartingIndexes(Date startDate, Date endDate) {
        int[] yearStartingIndexes = new int[readings.length];
        int count = 0;
        for (int i = 0; i < readings.length; i++) {
            if (readings[i].getDate().getMonth() == 1 && readings[i].getDate().getDay() == 1
                    && readings[i].getDate().getYear() >= startDate.getYear()
                    && readings[i].getDate().getYear() <= endDate.getYear()) {
                yearStartingIndexes[count] = i;
                count++;
            }
        }
        return yearStartingIndexes;
    }

    public static int firstFallFrost(int yearStartIndex, int yearEndIndex, int[] temperatures) {
        //System.out.println(yearEndIndex + " " + temperatures.length);
        //int midIndex = (yearStartIndex + yearEndIndex) / 2;
        // for (int i = midIndex; i <= yearEndIndex; i++) {
        for (int i = 180; i < temperatures.length; i++) {
            if (temperatures[i] < 32) {
                return i;
            }
        }
        return -1;
    }

    public void run(String[] args) {
        readFile(args[0]);
        int maxIndex = findMaxIndex();
        int minIndex = findMinIndex();
        Date startDate = new Date(1, 1, 1941);
        Date endDate = new Date(12, 31, 2020);
        averageMaxTemp = getAverageMaxTemp();
        averageMinTemp = getAverageMinTemp();
        yearStartingIndexes = getYearStartingIndexes(startDate, endDate);

        System.out.println("Maxtemperatur " + readings[maxIndex].getmaxTemp());
        System.out.println("Mintemperatur" + readings[minIndex].getminTemp());
        System.out.println("The data covers the time period from 01/01/1941 to 12/31/2020.");
        System.out.println("The average high temperature was " + averageMaxTemp + " degrees F.");
        System.out.println("The average low temperature was " + averageMinTemp + " degrees F.");
        System.out.println("Maxtemperature in the data is: " + readings[maxIndex].getmaxTemp()
                + " degrees F which occurred on " + readings[maxIndex].getDate() + ".");
        System.out.println("Mintemperature in the data is: "
                + readings[minIndex].getminTemp() + " degrees F which occurred on "
                + readings[minIndex].getDate() + ".");
        System.out.println("Number of years: 2020 - 1941 + 1 = " + (2020 - 1941 + 1));
        System.out.println("Year\tStarting Index");
       // System.out.println(Arrays.toString(yearStartingIndexes));
        for (int i = 0; i < yearStartingIndexes.length; i++) {
            if (yearStartingIndexes[i] != 0) {
                System.out.println(readings[yearStartingIndexes[i]].getDate().getYear()
                        + "\t" + yearStartingIndexes[i]);

                System.out.println();

                System.out.println("Year\tFirst Fall Frost");
                /*  for (int i = 0; i < yearStartingIndexes.length; i++) {*/
                if (yearStartingIndexes[i] != 0) {
                    int yearStartIndex = yearStartingIndexes[i];
                    int yearEndIndex;
                    if(yearStartingIndexes[i+1]==0)
                        yearEndIndex = readings.length -1;
                    else
                     yearEndIndex = yearStartingIndexes[i + 1] - 1;
                    int[] temperatures = new int[yearEndIndex - yearStartIndex + 1];
                    for (int j = 0; j < temperatures.length; j++) {
                        temperatures[j] = readings[yearStartIndex + j].getmaxTemp();
                    }
                    int firstFallFrostIndex = firstFallFrost(yearStartIndex, yearEndIndex, temperatures);
                    if (firstFallFrostIndex == -1) {
                        System.out.println(readings[yearStartIndex].getDate().getYear() + "\tNo Frost");
                    } else {
                        System.out.println(readings[yearStartIndex].getDate().getYear() + "\t"
                                + readings[firstFallFrostIndex].getDate());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage java ClimatData input");
            System.exit(1);
        }
        //System.out.println("start program");

        ClimatData cd = new ClimatData();
        cd.run(args);

    }

}
