/*
 *       Revision History newest first 
 * date      what you did                                         who you are
 ****************************************************************************
 * 8/1/2022 Designed and wrote this for a lab on file input       aapplin
 */

package musicboxshop;

/**
 * Description: A class to model a music box object.
 * Depends on: Mechanism
 * Author: Anne Applin
 * @author aapplin
 */
public class MusicBox {
    // Instance variables / properties of the class
    private int orderNumber;
    private Mechanism mechanism;
    private int style;     
    private int wood;  
    private double cost;  // mechanism + style + wood
    
    // static properties are shared by all objects
    private static String[] styles = {"plain", "octagonal", "inlaid"};
    private static String[] woods = {"rosewood", "walnut", "cherry"};
    private static double[][] prices = 
             {{250.95, 345.89, 575.99},{55.00, 75.00, 125.00}};
    
    public MusicBox(int orderNumber, Mechanism mechanism, int style, 
            int wood) {
        this.orderNumber = orderNumber;
        this.mechanism = mechanism;
        this.style = style;
        this.wood = wood;
        calcCost();
    }
    /**
     * Private method that calculates the cost of this music box based on the 
     * style and wood and the mechanism cost.
     */
    private void calcCost(){
        cost = mechanism.getCost()
                + prices [0][style] + prices[1][wood] ;            
    }
    public String getStyleWord(){
        return styles[style];
    }
    public String getWoodWord(){
        return woods[wood];
    }
    /**
     * The format() method of the string class has the same syntax as a 
     * printf() method. we call the mechanism's toString method explicitly 
     * NOTICE: 1) The %08d   and pay attention to what it does
     *         2) The %n  line feed after the String from mechanism.toString()
     *            This is the client (user) for Mechanism.
     *         3) There is no linefeed at the end of the formatted string.
     *            Always let the client add the linefeeds.
     *            
     * @return a formatted string
     */
    @Override
    public String toString() {
        return String.format("%08d %s%n%-12s  %-12s%60.2f", 
                orderNumber,  mechanism.toString(), getStyleWord(), 
                getWoodWord(), cost); 
                
    }
    /**
     * Unit text for MusicBox
     * @param args 
     */
    public static void main(String[] args){
        Mechanism m = new Mechanism("Happy Birthday to You", 18);
        MusicBox mb = new MusicBox(1, m, 0, 0);
        System.out.printf("%s %n", mb);
        mb = new MusicBox(2, m, 0, 1);
        System.out.printf("%s %n", mb);
        mb = new MusicBox(3, m, 0, 2);
        System.out.printf("%s %n", mb);
        m = new Mechanism("Pop Goes the Weasel", 30); 
        mb = new MusicBox(4, m, 1, 0);
        System.out.printf("%s %n", mb);
        mb = new MusicBox(5, m, 1, 1);
        System.out.printf("%s %n", mb);
        mb = new MusicBox(6, m, 1, 2);
        System.out.printf("%s %n", mb);        
        m = new Mechanism("Ode to Joy", 50);        
        mb = new MusicBox(7, m, 2, 0);
        System.out.printf("%s %n", mb);
        mb = new MusicBox(8, m, 2, 1);
        System.out.printf("%s %n", mb);
        mb = new MusicBox(9, m, 2, 2);
        System.out.printf("%s %n", mb);       
    }
}
