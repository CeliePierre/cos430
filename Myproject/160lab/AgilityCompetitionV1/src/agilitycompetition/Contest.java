
package agilitycompetition;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author kalalajoel
 */
public class Contest {
    
    private String location;
    private String sponsor;
    private Date date;
    private ArrayList<Dog> contestants; //update UML
    private Dog winner;
    private Dog second;
    private Dog third;

    public Contest(String location, String sponsor, Date date) {
        this.location = location;
        this.sponsor = sponsor;
        this.date = date;
        contestants = new ArrayList<>();
    }

    public void addContestant(Dog d){
        this.contestants.add(d);
    }
    public void determineWinners(){
        Collections.sort(contestants);
        winner = contestants.get(0);
        second = contestants.get(1);
        second = contestants.get(2);
    }

    public String getLocation() {
        return location;
    }

    public String getSponsor() {
        return sponsor;
    }

    public Date getDate() {
        return date;
    }
   
    public Dog getContestantAt(int i){
        Dog dog = null;
        if(i < contestants.size()){
            dog = contestants.get(i);
        }
        return dog;
    }
   
    public int getNumberOfContestants(){
        return contestants.size();
    }

    public Dog getWinner() {
        return winner;
    }

    public Dog getSecond() {
        return second;
    }

    public Dog getThird() {
        return third;
    }
}


    

