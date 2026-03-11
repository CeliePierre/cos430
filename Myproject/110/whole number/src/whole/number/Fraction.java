/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package whole.number;

/**
 *
 * @author kalalajoel
 */
public class Fraction {
    private int wholeNbr;
    private int numerator;
    private int denominator;
   
    public Fraction(){
        this.wholeNbr = 0;
        this.numerator = 0;
        this.denominator = 1;
    }
   
    public Fraction(int w, int n, int d){
        this.wholeNbr = w;
        this.numerator = n;
        this.denominator = d;
    }
    @Override
    public String toString(){
        return this.wholeNbr + " " + this.numerator + "/" + this.denominator;
    
}
     public void reduce (){
         this.wholeNbr +=this.numerator/this.denominator;
         this.numerator % = this.denominator;
         for(int i = this.numerator; i>=2; i--){
             if(this.numerator % i == 0 && this.denominator % i == 0)
                 this.numerator /=i;
             this.denominator
         }
     }
      static public Fraction copyof(Fraction f){
          Fraction f1= new Fraction(f.wholeNbr,f.numerator,f.denominator);
          return f2;
          
      }
      
}
