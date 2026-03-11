/**
 *                 Revision History
 * ***********************************************************
 *  2019 - rewrote the compareTo so that it only has one return <br>
 *         statement. Corrected the millisecond calculation in the <br> 
 *         constructor and refined the toString() to handle leading <br>
 *         zeros using String.format() switches. <br>
  * 2015 Written for the agility competition project to show how a class<br>
 *      can be written for a special purpose.
 */
package agilitycompetition;

/**
 * Course:  CSCI 160
 * Class:   ElapsedTime
 * Uses:    nothing
 * Extends: nothing
 * Implements: Comparable
 * @author aapplin
 */
public class ElapsedTime implements Comparable {

    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;
    private boolean debug = false; // easier than deleting statements

    /**
     * Default Constructor - Calls the parameterized constructor.
     */
    public ElapsedTime() {
        this(0, 0, 0, 0);
    }

    /**
     * Parameterized Constructor - each input value is validated in that an 
     * input over the maximum for any single item will increment the measure 
     * above it. So 1000 milliseconds would increment seconds and store 0 for
     * milliseconds.
     *
     * @param hours integer input for number of hours
     * @param minutes integer input for number of minutes
     * @param seconds integer input for number of seconds
     * @param milliseconds integer input for number of milliseconds
     */
    public ElapsedTime(int hours, int minutes, int seconds, int milliseconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        if (debug) {
            System.out.println("as entered " + this.toString());
        }
        // now, just in case the numbers are too big... 
        // we start at the bottom and work our way up
        if (this.milliseconds > 999) {
            if (debug) {
                System.out.println("miliseconds are greater than 999");
            }
            this.seconds += (this.milliseconds / 1000);
            this.milliseconds = this.milliseconds % 1000;
            if (debug) {
                System.out.println("miliseconds and seconds adjusted "
                        + this.toString());
            }
        }

        if (this.seconds > 59) {
            if (debug) {
                System.out.println("seconds are greater than 59");
            }
            this.minutes += (this.seconds / 60);
            this.seconds = this.seconds % 60;
            if (debug) {
                System.out.println("seconds and minutes adjusted "
                        + this.toString());
            }
        }
        if (this.minutes > 59) {
            if (debug) {
                System.out.println("minutes are greater than 59");
            }
            this.hours += (this.minutes / 60);
            this.minutes = this.minutes % 60;
            if (debug) {
                System.out.println("minutes and hours adjusted "
                        + this.toString());
            }
        }
    }

    /**
     * Accessor for the property hours
     *
     * @return an integer representing the number of hours in the elapsed time
     */
    public int getHours() {
        return hours;
    }

    /**
     * Accessor for the property minutes
     *
     * @return an integer representing the number of minutes in the elapsed time
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Accessor for the property seconds
     *
     * @return an integer representing the number of minutes in the elapsed time
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Accessor for the property milliseconds
     *
     * @return an integer representing the number of milliseconds in the elapsed
     * time.
     */
    public int getMilliseconds() {
        return milliseconds;
    }

    /**
     * Constructs a string of words representing the elapsed time.
     * Preconditions: all properties have values and it is assumed that
     * milliseconds is the last, but not only non zero property.
     *
     * @return
     */
    public String getTimeInWords() {
        StringBuilder timeStr = new StringBuilder();
        if (hours > 0) {
            timeStr.append(hours);
            timeStr.append(" hours, ");
        }
        if (minutes > 0) {
            timeStr.append(minutes);
            timeStr.append(" minutes, ");
        }
        if (seconds > 0) {
            timeStr.append(seconds);
            timeStr.append(" seconds, ");
        }
        if (milliseconds > 0) {
            timeStr.append("and ");
            timeStr.append(milliseconds);
            timeStr.append(" milliseconds.");
        }
        return timeStr.toString();
    }

    /**
     * adds leading zeros where needed to print the time in the form:
     * nn:nn:nn:nnn including leading zeros
     *
     * @return a string representing elapsed time
     */
    @Override
    public String toString() {
        return (String.format("%02d:%02d:%02d:%03d",
                hours, minutes, seconds, milliseconds));
    }

    /**
     * compareTo (abstract method of the comparable Interface) is implemented to
     * impose a natural ordering on a group of objects. compareTo is used by the
     * Collections.sort routine to allow us to sort the times included in
     * objects belonging to some Java collection.
     *
     * @param that the time we are comparing this time too
     * @return a negative integer, zero, or a positive integer as this object is
     * less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object that) {        
        int comparison = 0;
        if (that == null){// shouldn't be any null objects, but if there are
            comparison = 1; // put them at the end                            
        } // this optimization is usually worthwhile
        else if (this == that){ // if the addresses are the same... 
            comparison = 0; // they are equal
        } else {
            // we will sort in them in order from lowest to highest
            // by the time.  Primitive values follow this form 
            comparison = this.hours - ((ElapsedTime)that).hours;
            if (comparison == 0) { // the hours are equal
                comparison = this.minutes - ((ElapsedTime)that).minutes;
                if (comparison == 0) { //  hours AND minutes are equal
                    comparison = this.seconds - ((ElapsedTime)that).seconds;
                    if (comparison == 0) {// hour, minutes, seconds are equal
                        comparison =                                 
                                   this.milliseconds 
                                - ((ElapsedTime)that).milliseconds;
                    }
                }
            }
        }
        return comparison;
    }
    public static void main(String[]args){
        ElapsedTime time = new ElapsedTime(1,45,3,56);
        System.out.println(time.toString());
    }
}
