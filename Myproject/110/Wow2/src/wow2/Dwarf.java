/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wow2;

import java.util.ArrayList;

/**
 *
 * @author kalalajoel
 */
public class Dwarf {
    private String name;
    private int heathPoints=0;
     private     ArrayList<Weapon> weaponList = new ArrayList<>();
     private static int dwarfCount = 0;
    
    
    public Dwarf(){
        
        name= "Default";
       heathPoints=100 ;
       dwarfcount++;
       
       
        
    }
    public Dwarf(String n){
        setName(n);
        
        heathPoints=100 ;
        int dwarfcount = 0;
        dwarfcount++;
        
    }
    
    public void addWeappon(Weapon w){
        weaponList.add(w);
        
    }
    public static int getCount(){
        return Dwarf.dwarfCount;
    }
    public String getName(){
        return name;
        
    }
    public int getHeath() {
        return heathPoints;
    }
   public void setName (String name) { 
       this.name=name;
   }
    public void setHeath(int hp){
      heathPoints=hp;  
    }
    public boolean isAlive(){
            return heathPoints>0;
            }
    @Override
    public String toString(){
        return "Name: " + name + "Heath:" + heathPoints;
    }
    public int CompareTo(Dwarf d){
        int result = 0;
        if(this.heathPoints>d.heathPoints)
            result =1;
        else if (this.heathPoints<d.heathPoints)
            result=-1;
        return result;
      
    }
     public int strike(Dwarf d){
   Random r= new Random();
   int points= + r.nextInt(10) +1 ;
  d.heathPoints = Math.max(d.heathPoints-points,0);
        
        
     }
}
