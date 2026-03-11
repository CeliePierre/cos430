/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package game.of.life;

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/*
hey Mr sawyer my project i tried to put some word in french to do a chalenge 
as the word "comptervoisin for neigbors" some of my is in french
for the statement i used :
* public class GAMEOFLIFE extends GraphicsProgram for the size of Y and X ,COLUMNS, ROWS
i declared 2 JTextFiel and 2Jbouton.
* public void init for rename bouton(Go and Reset)
Jbouton erase mode and drwan mode alson generation and population.
*i created CompteVoisin who has each neigbors
* i created  public void mouseDragged for compteVoisin and islife
* i got public void mouseClicked for pattern can be created with mouse clicks

The game starts with a grid of rectangle where you can identify some as being alive by
clicking on them i tried to coded: 
a cell with 2 or 3 neighbors survives to the next generation
a live cell with 4 or more neighbors dies from over population
a live cell with 0 or 1 neighbors dies from isolation 
Every empty cell with exactly 3 neighbors

joelkalala
11/11/2022
*/

public class GAMEOFLIFE extends GraphicsProgram {

    static GAMEOFLIFE myWindow = new GAMEOFLIFE();
    final int WINDOW_SIZE = 760;
    final int BOX_SIZE = WINDOW_SIZE / 50;
    final int X_START = WINDOW_SIZE / 20;
    final int Y_START = WINDOW_SIZE / 20;
    final int GRID_SIZE = WINDOW_SIZE / 90;
    final int ROWS = 40;
    final int COLUMNS = ROWS;
    GRect[][] boxes;
    Color boxColor;
    JTextField myInputBox;
    JTextField myInputBox1;
    JButton myButton;
    JButton myButton1, mode;
    int[][] clicks = new int[ROWS][COLUMNS], voisin = new int[ROWS][COLUMNS];
    boolean[][] isLife = new boolean[ROWS][COLUMNS];
int generation = 0;
    boolean erase = false, go = false;

    public static void main(String[] args) {
        myWindow.start();
    }

    @Override
    public void init() {
        myWindow.setSize(WINDOW_SIZE, WINDOW_SIZE);
        //add a Reset button
        myButton = new JButton("Go");
        myWindow.add(myButton, SOUTH);
        

       
        myButton1 = new JButton("Reset");
        myWindow.add(myButton1, SOUTH);

        boxes = new GRect[COLUMNS][ROWS];

        for (int row = 0; row < ROWS; row++) {
            int y = X_START + (BOX_SIZE * row);
            //
            for (int col = 0; col < COLUMNS; col++) {
                int x = Y_START + (BOX_SIZE * col);
                boxes[row][col] = new GRect(row * BOX_SIZE + Y_START, col * BOX_SIZE + X_START, BOX_SIZE, BOX_SIZE);
                //GRect box = new GRect(x, y, BOX_SIZE, BOX_SIZE);
                boxColor = new Color(0, 0, 0, 0);
                boxes[row][col].setFillColor(boxColor);
                boxes[row][col].setFilled(true);
                //boxes[row][col] = box;
                myWindow.add(boxes[row][col]);
                clicks[row][col] = 0;
                isLife[row][col] = false;
                voisin[row][col]=0;

            }

        }
        myWindow.addMouseListeners();
        myInputBox = new JTextField(20);
        myInputBox.setEditable(false);
        myWindow.add(myInputBox, SOUTH);
        myInputBox.setText("Generation: 0");

        myInputBox1 = new JTextField(15);
        myInputBox1.setEditable(false);
        myInputBox1.setText("Population: 0");
        myWindow.add(myInputBox1, SOUTH);
        
        mode = new JButton();
        mode.setText("erase mode");
        myWindow.add(mode, SOUTH);
        myWindow.addActionListeners();
    }

