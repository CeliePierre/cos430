/*
 *                        Revision History
 *  ==========================================================
 * 
 */
package superandsub;

/**
 *
 * @author Anne
 */
public class Subclass extends ConcreteSubSuper{
    private int property1;
    private int property2;
    
    /**
     *
     */
    public Subclass(){
        
        this(0,0,0,0,0,0);
    }

    /**
     *
     * @param superSuperProp1
     * @param superSuperProp2
     * @param superProp1
     * @param superProp2
     * @param property1
     * @param property2
     */
    public Subclass(int superSuperProp1, int superSuperProp2,
            int superProp1, int superProp2,
            int property1, int property2){
        super(superSuperProp1, superSuperProp2,
                superProp1, superProp2);
        this.property1 = property1;
        this.property2 = property2;
        System.out.println("Finished Executing: constructor in Subclass.");
        
    }
    
    /**
     * overrides the one in AbstractSuper
     */
    @Override
    public void p(){
        System.out.println("Executing SubClass's p()");
        property2 = property1 * property2;
    }
    /**
     * overrides the one in ConcreteSubSuper
     */
    @Override
    public void m(){
        System.out.println("Executing SubClass's m()");
        System.out.println("Calling: super.m();");
        super.m();
    }
    public String toString(){
        StringBuilder str = new StringBuilder();
        String eol = System.lineSeparator();
        str.append(super.toString()).append(eol);
        str.append(String.format("In the Subclass class "
                + "property1 is %d and property2 is %d", 
                   property1, property2));
        return str.toString();
    }
    
}
