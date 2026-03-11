
package doghotelproject;
import java.util.Collections;
import java.util.ArrayList;


/**
 *
 * @author kalalajoel
 */
public class Hotel { 
    private String hotelName;
    private double dailyRate;
    private int capacity;
    private int occupancy;
    private ArrayList<Guest> guests;
    private Hotel hotel;
  
    
    
    
    
    public Hotel(String hotelName, double dailyRate, int capacity) {
    this.hotelName = hotelName;
    this.dailyRate = dailyRate;
    this.capacity = capacity;
    this.guests = new ArrayList<>();
}
  
    public String getHotelName() {
        return hotelName;
    }
      public double getDailyRate() {
        return dailyRate;
    }

    public int getCapacity() {
        return capacity;
    }

  
    public int getOccupancy() {
        return guests.size();
    
    }
    
    
     public int getSize() {
        return guests.size();
    }

    //public Guest getGuestAt(int index) {
       // return guests.get(index);
    //}
     public Guest getGuestAt(int index) {
    if (index >= 0 && index < guests.size()) {
        return guests.get(index);
    } else {
        return null;
    }
}



//public boolean addGuest(Guest g) {
  //  boolean added = false;
   // if (guests.size() < capacity) {
      //  guests.add(g);
       // Collections.sort(guests);
       // added = true;
   // }
   // return added;
//}
     
     
     public boolean addGuest(Guest g) {
    boolean added = false;
    if (hasVacancy()) {
        guests.add(g);
        Collections.sort(guests);
        added = true;
    }
    return added;
}

public boolean checkInGuest(Guest guest) {
    boolean added = false;
    if (guests.size() < capacity) {
        guests.add(guest);
        Collections.sort(guests);
        added = true;
    }
    return added;
}

public int checkInGuest(Guest g, Date today) {
    int roomNumber = findEmptyRoom();
    if (roomNumber != -1) {
        g.setRoomNumber(roomNumber);
        g.setCheckInDate(today);
        if (addGuest(g)) {
            occupancy++;
            return roomNumber;
        }
    }
    return -1;
}

public boolean hasVacancy() {
    return guests.size() < capacity;
}

private int findEmptyRoom() {
    for (int i = 0; i < guests.size(); i++) {
        if (guests.get(i).getRoomNumber() != i) {
            return i;
        }
    }
    return guests.size();
}

    // public String createFeedingOrder() {
    //String result = "";
    //for (int i = 0; i < hotel.getOccupancy(); i++) {
      //  Guest guest = hotel.getGuestAt(i);
        //result += "Feeding " + guest.getName() + "\n";
    //}
   // return result;
//}
public String[] createFeedingOrder() {
    //ArrayList<Guest> guests = getGuests();
    String[] orders = new String[guests.size()];
    for (int i = 0; i < guests.size(); i++) {
        Guest guest = guests.get(i);
        String order = String.format("%s %s", guest.name, guest.getBreed());
        orders[i] = order;
    }
    return orders;
    
}


public String checkOutGuest(String ownerLast, Date today) {
    int roomNumber = findGuest(ownerLast);
    if (roomNumber != -1) {
        Guest guest = guests.get(roomNumber);
        double charges = guest.checkOut(today);
        guests.remove(roomNumber);
        return guest.getName() + " in room " + guest.getRoomNumber() + " checked out with charges of $" + charges;
    } else {
        return ownerLast + " not found.";
    }
}

public int findGuest(String ownerLast){
    int room= 0;
    for(int i = 0; i < guests.size(); i++) {
         Guest guest = guests.get(i);
         if(guest.getOwner().equals (ownerLast)){
             room= guest.getRoomNumber();
             
         }
             
    }
    return room;
}

}