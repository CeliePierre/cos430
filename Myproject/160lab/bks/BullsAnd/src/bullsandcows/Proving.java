


package bullsandcows;

import java.util.Formatter;

/**
 *
 * @author kalalajoel
 */
public class Proving {

    private int month;
    private int day;
    private int year;
    private int daughters;
    private int records;
    private int milk;
    private double percentButterfat;
    private double genetics;
    private int totalButterfat;

    public Proving(int month, int day, int year, int daughters, int records, int milk, double percentButterfat, double genetics) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.daughters = daughters;
        this.records = records;
        this.milk = milk;
        this.percentButterfat = percentButterfat;
        this.genetics = genetics;
        this.totalButterfat = (int) (milk * percentButterfat / 100);
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public int getDaughters() {
        return daughters;
    }

    public int getRecords() {
        return records;
    }

    public int getMilk() {
        return milk;
    }

    public double getPercentButterfat() {
        return percentButterfat;
    }

    public double getGenetics() {
        return genetics;
    }

    public int getTotalButterfat() {
        return totalButterfat;
    }
     /*
     * Unit Test for Proving  Copy this into Proving and use Run File to 
     *   to test it.
     */

    public static void main (String[] args){
        Proving proof1 = new Proving(6, 6, 1920, 93, 180, 15894, 3.4, -476.8);
        Proving proof2 = new Proving(6, 6, 1920, 93, 180, 15894, 3.4, 476.8);
        System.out.println(proof1);
        System.out.println("\nExpected Output:\nDate = 06-06-1920\n"
                   + "Dtrs = 93 Records = 180\n"
                   + "AveMilk = 15894 AveBf% = 3.40 AveBf = 540\n"
                   + "ExImp = -476.80");
        System.out.println();
        System.out.println(proof2);
        System.out.println("\nExpected Output:\nDate = 06-06-1920\n"
                   + "Dtrs = 93 Records = 180\n"
                   + "AveMilk = 15894 AveBf% = 3.40 AveBf = 540\n"
                   + "ExImp = +476.80");
    }
}