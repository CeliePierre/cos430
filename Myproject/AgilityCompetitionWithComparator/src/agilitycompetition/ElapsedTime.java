/**
 * Course:  CSCI 160
 * Class:   ElapsedTime 
 * Uses:    nothing
 * Extends: nothing
 * Implements: Comparable
 */
package agilitycompetition;

/**
 *
 * @author aapplin
 */
public class ElapsedTime implements Comparable<ElapsedTime>{
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;
    private boolean debug = false;
    
    /**
     *  Default Constructor
     */
    public ElapsedTime(){
        this(0,0,0,0);
    }

    /**
     * Parameterized Constructor each input value is validated in that 
     * an input over the maximum for any single item will increment the 
     * measure above it.  so 180 milliseconds would increment seconds and 
     * store 80 for milliseconds.
     * @param hours integer input for number of hours
     * @param minutes integer input for number of minutes
     * @param seconds integer input for number of seconds
     * @param milliseconds integer input for number of milliseconds
     */
    public ElapsedTime(int hours, int minutes, int seconds, int milliseconds){
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        if (debug) System.out.println("as entered " + this.toString());
        // now, just in case the numbers are too big... 
        // we start at the bottom and work our way up
        if (this.milliseconds > 999){
            if (debug) 
                System.out.println("miliseconds are greater than 99");
            this.seconds += (this.milliseconds / 100);
            this.milliseconds = this.milliseconds % 1000;
            if (debug) 
                System.out.println("miliseconds and seconds adjusted " + 
                    this.toString());
        }
        
        if (this.seconds > 59){
            if (debug) 
                System.out.println("seconds are greater than 59");
            this.minutes += (this.seconds / 60);
            this.seconds = this.seconds % 60;
            if (debug) 
                System.out.println("seconds and minutes adjusted " + 
                    this.toString());
        }
        if (this.minutes > 59){
            if (debug) 
                System.out.println("minutes are greater than 59");
            this.hours += (this.minutes / 60);
            this.minutes = this.minutes % 60;
            if (debug) 
                System.out.println("minutes and hours adjusted " + 
                    this.toString());
        }        
    }

    /**
     * Accessor for the property hours
     * @return an integer representing the number of hours in the 
     * elapsed time
     */
    public int getHours() {
        return hours;
    }

    /**
     * Accessor for the property minutes
     * @return an integer representing the number of minutes in the 
     * elapsed time
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Accessor for the property seconds
     * @return an integer representing the number of minutes in the 
     * elapsed time
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Accessor for the property milliseconds
     * @return an integer representing the number of milliseconds in the 
     * elapsed time.
     */
    public int getMilliseconds() {
        return milliseconds;
    }
    /**
     * Constructs a string of words representing the elapsed time.
     * Preconditions: all properties have values and it is assumed that 
     * milliseconds is the last, but not only non zero property.
     * @return 
     */
    public String getTimeInWords(){
        StringBuilder timeStr  = new StringBuilder();
        if (hours  > 0){
            timeStr.append(hours);
            timeStr.append(" hours, ");
        }
        if (minutes > 0){
            timeStr.append(minutes);
            timeStr.append(" minutes, ");
        }
        if (seconds > 0){
            timeStr.append(seconds);
            timeStr.append(" seconds, ");
        }
        if (milliseconds > 0){
            timeStr.append("and ");
            timeStr.append(milliseconds);
            timeStr.append(" milliseconds.");
        }
        return timeStr.toString();
    }
    /**
     * adds leading zeros where needed to print the time in the form:
     * nn:nn:nn:nn 
     * @return a string representing elapsed time 
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        if (hours < 10)
            str.append("0");
        str.append(hours + ":");
        
        if (minutes < 10)
            str.append("0");
        str.append(minutes + ":");
        
        if(seconds < 10)
            str.append("0");
        str.append(seconds + ":");
        
        if(milliseconds < 100 && milliseconds >= 10)
            str.append("0");
        else if (milliseconds < 10)
            str.append("00");
        str.append(milliseconds);
        
        return str.toString();
    }

    /**
     * compareTo (abstract method of the comparable Interface) is implemented 
     * to impose a natural ordering on a group of objects.
     * compareTo is used by the Collections.sort routine to allow us to sort
     * the times included in objects belonging to some Java collection.
     * @param that the time we are comparing this time too
     * @return a negative integer, zero, or a positive integer as this object 
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(ElapsedTime that) {
        // named constants are used for clarity in the code
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        int comparison;
        if (that == null){
            return AFTER; // shouldn't be any null objects, but if there are
        }                 // put them at the end
        //this optimization is usually worthwhile, and can
        //always be added = if the addresses are the same... they are equal
        if (this == that){ 
            return EQUAL;
        }
        // we will sort in them in order from lowest to highest
        //primitive numbers follow this form
        comparison = this.hours - that.hours;
        if (comparison != EQUAL){
            return comparison;
        }  
        //if we get here the hours are equal
        comparison = this.minutes - that.minutes;
        if (comparison != EQUAL){
            return comparison;
        }
        // if we get here hours AND minutes are equal
        comparison = this.seconds - that.seconds;
        if (comparison != EQUAL){
            return comparison;
        }
        // if we get here hours, minutes AND Seconds are equal
        comparison = this.milliseconds - that.milliseconds;
        if (comparison != EQUAL){
            return comparison;
        }
        // if it gets all the way down here.. they are equal
        // look at it carefully until you understand how it works!
        return EQUAL;       
    }
}
