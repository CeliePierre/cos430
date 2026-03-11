package doghotelproject;

/**
 *
 * @author kalala
 */
public class Guest extends Pet {

    private int roomNumber;
    private Date checkInDate;
    private String typeOfFood;
    private double amountOfFood;
    private Pet pet;
    
    //private Date dob;
    //private double weightInPounds;
    
    public Guest(Date dob, String breed, double weight,
            String name, String owner,
            int roomNumber, Date checkInDate) {

        super(dob, breed, weight, name, owner);
        this.checkInDate = checkInDate;
        this.roomNumber = roomNumber;
        determineTypeOfFood();
        determineAmountOfFood();
        

    }
     // Constructor for check-in
    public Guest(Date dob, String breed, double weight, String name, String owner) {
        super(dob, breed, weight, name, owner);
    }

  
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
        determineTypeOfFood();
        determineAmountOfFood();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    // public String getTypeOfFood() {
    // return typeOfFood;
    //}
    public String getTypeOfFood() {
        if (typeOfFood == null) {
            determineTypeOfFood();
        }
        return typeOfFood;
    }

    //public double getAmountOfFood() {
    //return amountOfFood;
    //}
    public double getAmountOfFood() {
        if (amountOfFood == 0.0) {
           // double weightInKilos = getWeight() / 0.453592;
             double weightInKilos =  getWeight() / 2.0000;
            amountOfFood = weightInKilos * 0.5;
                     
            
        }
                
        
        return amountOfFood;
    }
    
    public double checkOut(Date today) {
    double charges = 0.0;
    if (checkInDate != null) {
        //int days = Date.daysBetween(checkInDate, today);
        //charges = days * hotel.getDailyRate();
    }
    return charges;
}   
    
 
//public int daysBetween(Date d1, Date d2) {
    //long diff = d2.getTime() - d1.getTime();
    //long msPerDay = 1000 * 60 * 60 * 24;
    //return (int) (diff / msPerDay);
//}  
    
    
    private void determineTypeOfFood() {
        double ageInYears = checkInDate.difference(birthDate) / 365.25;

        if (ageInYears <= 0.12) {
            typeOfFood = "ounce of puppy ";
       
        } else if (ageInYears <= 7) {
            typeOfFood = " ounce of adult";
        } else {
            typeOfFood = "ounce of mature";
        }

    }

    private void determineAmountOfFood() {
        double foodAmount = weight * 0.25;
        System.out.println("The dog needs " + foodAmount + " ounces of food per day.");
    }

    @Override

    public String toString() {
      return String.format("%10d %-10d %-10d %s \n%15.2f %-10s %-10s %-10d %-10d %-10d %10d",
         birthDate.getMonth(), birthDate.getDay(), 
         birthDate.getYear(), breed, weight, name,
         owner, roomNumber, checkInDate.getDay(),
         checkInDate.getMonth(),
         checkInDate.getYear());
}
       
        


    @Override
    public int compareTo(Object that) {
        int comparison;
        if (that == null) { // null object
            comparison = 1;
        } else if (this == that) { // same object
            comparison = 0;
        } else {
            comparison = this.roomNumber - ((Guest) that).getRoomNumber();
        }
        return comparison;

    }

    public static void main(String[] args) {
        //create unite guest

        // Create a Guest object with all parameters
        Guest guest1 = new Guest(new Date(2018, 1, 1), "Labrador Retriever",
                50, "Buddy", "Michael jackson", 101, new Date());

        // Create a Guest object with only parameters for Pet
        Guest guest2 = new Guest(new Date(2019, 1, 1),
                "Labrador Retriever", 50, "Buddy", "Joe kalala", 101, new Date());

        // Set room number and check-in date for guest2
        guest2.setRoomNumber(102);
        guest2.setCheckInDate(new Date());

        // Print both guests
        System.out.println("Guest 1: " + guest1);
        System.out.println("Guest 2: " + guest2);

        // Print type and amount of food for both guests
        System.out.println("Type of food for Guest 1: " + guest1.getTypeOfFood());
        System.out.println("Amount of food for Guest 1: " + guest1.getAmountOfFood());
        System.out.println("Type of food for Guest 2: " + guest2.getTypeOfFood());
        System.out.println("Amount of food for Guest 2: " + guest2.getAmountOfFood());
        
        // Print results of compareTo() for guest1 and guest1, guest2 and guest1, guest1 and guest2
        System.out.println("guest1.compareTo(guest1): " + guest1.compareTo(guest1));
        System.out.println("guest2.compareTo(guest1): " + guest2.compareTo(guest1));
        System.out.println("guest1.compareTo(guest2): " + guest1.compareTo(guest2));
    }
}

