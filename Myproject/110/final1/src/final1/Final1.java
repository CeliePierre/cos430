
package final1;


public class Final1 {

    
    
   
    public static void main(String[] args) {
        System.out.println(reformat("2020-05-02"));
      
    }
     public static String reformat(String s){
         String[] values = s.split("-");
         return String.format("%s/%s/%s",values[1],values[2],values[0]);
         
     }
    
}
