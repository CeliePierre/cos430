package wordle;
import java.io.*;
import java.util.*;
public class Dictionary {
  public static boolean isValid(String s)throws IOException{
      
 
    File f= new File("wordListLong.txt");  
   Scanner scnr= new Scanner(f); 
   boolean valid = false;
   while(scnr.hasNextLine()){
       String line = scnr.nextLine();
       if(line.equalsIgnoreCase(s)){
        valid=true ; 
       }
       
   }
   return valid;
  }
  
}
    
    