    public void compterVoisin(boolean[][] envie) {
        for (int rows = 0; rows < ROWS; rows++) {
            for (int col = 0; col < COLUMNS; col++) {

                voisin[rows][col] = 0;
                if (rows > 0 && rows < ROWS - 1 && col > 0 && col < COLUMNS - 1) {
                    for (int x = rows - 1; x <= rows + 1; x++) {
                        for (int y = col - 1; y <= col + 1; y++) {

                            if (envie[x][y] && !(x == rows && y == col)) {
                                voisin[rows][col]++;
                            }
                        }
                    }

                } else if (rows == 0 && col == 0) {
                    for (int x = rows; x <= rows + 1; x++) {
                        for (int y = col; y <= col + 1; y++) {
                            if (envie[x][y] && !(x == rows && y == col)) {
                                voisin[rows][col]++;
                            }

                        }
                    }

                } else if (rows == 0 && col == COLUMNS - 1) {
                    for (int x = rows; x <= rows + 1; x++) {
                        for (int y = col - 1; y <= col; y++) {
                            if (envie[x][y] && !(x == rows && y == col)) {
                                voisin[rows][col]++;
                            }

                        }
                    }

                } else if (rows == ROWS - 1 && col == 0) {
                    for (int x = rows - 1; x <= rows; x++) {
                        for (int y = col; y <= col + 1; y++) {
                            if (envie[x][y] && !(x == rows && y == col)) {
                                voisin[rows][col]++;
                            }

                        }
                    }

                } else if (rows == ROWS - 1 && col == COLUMNS - 1) {
                    for (int x = rows - 1; x <= rows; x++) {
                        for (int y = col - 1; y <= col; y++) {
                            if (envie[x][y] && !(x == rows && y == col)) {
                                voisin[rows][col]++;
                            }
                        }
                    }

                } else if (rows == 0 && col > 0 && col < COLUMNS - 1) {
                    for (int x = rows; x <= rows + 1; x++) {
                        for (int y = col - 1; y <= col + 1; y++) {
                            if (envie[x][y] && !(x == rows && y == col)) {
                                voisin[rows][col]++;
                            }
                        }
                    }

                } else if (rows == ROWS - 1 && col > 0 && col < COLUMNS - 1) {
                    for (int x = rows - 1; x <= rows; x++) {
                        for (int y = col - 1; y <= col + 1; y++) {
                            if (envie[x][y] && !(x == rows && y == col)) {
                                voisin[rows][col]++;
                            }
                        }
                    }

                } else if (col == 0 && rows > 0 && rows < ROWS - 1) {
                    for (int x = rows - 1; x <= rows + 1; x++) {
                        for (int y = col; y <= col + 1; y++) {
                            if (envie[x][y] && !(x == rows && y == col)) {
                                voisin[rows][col]++;
                            }
                        }
                    }

                } else if (col == COLUMNS - 1 && rows > 0 && rows < ROWS - 1) {
                    for (int x = rows - 1; x <= rows + 1; x++) {
                        for (int y = col - 1; y <= col; y++) {
                            if (envie[x][y] && !(x == rows && y == col)) {
                                voisin[rows][col]++;
                            }
                        }
                    }

                }

            }

        }

    }

    @Override
    public void mouseDragged(MouseEvent myGame) {
        compterVoisin(isLife);
        for (int rows = 0; rows < ROWS; rows++) {
            for (int col = 0; col < COLUMNS; col++) {
                boolean ibox = boxes[rows][col].contains(myGame.getX(), myGame.getY());
                if (ibox && !go) {

                    if (!erase) {
                        if (!isLife[rows][col]) {
                            clicks[rows][col]++;
                            boxes[rows][col].setFillColor(Color.black);
                    isLife[rows][col] = true;
                        }
                    }
                    
                } else {
                    if(erase){
                    isLife[rows][col] = false;
                    boxes[rows][col].setFillColor(boxColor);
                    }
                }

            }

        }

    }

    @Override
    public void mouseClicked(MouseEvent myGame) {
        compterVoisin(isLife);
        for (int rows = 0; rows < ROWS; rows++) {
            for (int col = 0; col < COLUMNS; col++) {
                boolean ibox = boxes[rows][col].contains(myGame.getX(), myGame.getY());
                if (ibox && !go) {
                    clicks[rows][col]++;

                    isLife[rows][col] = clicks[rows][col] % 2 - 1 == 0;
                    if (isLife[rows][col]) {
                        boxes[rows][col].setFillColor(Color.black);
                    } else {

                        boxes[rows][col].setFillColor(boxColor);
                    }

                }

            }

        }
    }
    public void pop(){
        int population =0;
        for (int rows = 0; rows < ROWS; rows++) {
                    for (int col = 0; col < COLUMNS; col++) {
                      if(isLife[rows][col]){
                          population++;
                          myInputBox1.setText("Population: " + population);
                      }  
                    }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent myAction) {
        compterVoisin(isLife);
        String command = myAction.getActionCommand();
        if (command.equals("erase mode")) {

            erase = true;
            mode.setText(" draw mode");
        }
        if (command.equals(" draw mode")) {

            erase = false;
            mode.setText("erase mode");
        }
        if (command.equals("Go")) {
            go = true;
            myButton.setText("Pause");

        }
        if (command.equals("Pause")) {
            go = false;
            myButton.setText("Go");

        }
        if(command.equals("Reset")){
           
            for (int rows = 0; rows < ROWS; rows++) {
                    for (int col = 0; col < COLUMNS; col++) {
                        boxes[rows][col].setFillColor(boxColor);
                        isLife[rows][col]=false;
                        clicks [rows][col]=0;
                        voisin [rows][col]=0;
                         myInputBox.setText("Generation: 0");
            myInputBox1.setText("Population: 0");
                    }
                   
                    }
            
        }
    }

    @Override
    public void run() {
        
        while (true) {
          pop();  
             compterVoisin(isLife);
            if (go) {
                
                myWindow.pause(10);
                
                generation++;
                myInputBox.setText("Generation: "+ generation);
                for (int rows = 0; rows < ROWS; rows++) {
                    for (int col = 0; col < COLUMNS; col++) {
                        if (isLife[rows][col]) {
                            if (voisin[rows][col] == 3 || voisin[rows][col] == 2) {
                                boxes[rows][col].setFillColor(Color.BLACK);
                                isLife[rows][col] = true;
                            }
                            if (voisin[rows][col] < 2 || voisin[rows][col] > 3) {
                                boxes[rows][col].setFillColor(boxColor);
                                isLife[rows][col] = false;
                            }
                        } else {
                            if (voisin[rows][col] == 3) {
                                boxes[rows][col].setFillColor(Color.BLACK);
                                isLife[rows][col] = true;
                            }
                        }
                    }
                }

            }
        }
      
    }
}
