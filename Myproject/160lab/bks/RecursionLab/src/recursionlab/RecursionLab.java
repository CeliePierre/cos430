
package recursionlab;

import java.util.Scanner;

/**
 *
 * @author kalalajoel
 */
public class RecursionLab {
       /**
     * variable count of int datatype
     */
    private static int count = 0;
    /**
     * method to calculate factorial of n
     * @param n
     * @return the factorial of a number n
     */
    public int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
    /**
     * method to calculate sum of numbers
     * @param n
     * @return sum of n numbers
     */
    public int sums(int n) {
        if (n == 0) {
            return 0;
        } else {
            return n + sums(n - 1);
        }
    }
    /**
     * method to calculate Fibonacci series
     * @param n
     * @return Fibonacci series
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }
    /**
     * method to display number of moves a disk is being moved
     * @param n
     * @param fromTower
     * @param toTower
     * @param auxTower
     */
    public void moveDisks(int n, char fromTower,
            char toTower, char auxTower) {
        if (n == 1) { // Stopping condition
            count++;
            System.out.println("move # " + count + " Move disk " + n + " from "
                    + fromTower + " to " + toTower);
        } else {
            moveDisks(n - 1, fromTower, auxTower, toTower);
            count++;
            System.out.println("move # " + count + " Move disk " + n + " from "
                    + fromTower + " to " + toTower);
            moveDisks(n - 1, auxTower, toTower, fromTower);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RecursionLab rl = new RecursionLab();
        System.out.println("10! = " + rl.factorial(10));
        for (int i = 2; i <= 10; i++) {
            System.out.println(" the sum of the numbers from 1 to " + i
                    + " is : " + rl.sums(i));
        }
        System.out.println("Fibonacci Numbers");
        for (int i = 0; i < 10; i++) {
            System.out.print(rl.fib(i));
            System.out.print(", ");
        }
        System.out.println(rl.fib(10));
        System.out.println("Towers of Hanoi");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of disks: ");
        int n = input.nextInt();
        // Find the solution recursively
        System.out.println("The moves are:");
        rl.moveDisks(n, 'A', 'B', 'C');
    }

}
