
package agilitycompetition;

/**
 *
 * @author kalalajoel
 */
public class Dog implements Comparable{
   private String name;
    private Date birthDate;
    private String breed;
    private double weight;
    private String owner;
    private ElapsedTime time;
    /**
     *
     * @param name The dogs name
     * @param birthDate The dogs birth date
     * @param breed The dogs breed
     * @param weight The dogs weight
     * @param owner The dogs owner
     * @param time The time it took the dog to finish the course
     */
    public Dog(Date birthDate, String breed, double weight,
                String name, String owner, ElapsedTime time){
        this.name = name;
        this.birthDate = birthDate;
        this.breed = breed;
        this.weight = weight;
        this.owner = owner;
        this.time = time;
    }
   
    public String getName(){
        return name;
    }
    public Date getBirthDate(){
        return birthDate;
    }
    public String getBreed(){
        return breed;
    }
    public String getOwner(){
        return owner;
    }
    public double getWeight(){
        return weight;
    }
    public ElapsedTime getTime(){
        return time;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        String eol = System.lineSeparator();
        str.append(String.format("%4d %4d %4d %-25s",
            birthDate.getDay(), birthDate.getMonth(),
            birthDate.getYear(), breed));
            str.append(String.format("%7.2f   %-15s%12s %d %d %d %d",
                    weight, name, owner, time.getHours(),
                    time.getMinutes(), time.getSeconds(),
                    time.getMilliseconds()));
            return str.toString();
    }
   
    @Override
    public int compareTo(Object that) {
        int comparison = 0;
        if (that == null){
            comparison = 1;
        } else if (this == that) {
            comparison = 0;
        } else {
            comparison = this.time.compareTo(((Dog)that).time);
            if (comparison == 0){
                comparison = this.getName().compareTo(((Dog)that).getName());
            }
        }
    return comparison;
    }
    public static void main(String[] args){
        Date date1 = new Date (7,26,2006);
        Date date2 = new Date (10,17,2017);
        Dog dog1 = new Dog(date1, "Toy Poodle", 10.2, "Eudora", "Anne",
                       new ElapsedTime(0,2,35,40));
        Dog dog2 = new Dog(date2, "Bulldog", 20.4, "Bubba", "Jesse",
                        new ElapsedTime(0,5,24,50));
        System.out.println(dog1);
        System.out.println(dog2);
        System.out.println("Testing compareTo()");
        System.out.println("dog1.compareTo(dog2): " + dog1.compareTo(dog2));
        System.out.println("dog1.compareTo(dog1): " + dog1.compareTo(dog1));
        System.out.println("dog2.compareTo(dog1): " + dog2.compareTo(dog1));
    }
} 