/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vetcliniq;

import acm.gui.*;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import acm.graphics.*;
import java.awt.Color;

/**
 *
 * @author kalalajoel
 */
public class VETCLINIQ extends GraphicsProgram {

    static VETCLINIQ myWindow = new VETCLINIQ();
    JTextField myInputBox;
    JTextField myInputBox1;
    JTextField myInputBox2;
    DoubleField myInputBox3;
    JButton myButton;
    JButton myButton1;
    JTextField Output = new JTextField(36);
     ArrayList<Dog> ChienList = new ArrayList<>();
Dog heaviest= new Dog();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        myWindow.start();

    }

    public int ligne(boolean Gauche) {
        int valeur = 0;

        if (Gauche) {
            valeur = 100;

        } else {
            valeur = 250;

        }
        return valeur;
    }

    @Override

    public void init() {

        myWindow.setTitle("VET_CLINIC");

        GLabel myLabel = new GLabel(" Dog_Name");
        myWindow.add(myLabel, ligne(true), 100 + myLabel.getHeight() / 2);

        myInputBox = new JTextField(20);
        myWindow.add(myInputBox, ligne(false), 100);

        GLabel myLabel1 = new GLabel(" Owner_Name");

        myWindow.add(myLabel1, ligne(true), 150 + myLabel1.getHeight() / 2);

        myInputBox1 = new JTextField(20);
        myWindow.add(myInputBox1, ligne(false), 150);

        GLabel myLabel2 = new GLabel(" Dog_Breed");
        myWindow.add(myLabel2, ligne(true), 200 + myLabel2.getHeight() / 2);

        myInputBox2 = new JTextField(20);
        myWindow.add(myInputBox2, ligne(false), 200);

        GLabel myLabel3 = new GLabel(" Dog_Weigth");
        myWindow.add(myLabel3, ligne(true), 250 + myLabel3.getHeight() / 2);

        myInputBox3 = new DoubleField();
        myInputBox3.setSize(myInputBox2.getWidth(), myInputBox2.getHeight());
        myWindow.add(myInputBox3, ligne(false), 250);

        myButton = new JButton("Add dog");
        myWindow.add(myButton, ligne(true), 300);

        myButton1 = new JButton("Get Heavy");

        myWindow.add(myButton1, ligne(false), 300);
        myWindow.add(Output, ligne(true), 350);
        myWindow.addActionListeners();
    }
  public void findDog(){
      
      double maxWeigth= ChienList.get(0).getWeigth();
      heaviest=ChienList.get(0);
      for(Dog d:ChienList){
          if (d.getWeigth()>maxWeigth){
              heaviest=d;
          }
      }
  }
    @Override
    public void actionPerformed(ActionEvent myAction) {
        String button = myAction.getActionCommand();
        String Dog_Name = "";
        String Owner_Name = "";
        String Dog_Breed = "";
        double Dog_Weigth = 0.0;
        if (button.equals("Add dog")) {
            Dog_Name = myInputBox.getText();
            Owner_Name = myInputBox1.getText();
            Dog_Breed = myInputBox2.getText();
            Dog_Weigth = myInputBox3.getValue();

            if (Dog_Name.equals("") || Owner_Name.equals("") || Dog_Breed.equals("")) {
                if (Dog_Name.equals("")) {

                }
                if (Owner_Name.equals("")) {

                }
                if (Dog_Breed.equals("")) {

                }

            } else {
                Dog d = new Dog();
                d.setName(Dog_Name );
                d.setownName(Owner_Name);
                d.setBreed(Dog_Breed);
                d.setWeight(Dog_Weigth);
                
                ChienList.add(d);
                
            } 
            

        }else if(button.equals(myButton1.getText())){
         findDog();
            Output.setText("Heaviest dog "+heaviest.toString());
        }
            

    }

}
