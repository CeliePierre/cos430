
package final2;
import java.util.*;

public class Final2 {
    static final double OUNCE = 28.3495;
    static final  int POUND =16;
     public static double fun(int pounds, double ounces){
         
         
     double kg=0;
     kg=(pounds*POUND+ounces)*OUNCE/1000;
     return kg;
}

       
         
  public static void main(String[] args){
        
       
        
        System.out.printf("%.2f\n",fun(1,0));
                
            }
    }
    

