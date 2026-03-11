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
public class ConcreteSubSuper extends AbstractSuper {
    private int property1;
    private int property2;
    
    /**
     *
     */
    public ConcreteSubSuper(){
        
        this(0,0,0,0);
    }

    /**
     *
     * @param superProp1
     * @param superProp2
     * @param property1
     * @param property2
     */
    public ConcreteSubSuper(int superProp1, int superProp2,
            int property1, int property2){
        super(superProp1, superProp2);
        this.property1 = property1;
        this.property2 = property2;
        System.out.println("Finished Executing: constructor in "
                + "ConcreteSubSuper");        
    }

    /**
     * There is an m in the super class
     */
    @Override
    public void m(){
        System.out.println("Executing ConcreteSubSuper's m()");
        property1 *= 2;
        System.out.println("Calling: super.m();");
        super.m();
    }
 
    /**
     * this one is an overloaded method:  same name, different signature.
     * @param p
     */
    public void m(int p){
        System.out.println("Executing ConcreteSubSuper's m(int)");
        property2 = p + property2;
    }
    @Override
    public int n() {
        System.out.println("Executing: ConcreteSubSuper's n()");
        System.out.println("Calling: super.p();");
        super.p();
        return super.property2;
    }
    public String toString(){
        StringBuilder str = new StringBuilder();
        String eol = System.lineSeparator();
        str.append(super.toString()).append(eol);
        str.append(String.format("In the ConcretSubSuper class "
                + "property1 is %d and property2 is %d", 
                   property1, property2));
        return str.toString();
    }


}
