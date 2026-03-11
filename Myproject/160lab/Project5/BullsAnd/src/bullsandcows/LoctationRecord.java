
package bullsandcows;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author kalalajoel
 */
public class LoctationRecord {
    
    private int year;
    private int month;
    private int daysOfMilk;
    private int IbsOfMilk;
    private double percentButterfat;
    private int totalButterfat;

    public LoctationRecord(int year, int month, int daysOfMilk, int IbsOfMilk, double percentButterfat) {
        this.year = year;
        this.month = month;
        this.daysOfMilk = daysOfMilk;
        this.IbsOfMilk = IbsOfMilk;
        this.percentButterfat = percentButterfat;
        this.totalButterfat = (int) (IbsOfMilk * percentButterfat/100);
         //this.totalButterfat = (milkProduced * butterfatPercentage) / 100.0;
    }
    

   @Override
    public String toString() {
        return "LoctationRecord #year: " + year + " #month: " + month + " #daysOfMilk: " + daysOfMilk + " #IbsOfMilk: " 
       + IbsOfMilk + " #percentButterfat: " + percentButterfat + " #totalButterfat: " + totalButterfat;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDaysOfMilk() {
        return daysOfMilk;
    }

    public int getIbsOfMilk() {
        return IbsOfMilk;
    }

    public double getPercentButterfat() {
        return percentButterfat;
    }

    public int getTotalButterfat() {
        return totalButterfat;
    }

/*
     * Unit Test for LactationRecord Copy this into LactationRecord and use 
     *    Run File to test it.
     */
     public static void main(String[] args){
        ArrayList<LoctationRecord> lacRecs = new ArrayList<>();
        lacRecs.add(new LoctationRecord(2, 10, 228, 17232, 5.3));
        lacRecs.add(new LoctationRecord(3, 2, 178, 3290, 4.0));
        lacRecs.add(new LoctationRecord(4, 5, 260, 266, 3.2));
        System.out.println("Milk Records");
        for(LoctationRecord record: lacRecs){
            System.out.println(record);
        }
        System.out.println("Expected Output:\nMilk Records\n"
                   + "02-10  228  17232  5.3   913\n"
                   + "02-10  228  17232  5.3   913\n"
                   + "03-02  178   3290  4.0   131\n"
                   + "04-05  260    266  3.2     8 \n");

    }
}// end Unit Test