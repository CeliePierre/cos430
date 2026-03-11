/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication13;

/**
 *
 * @author kalalajoel
 */
public class JavaApplication13 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double squareRoot;
        for(double i = 1;i<=10;i++) {
            squareRoot = Math.sqrt(i);
            System.out.printf("square root of %.0f = %.3f\n", i,squareRoot);
        }
    }
    
}
