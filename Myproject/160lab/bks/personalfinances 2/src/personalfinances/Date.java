
package personalfinances;

/**
 *
 * @author kalalajoel
 */
public class Date  implements Comparable{

    private int month;
    private int day;
    private int year;
    private double dayNumber; // the number of days since 1/12/4712 BCE
    /**
     * Default Constructor
     * Sets the date to January 1, 1970 
     */
    public Date() {
        
        
        this(1, 1, 1970); // a default date - the start date for Unix systems
    }
    /**
     * Parameterized Constructor
     * Validates Year, Month, and Day.  Sets invalid values to 1/1/1970
     * Calls: calcDayNumber() to set that property.
     * @param month input integer for month.
     * @param day   input integer for day.
     * @param year  input integer for year
     */
    public Date(int month, int day, int year) {
        if (year >= -4712)
           this.year = year;
        else 
            this.year = 1970;
        if (isValidMonth(month)) // have to validate month before day
           this.month = month;
        else 
            this.month = 1;
        if(isValidDay(day))
            this.day = day;
        else
            this.day = 1;  
        calcDayNumber();
    }
    /**
     * Validates the month passed in.  If the month is not valid it is set 
     * to 1.
     * @param month the value sent in by the calling module
     */
    public void setMonth(int month) {
        if (isValidMonth(month)) {
            this.month = month;
        } else {
            this.month = 1;
        }
    }
    /**
     * validates the day passed in from the constructor.  
     * Preconditions: The month must already be validated for this to work.
     * @param day the value sent in by the calling module
     */
    public void setDay(int day,int month,int year) {
        // thirty days hath September, April, June, and November
        if (isValidDay(day) ) {
            this.day = day;
        }else{
            this.day = 1;
    }
    if (isValidMonth(month) ) {
            this.month = day;
    }else{
            this.month = 1;
    }
    if (isValidDay(year) ) {
            this.day = year;
    }else{
            this.year = 1970;
    }
    }

    /**
     * Validates and then sets the property year.
     * @param year the integer passed in from the constructor.
     */
    private void setYear(int year) {
        if (year > -4712)
           this.year = year;
        else 
            this.year = 1970;
       
    }
    
    /**
     * Validates that the value for month is between 1 and 12 inclusive.<br>
     * Only called by members of the class.
     * @param month the integer passed in from the constructor.
     * @return true if the value is between 1 and 12 inclusive and false
     * otherwise.
     */
    private boolean isValidMonth(int month){
        return (month > 0 && month <= 12);

        
    }
    /**
     * Validates the day based on the month <br>
     * Only called by members of the class.
     * Preconditions: month must be validated and set before this is called.
     * Thirty days hath September, April, June, and November.  All the rest
     * have 31 excepting February alone and it has 28 days clear and 29
     * on each leap year.
     * @param day input integer for day
     * @return true if the day is valid based on the month.
     */
    private boolean isValidDay(int day){
        boolean valid = false;
        if (this.month == 9 || this.month == 4 || this.month == 6
                    || this.month == 11) {
                if (day > 0 && day <= 30) { // 1..30
                    valid = true;
                }
        } else if (this.month == 1 || this.month == 3|| this.month == 5 ||
                   this.month == 7 || this.month == 10 || this.month == 12) {
                if (day > 0 && day <= 31) { // 1 .. 31 are valid
                    valid = true;
                }
        } else if (isLeapYear()){ // month == 2 && leap year
            if (day > 0 && day <= 29) // 1 .. 29 are valid
                valid = true;
        } else {
            if (day > 0 && day <= 28) //  1 .. 28 are valid
                valid = true;
        }
        
        
        
        return valid;
    }
    /**
     * given that year is valid, this method check to see if it is a<br>
     * leap year.  used to validate a day in February. <br>
     * @return true if the year is a leap year using standard algorithm.
     */
    public boolean isLeapYear() {
 	return ((year % 400 == 0) || 
                ((year % 4 == 0) && (year % 100 != 0)));	
    }
    
    /**
     * The day number is the number of days that have elapsed since noon on<br> 
     * January 1, 4712 BCE - the beginning of the calendar developed during<br> 
     * the reign of Julius Ceasar. For more information .. try Google. <br> 
     * This makes it easier to compute differences between two dates or <br>
     * to calculate an age. Only called by members of the class<br>
     * A = Y / 100 <br>
     * B = A / 4<br>
     * C = 2 - A + B<br>
     * E = 365.25 * (Y + 4716)<br>
     * F = 30.6001 * (M + 1)<br>
     * JD= C + D + E + F - 1524.5 <br>
     */
    
    private void calcDayNumber(){
        double a, b, c, e, f;
        a = year / 100;
        b = a / 4;
        c = 2 - a + b;
        e = 365.25 * (year + 4716);
        f = 30.6001 * (month + 1);
        dayNumber = c + day + e + f - 1524.5;
    }
    /**
     * calculates the difference between two dates as an absolute value <br>
     * of the two day numbers.<br>
     * @param that  a second date.
     * @return the number of elapsed days.
     */
    public double difference(Date that){
        return Math.abs(that.dayNumber - this.dayNumber);
    }
    
    

    /**
     * Accessor for the value of the property day.
     * @return current value of the property day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Accessor for the value of the property month.
     * @return the current value of the property month.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Accessor for the value of the property year
     * @return the current value of the property year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Accessor for dayNumber
     * @return the current value of dayNumber
     */
    public double getDayNumber() {
        return dayNumber;
    }
    
    /**
     * creates a string for date in the form mm/dd/yyyy
     * @return a string in the form mm/dd/yyyy
     */
    public String toString(){
        StringBuilder str = new StringBuilder();        
        str.append(String.format("%d/%d/%d", month, day, year));
        return str.toString();
    }
    /**
     * compareTo (abstract method of the Comparable Interface) is <br> 
     * implemented to impose a natural ordering on a group of objects.<br>
     * compareTo is used by the Collections.sort routine to allow us to <br>
     * sort the Date objects belonging to some Java collection.<br>
     * @param that is the Date object we are comparing this one to<br>
     * @return a negative integer, zero, or a positive integer if this object <br>
     * comes before, is equal to, or comes after the specified object.<br>
     */
    @Override
    public int compareTo(Object that){
        int comparison  = 0;        
        // shouldn't be any null objects, but if there are
        // put them after this one
        if (that == null)
            comparison = 1; 
            // if the addresses are the same... they are equal
        else if (this == that) 
            comparison = 0;
            // now we will compare dates using the daynumber 
            // since these are primitive values a simple difference 
            // is all we need.
        else
            comparison = 
                    (int)(this.dayNumber - ((Date)that).getDayNumber());
        return comparison; 
    }
    
    
    

    /**
     * THe main method
     * @param args command line arguments
     */
    
    public static void main(String[] args) { 
	Date obj1 = new Date(54,14,-4800);
        System.out.println(obj1);
	System.out.println(obj1.getYear() + " is a leap year : " + 
                obj1.isLeapYear());
        obj1.setYear(1996);
	System.out.println("1996 is a leap year : " + obj1.isLeapYear());
        obj1.setYear(2012);
	System.out.println("2012 is a leap year : " + obj1.isLeapYear());
      
        
        
        
        
        // want to know how many days you have been alive?
        // uncomment the lines below and put in the correct dates.
        //Date today = new Date(9,20,2021);
        //Date birthday = new Date(5,5,2021);
        //int daysAlive = (int)today.difference(birthday);
        //System.out.println("You have been alive " +  daysAlive);
        
        
    }
}


    
    
    
    
    
    

