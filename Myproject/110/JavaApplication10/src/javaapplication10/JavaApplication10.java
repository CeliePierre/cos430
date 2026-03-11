/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication10;
import java.util.Scanner;
/**
 *
 * @author kalalajoel
 */
public class JavaApplication10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner (System.in);
        int inches = s.nextInt();
        int feet = inches/12;
        int yards = feet/3;
        int remainFeet = feet%3;
        int remainInches = inches%12;
        System.out.println("Yards: "+yards + " Feet: " + remainFeet +" Inches: " + remainInches);

}
    
}// TODO code application logic here
    
  
    


    

