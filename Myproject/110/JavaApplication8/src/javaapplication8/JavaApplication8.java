/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication8;
import java.util.Scanner;2
/**
 *
 * @author kalalajoel
 */
public class JavaApplication8 {

    /**
     * @param args the command line arguments
     */

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner s = new Scanner(System.in);
        int entry;
   
    do {
        System.out.println("Enter a shape - Square (1), Triangle (2), Quit program (3):");
        entry = s.nextInt();
        if (entry==3) {
        } else {
        System.out.println("Enter a side length: ");
        if(entry==1) {
            int length = s.nextInt();
            for(int x = 0; x < length; x++) {
              for(int y = 0; y<length;y++) {
                if(x==0||x==length-1)  {
                    System.out.print("0");
                } else {
                   if(y==0||y==length-1) {
                       System.out.print("0");
                   } else {
                       System.out.print(" ");  
                   }
                }
              }
                System.out.println("");
            }
        } else if (entry==2) {
    int length = s.nextInt();
    for (int x = 0; x < length; x++) {
        for(int y = 0; y<x;y++) {
            if((x==0)||(x==length-1)) {
                System.out.printf("0");
            } else {
                if((y==0)||(y==x-1)) {
                    System.out.printf("0");
                } else {
                    System.out.printf(" ");
                }
            }
        }
        System.out.printf("\n");
        }
        }
        }
    } while (entry!=3);
    
    }
    }
Random r = new Random();
int pick + r.nextInt(100)+1;
Sytem.out.println(pick)
        // TODO code application logic here
    
    

