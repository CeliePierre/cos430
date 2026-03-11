/** **********************************************************************<br>
 * Revision History (newest first)<br>
 ***********************************************************************<br>
 * 2/4/2022 - AA added try..catch to getValidInput()<br>
 * 12/2020 - AA - house cleaning <br>
 * 1/2018 - AA - rewrote without static methods<br>
 * 2014 - AA - Wrote the original driver<br>
 * ********************************************************************
 */
package doghotelproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The driver for the Dog Hotel problem.  <br>
 * To model several good strategies for students.<br>
 * <ul>
 * <li>Creates a Hotel object,</li>
 * <li>Reads the input file,</li>
 * <li>Displays a menu,</li>
 * <li>Validates input,</li>
 * <li>Executes based on user input using a switch statement.</li>
 * </ul>
 *
 * @author aapplin
 */
public class DogHotelProject {

    private Hotel hotel;
    private Date today;

    /**
     * A method that reads the file and loads the guests<br>
     * You need to write this one! Input Data Format:<br>
     * birth_date breed<br>
     * weight name owner room_number check_in_date
     *
     * @param fileName an input file name
     */
    public void readGuestFile(String fileName) {
        try {
            Scanner inFile = new Scanner(new FileReader(fileName));
            // check for an empty file. code 2
            while (inFile.hasNext()) {
                //int birthmonth= inFile.nextInt();

                // Read the first line
                int birthMonth = inFile.nextInt();
                int birthDay = inFile.nextInt();
                int birthYear = inFile.nextInt();
                String breed = inFile.nextLine();

                // Read the second line
                double weight = inFile.nextDouble();
                String name = inFile.next();
                String owner = inFile.next();
                int roomNumber = inFile.nextInt();
                int checkInMonth = inFile.nextInt();
                int checkInDay = inFile.nextInt();
                int checkInYear = inFile.nextInt();

                // Create and add the guest to the list
                Guest guest = new Guest(new Date(birthMonth, birthDay, birthYear), breed, weight,
                        name, owner, roomNumber, new Date(checkInMonth, checkInDay, checkInYear));


               boolean added = hotel.addGuest(guest);
        if (added) {

        }
            }

            
            inFile.close();
        } catch (FileNotFoundException ex) {
            // we catch it and print an error message
            System.err.println("File " + fileName + " not found");
            ex.printStackTrace();
            System.exit(3);
        } catch (InputMismatchException ex) {
            System.err.println("Next data type doesn't match.");
            ex.printStackTrace();
            System.exit(4);
        } catch (NoSuchElementException ex) {
            System.err.println("No tokens left in file.");
            ex.printStackTrace();
            System.exit(5);
        } catch (IllegalStateException ex) {
            System.err.println("The file seems to be closed.");
            ex.printStackTrace();
            System.exit(6);
        }
    }

    /**
     * This method is complete, DO NOT CHANGE <br>
     * Displays a menu for the user to select from.
     */
    public void displayMenu() {
        System.out.println("=================================");
        System.out.println("|  1.  Checkin a guest.         |");
        System.out.println("|  2.  Checkout a guest.        |");
        System.out.println("|  3.  Print the feeding order. |");
        System.out.println("|  4.  Backup.                  |");
        System.out.println("|  5.  Perform end of day.      |");
        System.out.println("=================================");
        System.out.println();
    }
    

    /**
     * This method is complete, DO NOT CHANGE <br>
     * Gets an integer from the user in the range of min to max
     *
     * @param in a Scanner object to read from the keyboard
     * @param low the lowest acceptable number for input
     * @param high the highest acceptable number for input
     * @param message the kind of input we are looking for
     * @return a valid integer input
     */
    //
    public int getValidIntInput(Scanner in, int low, int high,
            String message) {
        int choice;

        do {
            System.out.print("Enter a " + message + " between " + low
                    + " and " + high + ":  ");
            try {
                choice = in.nextInt();
            } catch (InputMismatchException ex) {
                choice = low - 1;
            }
        } while (choice < low || choice > high);
        return choice;
    }

   public void printGuestsInfo() {
        //System.out.printf("%7s  %-12s %-10s %10s %10s\n","Room:","Name","Amount","Type");
        System.out.printf("%7s  %-12s %-10s %10s %10s\n","Room:", "Name", "Amount", "Type", "");
        int numberOfGuests = hotel.getSize();
        //for (Guest guest : hotel.getGuests()) {
         for (int i = 0; i < numberOfGuests; i++) {
            Guest guest = hotel.getGuestAt(i);
            int roomNumber = guest.getRoomNumber();
            String name = guest.getName();
            double amount = guest.getAmountOfFood();
            String type = guest.getTypeOfFood();
        System.out.printf("%6d  %-12s %-10s %10.2f\n", roomNumber, name, type, amount);
            //System.out.printf("%6d   %-12s %10s %-10.2f\n", roomNumber,name,amount,type);
        }
    }
    
    
    /**
     * This method is complete, DO NOT CHANGE <br>
     * Acquires all of the guest information and creates a guest.
     *
     * @param in A Scanner Object
     * @return a Guest object with everything but a room number and <br>
     * check in date.
     */
    public Object getGuestInfo(Scanner in) {
        System.out.print("Enter the owner's name: ");
        String owner = in.next();
        System.out.print("Enter the dog's name: ");
        String name = in.next();

        System.out.println("Please enter the dog's breed:  ");
        in.nextLine(); // throw away the end of line character
        // before using nextLine() to get a string with embedded spaces
        String breed = in.nextLine();
        System.out.println("Enter the dog's date of birth ");
        int month = getValidIntInput(in, 1, 12, "a month");
        int day = getValidIntInput(in, 1, 31, "a day");
        int year = getValidIntInput(in, 1970, 2023, "a year");
        System.out.print("Enter the dog's weight: ");
        double weight = in.nextDouble();
        // Guest guest = new Guest(new Date(month, day, year), breed, weight,
        //        name, owner);
        //  return guest;
        return null;
    }

