/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wow2;
import java.util.*;
/**
 *
 * @author kalalajoel
 */
public class Wow2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        ArrayList<Dwarf> dwarfArmy = new ArrayList<>();
        Dwarf dwarf1 = new Dwarf("Theon");
        dwarfArmy.add(dwarf1);
        
        Dwarf dwarf2 = new Dwarf("Gimli");
        dwarfArmy.add(dwarf2);
        
       
        for(Dwarf d: dwarfArmy)
        System.out.println( dwarf1.toString());
        //System.out,println(dwarfl.CompareTo(dwarf2 ));
        
        while(dwarf1.isAlive() && dwarf2.isAlive()){
            dwarf1.strick(dwarf2);
         dwarf2.strick(dwarf1);
         System.out.println(dwarf1.getName() + " " + dwarf1.getHeath() + dwarf2.getName() + " " 
                 + dwarf2.getHeath());
    }
    
    if(dwarf1.CompareTo(dwarf2)==1)
        System.out.println(dwarf1.getName() + " WINS ");
    else if(dwarf1.CompareTo(dwarf2)==-1)
            
    System.out.println(dwarf2.getName() + " WINS! ");
    
    else
        System.out.println("TIE");
        System.out.println("Dwarfs created = " + Dwarf.getCount());
    
    Weapon w1 =new Weapon();
    w1.name = "Axe";
    w1.maxPoints = 10;
    
    dwarf1.addWeappon(w1);
        
}
}
