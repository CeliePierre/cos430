
package final3;
import java.io.*;
import java.util.*;

public class Final3 {

   
    public static void main(String[] args) throws IOException{
        File f = new File("integerList.dat");
        Scanner s = new Scanner(f);
         ArrayList<Integer> numbers = new ArrayList<>();
         int total =0;
         int count=0;
         while(s.hasNextInt()){
             numbers.add(s.nextInt());
             total+=numbers.get(numbers.size()-1);
             count++;
         }
        System.out.printf("Average = %.2f\n",(double)total/count);
    }
    
}