    /**
     * This method is complete, DO NOT CHANGE <br>
     * We need this information in two instances: checking a guest in and <br>
     * checking a guest out, so making it a method keeps from repeating <br>
     * the same code.
     *
     * @param in A Scanner Object
     * @return a String
     */
    public String getOwner(Scanner in) {
        System.out.print("Enter the owner's name: ");
        String owner = in.next();
        return owner;
    }

    /**
     * This method is complete DO NOT CHANGE <br>
     * Backs up the current hotel guests into a data file.<br>
     * Since the computer system is powered down at night, we<br>
     * need to write the current guests out to a file that can be <br>
     * read in the next morning.
     *
     * @param fileName the fileName that we are writing to.
     * @param today the system date
     */
    //This method is complete, DO NOT CHANGE    
    public void backup(String fileName, Date today) {
        String date = today.toString();
        date = date.replace('/', '_');
        String front = fileName.substring(0, fileName.indexOf('.'));
        String end = fileName.substring(fileName.indexOf('.'));
        fileName = front + date + end;
        try {
            PrintStream out = new PrintStream(new File(fileName));
            for (int i = 0; i < hotel.getOccupancy(); i++) {
                out.println(hotel.getGuestAt(i));
            }
            out.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Getting an error we shouldn't get."
                    + " Is the folder write protected?");
            System.err.println("Check the file and try again.");
        }
    }

    /**
     * This method is complete, DO NOT CHANGE <br>
     * A method that retrieves the system date and time Initializes the property
     * today and greets the user.
     */
    public void displayGreeting() {
        // get the current date from the computer.
        LocalDateTime ldt = LocalDateTime.now();
        int month = ldt.getMonthValue();
        int day = ldt.getDayOfMonth();
        int year = ldt.getYear();
        int hour = ldt.getHour();
        today = new Date(month, day, year);
        if (hour < 12) {
            System.out.print("Good Morning! ");
        } else {
            System.out.print("Good Afternoon! ");
        }
        System.out.print("Today is " + ldt.getDayOfWeek() + ", "
                + month + " " + day);
        String post;
        switch (day) {
            case 1:
            case 21:
            case 31:
                post = "st";
                break;
            case 2:
            case 22:
                post = "nd";
                break;
            case 3:
            case 23:
                post = "rd";
                break;
            default:
                post = "th";
        }
        System.out.println(post);
    }

    /**
     * Driver for the DogHotel project.<br>
     * This method is complete, DO NOT CHANGE<br>
     *
     * @param args the command line arguments
     */
    //This method is complete, DO NOT CHANGE
    public void run(String[] args) {
        hotel = new Hotel("Pampered Pooch Inn", 15.00, 25); 
        //System.out.println("start run");

        displayGreeting();

        readGuestFile(args[0]);
        //System.out.println("file done");
        boolean done = false; // we just started!
        Scanner in = new Scanner(System.in); // for user input
        while (!done) {
            displayMenu();
            int choice = getValidIntInput(in, 1, 5, "menu choice");
            switch (choice) {
                case 1:
                    if (hotel.getSize() == hotel.getOccupancy()) {
                        System.out.println("No room available.");
                    } else {
                        Guest guest = (Guest) getGuestInfo(in);
                        int room = hotel.checkInGuest(guest, today);
                        System.out.println("Checked into room " + room);
                    }
                    break;
                case 2:
                    String owner = getOwner(in);
                    System.out.println(hotel.checkOutGuest(owner, today));
                    break;
                case 3:
                    String[] list = hotel.createFeedingOrder();
                    printGuestsInfo();
                    //for (int i = 0; i < list.length; i++) {
                    //    System.out.println(list[i]);
                    //}
                    break;
                case 4:
                    backup(args[1], today);
                    break;
                case 5:
                    backup(args[1], today);
                    done = true;
            } // end switch
        } // end while ! done
    } // end run() 

    /**
     * Main method. You Write this according to the specifications detailed in
     * Lab 2.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("main");
        if (args.length < 1) {
            System.err.println("Usage java DogHotel input");
            System.exit(1);
        }
        //System.out.println("start program");

        DogHotelProject cd = new DogHotelProject();
        //System.out.println("call run");
        cd.run(args);

    }

}

