/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication37;
import java.util.*;
/**
 *
 * @author kalalajoel
 */
public class JavaApplication37 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Random r =new Random();
        double[] nums = {1.2,3.5,4.43,5.13,53.3,324.333,15.15,3.33,34.2,5.55,1234.3333};
        System.out.printf("Average: %.2f\n", getAverage(nums));
    }
 
   public static double getAverage(double[] nums) {
        double total=0.0;
        for(int i=0;i<nums.length;i++){
            total+=nums[i];
        }
        return total/(double )nums.length;
    }
}
