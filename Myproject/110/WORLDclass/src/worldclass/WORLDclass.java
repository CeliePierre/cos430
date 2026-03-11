/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package worldclass;
import java.io.*;
import java.util.*;

public class WORLDclass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        File inFile = new File("names2.");
        try(Scanner inLine = new Scanner(inFile)) {
            while(inLine.hasNextLine()){
                String data = inLine.nextLine();
                int comma = data.indexOf(",");
                //System.out.println(data.substring(comma+1) + " " + data.substring(0,comma));
System.out.println(data);
            }
            inLine.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    }
    
}
