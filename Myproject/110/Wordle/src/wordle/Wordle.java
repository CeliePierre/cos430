/* hey Mr keith Sawyer happy new year when run my projet you need to "tap on the window for 
writing the word" this project and when you got correct word 
"Win" message gonna sgow up as "you win " i created 3 class " wordle, Word,Dictionary"
Wordle.java which will be the driver program providing the user interface and game logic; 
Word.java which will read the list of official words and randomly choose an answer; 
Dictionary.java which will read the long list of 5 letter words and validate user entries.

*/



package wordle;

import acm.graphics.GRect;
import acm.gui.*;
import java.io.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Wordle extends GraphicsProgram {

    final int WINDOW_SIZE = 800, xoff = 200, yoff = 70, boxes_size = 86;
    static Wordle myWindow = new Wordle();
    static private Dictionary history;
    static private Word mysteryWord;
    JTextField[][] boxes = new JTextField[6][5];
    int currentrow = 0, currentcol = 0;
    String rowWord = "";
    String myWord = "";
    boolean[] entered = {false, false, false, false, false, false};
    boolean wordFound = false;
  JTextField Winner;
    public static void main(String[] args) {
        myWindow.start();

    }
    
    @Override
    public void mouseClicked(MouseEvent myMouse) {
        myWindow.requestFocus();
    }

    @Override
    public void init() {
        try {
            myWord = Word.getWord();

        } catch (IOException e) {

        }
        Winner = new JTextField();
        Winner.setText("You Win");
        Winner.setSize(100,40);
        Winner.setHorizontalAlignment(JTextField.CENTER);
        Winner.setVisible(false);
        myWindow.add(Winner,WINDOW_SIZE/2-50,xoff-180);
        Winner.setEditable(false);
        

        myWindow.setSize(WINDOW_SIZE, WINDOW_SIZE);
        myWindow.setTitle("wordle");
        //add a Reset button

        JButton myButton = new JButton("world");
        myWindow.add(myButton, SOUTH);
        for (int row = 0; row < 6; row++) {

            for (int col = 0; col < 5; col++) {
                boxes[row][col] = new JTextField();
                boxes[row][col].setEditable(false);
                boxes[row][col].setBackground(Color.red);
                boxes[row][col].setSize(boxes_size, boxes_size);
                boxes[row][col].setHorizontalAlignment(JTextField.CENTER);
                myWindow.add(boxes[row][col], xoff + boxes_size * col, yoff + boxes_size * row);

            }

        }
        myWindow.setBackground(Color.YELLOW);
        myWindow.addKeyListeners();
        myWindow.addMouseListeners();
        boxes[0][0].requestFocus();

    }

    public void colorRow() {
        if (rowWord.equals(myWord)) {
            wordFound = true;
            Winner.setVisible(true);
        }
        char[] guessLetter = new char[5];
        char[] wordLetter = new char[5];
        for (int a = 0; a < 5; a++) {
            guessLetter[a] = rowWord.charAt(a);
            wordLetter[a] = myWord.charAt(a);
        }
        for (int a = 0; a < 5; a++) {
            if (guessLetter[a] == wordLetter[a]) {
                boxes[currentrow][a].setBackground(Color.GREEN);

            } else {
                boolean letterInWord = false;
                int c = 0;
                while (!letterInWord && c < 5) {
                    if (guessLetter[a] == wordLetter[c]) {
                        letterInWord = true;
                    }
                    c++;
                }
                if (letterInWord) {
                    boxes[currentrow][a].setBackground(Color.YELLOW);
                } else {
                    boxes[currentrow][a].setBackground(Color.DARK_GRAY);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent myKey) {
        if (!wordFound) {
            char k = myKey.getKeyChar();
            if (k >= 97 && k <= 122 && currentcol<5) {
                do {
                    boxes[currentrow][currentcol].setText(String.format("%c", k).toUpperCase());
                    //if(currentcol<5) {
                    currentcol++;
                    //}

                } while (currentcol < 5 && !(boxes[currentrow][currentcol].getText().equals("")));
            } else if (k == 10) {
                boolean rowisfull = true;
                rowWord = "";
                for (int col = 0; col < 5; col++) {
                    if (boxes[currentrow][col].getText().equals("")) {
                        rowisfull = false;

                    }
                    rowWord += boxes[currentrow][col].getText();
                }
                try {
                    if (rowisfull && Dictionary.isValid(rowWord)) {
                        entered[currentrow] = true;
                        colorRow();
                        if (currentrow < 6) {
                            currentrow++;
                        }

                        currentcol = 0;
                    } else {
                        entered[currentrow] = false;
                    }
                } catch (IOException e) {

                }

            } else if (k == 8) {
                if (!entered[currentrow]) {
                    if (currentcol > 0) {
                        currentcol--;

                    }
                    boxes[currentrow][currentcol].setText("");
                }

            }
        }
    }
}
