/*
 *       Revision History newest first 
 * date      what you did            who you are
 * 
 */
package matrixmultiplication;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Random;

/**
 *
 * @author aapplin
 */
public class MatrixMultiplication {

    //private int dim = 64;
    // c (i,j) = a (i,k) * b (k,j)
    private int[][] matrixA;
    private int[][] matrixB;
    private int[][] matrixC;
    private String loopOrders = "      I J K      I K J    "
            + "  J I K      J K I    "
            + "  K I J      K J I";

    public void generateArray(int[][] a) {
        Random rand = new Random();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = (rand.nextInt(11) - 5); // -5 .. 5
            }
        }
    }

    /**
     * For an Array A M X N, and Array B N X P the resultant array will be Array
     * C of dimensions M X P Columns of a must match rows of b c (i,j) = a (i,k)
     * * b (k,j)
     *
     * @param a an array of ints
     * @param b an array of ints
     * @return an array of ints or null if a.columns != b.rows
     */
    public int[][] matMultIJK(int[][] a, int[][] b) {
        int[][] c = null;
        if (a[0].length == b.length) { // columns a == rows b
            c = new int[a.length][b[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < b.length; k++) {
                        c[i][j] += (a[i][k] * b[k][j]);
                    }
                }
            }
        }
        return c;
    }

    public int[][] matMultIKJ(int[][] a, int[][] b) {
        int[][] c = null;
        if (a[0].length == b.length) {
            c = new int[a.length][b[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int k = 0; k < b.length; k++) {
                    for (int j = 0; j < b[0].length; j++) {
                        c[i][j] += (a[i][k] * b[k][j]);
                    }
                }

            }
        }
        return c;
    }

    public int[][] matMultJIK(int[][] a, int[][] b) {
        int[][] c = null;
        if (a[0].length == b.length) {
            c = new int[a.length][b[0].length];
            for (int j = 0; j < b[0].length; j++) {
                for (int i = 0; i < a.length; i++) {
                    for (int k = 0; k < b.length; k++) {
                        c[i][j] += (a[i][k] * b[k][j]);
                    }
                }

            }
        }
        return c;
    }

    public int[][] matMultJKI(int[][] a, int[][] b) {
        int[][] c = null;
        if (a[0].length == b.length) {
            c = new int[a.length][b[0].length];
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    for (int i = 0; i < a.length; i++) {
                        c[i][j] += (a[i][k] * b[k][j]);
                    }
                }

            }
        }
        return c;
    }

    public int[][] matMultKIJ(int[][] a, int[][] b) {
        int[][] c = null;
        if (a[0].length == b.length) {
            c = new int[a.length][b[0].length];
            for (int k = 0; k < b.length; k++) {
                for (int i = 0; i < a.length; i++) {
                    for (int j = 0; j < b[0].length; j++) {
                        c[i][j] += (a[i][k] * b[k][j]);
                    }
                }

            }
        }
        return c;
    }

    public int[][] matMultKJI(int[][] a, int[][] b) {
        int[][] c = null;
        if (a[0].length == b.length) {
            c = new int[a.length][b[0].length];
            for (int k = 0; k < b.length; k++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int i = 0; i < a.length; i++) {
                        c[i][j] += (a[i][k] * b[k][j]);
                    }
                }

            }
        }
        return c;
    }

    public void run() {
        StringBuilder str = new StringBuilder();
        System.out.println("Square arrays of size N named a, b, and c.");
        
        System.out.println("Matrix multiplication equation:"
                + " c (i,j) = a (i,k) * b (k,j)");
        System.out.println("This requires 3 nested for loops "
                + "using i, j, and k as loop control variables.");
        System.out.println("The single calculation statement is executed "
                + "N^3 times");
        System.out.println("A dot appears at the end of each iteration.  "
                + "So they show progress through the code.");
        System.out.println("Each loop is run 5 times and the execution time "
                + "is an average of those 5 iterations.");
        System.out.println("Time shown is in seconds\n");
        for (int dim = 64; dim < 2049; dim *= 2) {
            matrixA = new int[dim][dim];
            generateArray(matrixA);
            matrixB = new int[dim][dim];
            generateArray(matrixB);
            long sum = 0;
            double avg;
            
            System.out.println("\nThe size of N: " + dim);
            System.out.println("n^3 = " + Math.pow(dim, 3));
            System.out.printf("The size of the 3 arrays in memory "
                    + "is %5.2f GB.%n",                   
                      ((3 * Math.pow(dim, 2) * 4)/ 1000000.0));
            str = new StringBuilder();
            str.append(loopOrders).append("\n   ");
            System.out.print("      ");
            for (int i = 0; i < 5; i++) {
                long start = System.currentTimeMillis();
                matrixC = matMultIJK(matrixA, matrixB);
                long end = System.currentTimeMillis();
                sum += end - start;
                System.out.print(".");
            }
            str.append(String.format("%8.3f", (sum / 5)/1000.0));
            System.out.print("      ");
            sum = 0;
            for (int i = 0; i < 5; i++) {
                long start = System.currentTimeMillis();
                matrixC = matMultIKJ(matrixA, matrixB);
                long end = System.currentTimeMillis();
                sum += end - start;
                System.out.print(".");
            }
            str.append(String.format("%11.3f", (sum / 5)/1000.0));
            System.out.print("      ");            
            sum = 0;
            for (int i = 0; i < 5; i++) {
                long start = System.currentTimeMillis();
                matrixC = matMultJIK(matrixA, matrixB);
                long end = System.currentTimeMillis();
                sum += end - start;
                System.out.print(".");
            }
            str.append(String.format("%11.3f", (sum / 5)/1000.0));
            System.out.print("      ");
            sum = 0;
            for (int i = 0; i < 5; i++) {
                long start = System.currentTimeMillis();
                matrixC = matMultJKI(matrixA, matrixB);
                long end = System.currentTimeMillis();
                sum += end - start;
                System.out.print(".");
            }
            str.append(String.format("%11.3f", (sum / 5)/1000.0));
            System.out.print("      ");
            sum = 0;
            for (int i = 0; i < 5; i++) {
                long start = System.currentTimeMillis();
                matrixC = matMultKIJ(matrixA, matrixB);
                long end = System.currentTimeMillis();
                sum += end - start;
                System.out.print(".");
            }
            str.append(String.format("%11.3f", (sum / 5)/1000.0));
            System.out.print("      ");
            sum = 0;
            for (int i = 0; i < 5; i++) {
                long start = System.currentTimeMillis();
                matrixC = matMultKJI(matrixA, matrixB);
                long end = System.currentTimeMillis();
                sum += end - start;
                System.out.print(".");
            }
            System.out.println();
            str.append(String.format("%11.3f", (sum / 5)/1000.0));
            System.out.println(str.toString());
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MatrixMultiplication mat = new MatrixMultiplication();
        mat.run();

    }

}
