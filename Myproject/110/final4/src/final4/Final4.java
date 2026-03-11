
package final4;

public class Final4 {
    private String OwnerName;
    private int AcctNbr;
    private double debits;
    private double credits;
   
    public Final4(String w,int n){
      this.OwnerName =w;
        this.AcctNbr =n;
        this.debits= 0;
        this.credits = 0;
        
       
    }
    /**
     * 
     * @param c 
     * i add credits
     */
        
         
    public void addCredit(double c){
        credits=credits+c;
        
    }
    /**
     * 
     * 
     * formatting the account information
     */
    
    public String toString(){
        double c = credits-debits;
        return "Name is:"+ OwnerName+" "+"Account Number:"+ AcctNbr+" "+"Current Balance:"+" "+c;
    }
}

     



   
      
