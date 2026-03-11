/*
 *       Revision History newest first 
 * date      what you did                                         who you are
 ****************************************************************************
 * 7/31/2022 Designed and wrote this for a lab on file input      aapplin
 */

package musicboxshop;

/**
 * Description: a class to model the actual wind up mechanism that plays the 
 *              music in the music box.
 * Depends on: nothing
 * Author: Anne Applin
 * @author aapplin
 */
public class Mechanism {
    private String composition; // title with embedded spaces 7
    private int movement;  // 18, 30, 50 notes
    private double cost;   //  28.99, 469.97, 835.93
    // static members are shared by all objects.  This makes it easier
    // to change these constants in one place. 
    private static double[] prices = {28.99, 469.97, 835.93};
    /**
     * Parameterized constructor for the class No mutators are provided.
     * Objects of this class are immutable.
     * @param composition the title of the music
     * @param movement the number of notes in the mechanism
     * @param cost the cost for the mechanism
     */
    public Mechanism(String composition, int movement) {
        this.composition = composition;
        this.movement = movement;
        assignCost();
    }
    /**
     * Processing method 
     * The prices of the different sizes of mechanisms are in a static
     * array shared by all objects of the class.
     */
    private void assignCost(){
        if(movement == 18)
            cost = prices[0];
        else if (movement == 30)
            cost = prices[1]; 
        else
            cost = prices[2];
    }
    /**
     * Accessor for the composition property
     * @return the title of this composition
     */
    public String getComposition() {
        return composition;
    }
    /**
     * Accessor for the movement property
     * @return the number of notes in this mechanism
     */
    public int getMovement() {
        return movement;
    }
    /**
     * Accessor for the cost property 
     * @return the cost for this mechanism
     */
    public double getCost() {
        return cost;
    }    
    /**
     * The foramt() method of the string class has the same syntax as a 
     * printf() method.  
     * Notice: 1)  The escape characters so that the quotes print
     *         2)  There is no line feed at the end of the formatted string.
     *             Always let the client add the linefeeds.
     * @return a formatted string for an invoice
     */
    @Override
    public String toString() {
        return String.format("%d note movement playing \"%s\" ", 
                movement, composition);
    } 
    /**
     * Unit test for Mechanism
     * @param args 
     */
    public static void main(String [] args){
        Mechanism m = new Mechanism("Happy Birthday to You", 18);        
        System.out.printf("%s at a cost of $%.2f%n", m, m.getCost() );
        m = new Mechanism("Happy Birthday to You", 30);        
        System.out.printf("%s at a cost of $%.2f%n", m, m.getCost() );
        m = new Mechanism("Happy Birthday to You", 50);        
        System.out.printf("%s at a cost of $%.2f%n", m, m.getCost() );
    }
}
