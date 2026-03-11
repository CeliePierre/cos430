package wordle;

   import java.util.*;
   import java.io.*;

public class Word {
      public static String getWord()throws IOException{
          ArrayList<String> words = new ArrayList<>();
          File f= new File("wordOfficial.txt");  
   Scanner scnr= new Scanner(f); 
   while(scnr.hasNextLine()){
       words.add(scnr.nextLine());
       
   }
   Random r = new Random();
   int pick = r.nextInt(words.size());
          System.out.println(words.get(pick));
   return words.get(pick);
      }
      
}


