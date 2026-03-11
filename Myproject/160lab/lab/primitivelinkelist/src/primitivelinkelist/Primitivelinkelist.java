
package primitivelinkelist;

/**
 *
 * @author kalalajoel
 */
public class Primitivelinkelist {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   Linkedlist list = new Linkedlist();
        for (int i = 20; i >= 0; i--) {
            list.add(i);
        }
        System.out.println("The original list.");
        System.out.println(list.toString());
        Integer object = 15; // autoboxing occurs
        System.out.println("Integer object = 15;\nlist.remove(object);");
        list.remove(object);
        System.out.println(list.toString());
        System.out.println("list.remove(12); ");
        list.remove(12);
        System.out.println(list.toString());
        System.out.println("list.set(10, 100)");
        System.out.print(" old value of element [10] ");
        //System.out.println(list.set(10, 100));
        System.out.println(list.toString());
    }
}