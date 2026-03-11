/*
 *       Revision History newest first 
 * date      what you did                                         who you are
 ****************************************************************************
 * 8/1/2022 Designed and wrote this for a lab on file input       aapplin
 */

package musicboxshop;

/**
 * Description: A container class to model the active orders for the shop
 * Depends on: MusicBox
 * Author: Anne Applin
 * @author aapplin
 */
public class Orders {
    private MusicBox[] orderList;
    private int numberOfOrders;
    
    /**
     * Default constructor for the class.  Creates the array and initializes
     * the number of orders in the list.
     */
    public Orders() {
        orderList = new MusicBox[200];
        numberOfOrders = 0;
    }
    /**
     * Mutator for the property orderList.
     * Because nothing outside of this class can directly access the array, we
     * need a public way to add new orders to it.
     * @param m a MusicBox object
     */
    public void addOrder(MusicBox m){
        orderList[numberOfOrders] = m;
        numberOfOrders++;
    }
    
    /**
     * Accessor for the Property orderList.
     * Because nothing outside of this class can directly access the array, we
     * need a public way to access the orders in it 
     * @param n an index into the array
     * @return a MusicBox object which may be null if the index is invalid
     */
    public MusicBox getOrder(int n){
        MusicBox order = null;
        if (n < numberOfOrders){
            order = orderList[n];
        }
        return order;
    }
    /**
     * Accessor for the property numberOfOrders
     * @return the number of data elements in the array.
     */
    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    /**
     * the toString for this class.  The StringBuilder allows us to keep 
     * appending strings to it until we have all of the orders in a single 
     * string.
     * This is the client (user) for MusicBox this string has line feeds
     * @return All of the orders from the shop as a formatted string
     */
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < numberOfOrders; i++){
            str.append(orderList[i].toString()).append(System.lineSeparator());
            str.append(System.lineSeparator());
        }
        return str.toString();
    }
        /**
     * Unit text for Orders. Uses the same 9 Music Boxes from that unit test.
     * @param args 
     */
    public static void main(String[] args){
        Orders orders = new Orders();
        Mechanism m = new Mechanism("Happy Birthday to You", 18);
        orders.addOrder(new MusicBox(1, m, 0, 0));        
        orders.addOrder(new MusicBox(2, m, 0, 1));
        orders.addOrder(new MusicBox(3, m, 0, 2));
        m = new Mechanism("Pop Goes the Weasel", 30); 
        orders.addOrder(new MusicBox(4, m, 1, 0));       
        orders.addOrder(new MusicBox(5, m, 1, 1));        
        orders.addOrder(new MusicBox(6, m, 1, 2));             
        m = new Mechanism("Ode To Joy", 50);        
        orders.addOrder(new MusicBox(7, m, 2, 0));
        orders.addOrder(new MusicBox(8, m, 2, 1));    
        orders.addOrder(new MusicBox(9, m, 2, 2));
        System.out.println(orders);   
    }
}
