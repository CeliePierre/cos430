/**
 * File Name: PgmImage.java
 * Class: PgmImage
 * Author: S. Stansfield (in C++)
 * Adapted by : A.ann  (in Java) 01/17/23
 * Revision: 7/12/2022  added the property maxGV 
 * 
 */
package pgmImage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Description: This class permits reading and display of gray scale images 
 * in the pgm format.   Images are read from a file (must have .pgm extension)
 * Images are displayed on a JPanel inside a label object. (So to use the 
 * displayImage method, you must use this class with a JFrame Form.)
 * Note:  You can create a .pgm image from color images in other format 
 * using software that reads and writes the pgm format (such as Paint 
 * Shop Pro.)  First change the image to gray scale, then save as pgm. There
 * are converters on the web.
 * @author aapplin
 */
public class PgmImage {
    /**
     * The number of columns in the image.
     */
    
    public int width;   

    /**
     * The number of rows in the image.
     */
    public int height;  
    
    /**
     * The largest gray value in this image.
     */
    public int maxGV;
    
    /**
     * a two dimensional array of ints. Each element
     * is a grey scale pixel in the image.
     */
    public int[][] image;

    /**
     *  Creates a new image by copying the input image.
     *  modifies, height, width, and the image that called the method
     * @param imageToBeCopied A PgmImage to be duplicated
     */
    public void copyImage(PgmImage imageToBeCopied) {
        width = imageToBeCopied.width;
        height = imageToBeCopied.height;
        if (width > 0 && height > 0) {
            image = new int[height][width];
            maxGV = imageToBeCopied.maxGV;
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    image[row][col] = imageToBeCopied.image[row][col];
                }
            }
        } else {
            image = null;
        }
    }

    /**
     * Creates an image array with all the pixels having the same gray value
     * @param width the number of columns in the image
     * @param height the number of rows in the image
     * @param grayVal the color that all pixels will be set to
     */
    public void makeBlankImage(int width, int height, int grayVal) {
        this.width = width;
        this.height = height;
        if (width > 0 && height > 0) {
            image = new int[height][width];
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    image[row][col] = grayVal;
                }
            }
        } else {
            image = null;
        }
    }

    /**
     * Loads a pre-existing image from a file into the image array
     * if the file does not exist or is not in the right format
     * the image will be set to null and the height and width will be
     * set to zero.  Attempting to access the objects members will result
     * in a run time error
     * @param fileName the name of the file as ANSI String
     */
    public void loadFile(String fileName) {
        String fileType;
        if (fileName.indexOf('.') == -1)
            fileName = fileName + ".pgm";
        Scanner inFile = null;
        // now try to connect the sybolic name to the physical file
        try {            
            inFile = new Scanner(new FileReader(fileName));
            // if the physical file doesn't exist it throws an exception
            fileType = inFile.next();
            if (fileType.equals("P2") || fileType.equals("P5")) {
                width = inFile.nextInt();
                height = inFile.nextInt();
                maxGV = inFile.nextInt(); 
                image = new int[height][width];
                for (int row = 0; row < height; row++) {
                    for (int col = 0; col < width; col++) {
                        image[row][col] = inFile.nextInt();
                    }
                }
            } else {
                image = null;
                width = 0;
                height = 0;
                maxGV = 0;
            }
        } catch (FileNotFoundException ex) {
            // we catch it and print an error message
            System.out.println("File " + fileName + " not found");
            image = null;
            width = 0;
            height = 0;            
            maxGV = 0;
        }        
    }

    /**
     * Displays the image in the JLabel on the JFrame that is passed in.
     * @param frame a JFrame onto which the image, contained in a label,
     *    will be placed.
     * @param label a JLabel into which the image will be loaded
     */
    public void displayImage(ImageFrame frame, JLabel label) {      
        
        BufferedImage bufImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int grey = image[y][x];
                int rgb = ((grey << 16) | (grey << 8) | (grey));
                bufImage.setRGB(x, y, rgb);
            }
        }
        label.setSize(width, height);
        label.setIcon(new ImageIcon(bufImage));
    }
}
