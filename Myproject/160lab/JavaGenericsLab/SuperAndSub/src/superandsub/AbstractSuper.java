/*
 *                        Revision History
 *  ==========================================================
 * created class for demonstration.  I will not complete the 
 * documentation, because everything is just a dummy thing
 */
package superandsub;

/**
 *
 * @author Anne
 */
public abstract class AbstractSuper {
    // not accessible since private and no accessor 
    private int property1;
    // directly accessible by subclass
    protected int property2;
    
    /**
     *  a default constructor
     */
    protected AbstractSuper(){
        this(0,0);
    }

    /**
     * parameterized constructor.
     * @param property1 
     * @param property2
     */
    protected AbstractSuper(int property1, int property2){
        this.property1 = property1;
        this.property2 = property2;
        System.out.println("Finished Executing: constructor in AbstractSuper");
    }
    
    /**
     *
     */
    public void m(){
        System.out.println("Executing AbstractSuper's m()");
        property1 = property1 * property2;
    }
    /**
     * forces implementation in any class extending this one
     * @return something from the class extending this one
     */
    public abstract int n();
    /**
     *
     */
    protected void p(){
        System.out.println("Executing AbstractSuper's p()");
        property2 = property1 + property2;
    }
    public String toString(){
        return String.format("In the AbsractSuper class "
                + "property1 is %d and property2 is %d", 
                   property1, property2);
    }
}
