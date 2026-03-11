/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication14;
import java.util.Scanner;
/**
 *
 * @author kalalajoel
 */
public class JavaApplication14 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        System.out.println("Enter a dte in U.S. format: ");
        String date = s.next();
        String month = date.substring(0,date.indexOf("/"));
        String day = date.substring(date.indexOf("/")+1,date.lastIndexOf("/"));
        String year = date.substring(date.lastIndexOf(day)+1,date.length());
        System.out.println(day + "/"+ month + "/" + year);
        
        // TODO code application logic here
    }
    
}
