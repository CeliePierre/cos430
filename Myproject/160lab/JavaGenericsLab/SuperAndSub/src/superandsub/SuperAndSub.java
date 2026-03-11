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
public class SuperAndSub {
    public void run(){
        System.out.println("Executing: "
                + "ConcreteSubSuper css = new ConcreteSubSuper(1,2,3,4);");
        ConcreteSubSuper css = new ConcreteSubSuper(1,2,3,4);
        System.out.println("One ConcreteSubSuper object created \n");
        System.out.println("contents of css: \n" + css);
        System.out.println("\n==============================================\n");
        
        System.out.println("Executing: "
                + "Subclass sc = new Subclass(5,6,7,8,9,10);"); 
        Subclass sc = new Subclass(5,6,7,8,9,10);
        System.out.println("One Subclass object created \n");
        System.out.println("contents of sc: \n" + sc);
        System.out.println("\n==============================================\n");

        System.out.println("Calling: css.m(12);");
        css.m(12);
        System.out.println("contents of css: \n" + css);
        System.out.println("\n==============================================\n");
        System.out.println("Calling: css.p();");
        css.p();
        System.out.println("contents of css: \n" + css);
        System.out.println("\n==============================================\n");
        System.out.println("Calling: sc.m(15);");
        sc.m(15);
        System.out.println("contents of sc: \n" + sc);
        System.out.println("\n==============================================\n");
        System.out.println("Calling: sc.n();");
        sc.n();
        System.out.println("contents of sc: \n" + sc);
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // uses a default constructor provided by Java
        SuperAndSub ss = new SuperAndSub();
        ss.run();
        
    }
    
}
