/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vetcliniq;

/**
 *
 * @author kalalajoel
 */
public class Dog {
private String name;
private String ownName;
private String Breed;
private double weigth;




   public String size(){
       if(weigth <= 10)
       return "small";
       else if (weigth <=30 && weigth > 10)
           return "meduim";
       return "large";          
   } 
   public String getName(){
        return name;
   }
   public void setName(String name) {
       this.name =name;
   }
   public String getownName(){
       return ownName;
   }
   public void setownName(String ownName){
      this.ownName=ownName; 
   }
   public String getBreed(){
       return Breed;
   }
   public void setBreed(String Breed){
       this.Breed = Breed;
   }
  
     public double getWeigth(){
       return weigth;
   }
   public void setWeight(double weight){
    this.weigth = weight;  
   }
   @Override
     public String toString(){
         String result = name+" ("+ Breed+"), "+String.format("%.1f",weigth)+" lbs owned by "+ownName;
         return result;
     }     
}
