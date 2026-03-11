/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication35;
import java.util.*;
/**
 *
 * @author kalalajoel
 */
public class JavaApplication35 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a reduis of a sphere:" );
        double raduis =s.nextDouble();
        System.out.printf("volume: %.2f\n",calcVolume(raduis));
    }
    // calculate the volume of a sphere
    public static double calcVolume(double raduis){
        double volume;
        volume = Math.pow(raduis, 3)*(4.0/3.0);
        
        return volume;
       
    }
}
