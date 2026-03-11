/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication11;
import java.util.Scanner ;
/**
 *
 * @author kalalajoel
 */
public class JavaApplication11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your chess rating: ");
        int rating = s.nextInt();
        if(rating>=2400) {
            System.out.println("Senior Master");
        } else if (rating>=2200&&rating<=2399){
            System.out.println("National Master");
             } else if (rating>=2000&&rating<=2199){
                 System.out.println("Expert");
                 } else if (rating>=1200&&rating<=1999){ 
                     System.out.println("Non-Expert");
                      } else if (rating>=0&&rating<=1199){ 
                           System.out.println("Novice");
        }
        // TODO code application logic here
    }
    
}
