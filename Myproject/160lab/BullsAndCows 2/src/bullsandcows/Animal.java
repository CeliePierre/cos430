package bullsandcows;

import java.util.ArrayList;

/**
 *
 * @author kalalajoel
 */
public class Animal {

    private String registrationNumber;
    private String name;
    private String sireRegNum;
    private String damRegNum;
    private char gender;
    private int month;
    private int day;
    private int year;
    private ClassificationScore classificationScore;

       public Animal(String registrationNumber, String name, String sireRegNum, String damRegNum, char gender, int month, int day, int year, ClassificationScore classificationScore) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.sireRegNum = sireRegNum;
        this.damRegNum = damRegNum;
        this.gender = gender;
        this.month = month;
        this.day = day;
        this.year = year;
        this.classificationScore = classificationScore;
    }
    @Override
      public String toString() {
        return "Animal #registrationNumber : " + registrationNumber + " #name: " + name + " #sireRegNum: " + sireRegNum 
       + " #damRegNum: " + damRegNum + " # gender : " + gender + " # month: " + month + " #day : " + day 
       + " #year : " + year + " # classificationScore : " + classificationScore;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getName() {
        return name;
    }

    public String getSireRegNum() {
        return sireRegNum;
    }

    public String getDamRegNum() {
        return damRegNum;
    }

    public char getGender() {
        return gender;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public ClassificationScore getClassificationScore() {
        return classificationScore;
    }
    
    
public void createAnimal() {
    String registrationNumber = "12345";
    String name = "My Animal";
    String sireRegNum = "12345";
    String damRegNum = "67890";
    char gender = 'm';
    int month = 4;
    int day = 8;
    int year = 2000;
   // ClassificationScore classificationScore = new ClassificationScore(1, 2, 2010, 30, 20, 20, 30);

    Animal animal = new Animal(registrationNumber, name, sireRegNum, damRegNum, gender, month, day, year, classificationScore);
}

 /**
     * Unit Tester for Animal, Cow and Bull.  Copy this into Animal to unit
     *  test both Cow and Bull. 
     * @param args command line arguments
     */
        public static void main(String[] args) {
        ClassificationScore perfectCow = 
              new CowClassificationScore(1, 2, 2010, 30, 20, 20, 30);
        ClassificationScore nullCow = null;
        ClassificationScore perfectBull = 
              new BullClassificationScore(5, 6, 2012, 40, 30, 30);
        ClassificationScore nullBull = null;

        Proving noProving = null;
        Proving  proving = new Proving(6, 4, 2016, 20, 200,
                        1500, 3.25, 324);
        ArrayList<LoctationRecord> empty = null;
        ArrayList<LoctationRecord> lacRecs = new ArrayList<>();
        lacRecs.add(new LoctationRecord(2, 10, 228, 17232, 5.3));
        lacRecs.add(new LoctationRecord(3, 2, 178, 3290, 4.0));
        lacRecs.add(new LoctationRecord(4, 3, 260, 266, 3.2));
        Animal bull1 = new Bull("0001", "0002", "0003", 'm', 4, 8, 2000,
                "aBull", perfectBull, noProving);
        Animal bull2 = new Bull("0001", "0002", "0003", 'm', 4, 8, 2000,
                "anotherBull", nullBull, proving);
        Animal cow1 = new Cow("0001", "0002", "0003", 'f', 4, 8, 2000,
                "aCow", perfectCow, empty);
        Animal cow2 = new Cow("0001", "0002", "0003", 'f', 4, 8, 2000,
                "anotherCow", nullCow, lacRecs);
        System.out.println(bull1);
        System.out.println("Expected Output:\nRegistration number : 0001\n"
                   + "Name: aBull\n"
                   + "Born: 04-08-2000\n"
                   + "Classification: Date = 05-06-2012 GA = 40 DC = 30 BC = 30"
                   + " Total = 100\n"
                   + "Proving: n/a\n");
        System.out.println();
        System.out.println(bull2);
        System.out.println("Expected Output:\nRegistration number : 0001\n"
                   + "Name: anotherBull\n"
                   + "Born: 04-08-2000\n"
                   + "Classification: n/a\n"
                   + "Proving: Proving: Date = 06-04-2016\n"
                   + "Dtrs = 20 Records = 200\n"
                   + "AveMilk = 1500 AveBf% = 3.25 AveBf = 48\n"
                   + "ExImp = +324.00\n" );
        System.out.println();
        System.out.println(cow1);
                System.out.println("Expected Output:\nRegistration number : 0001\n"
                   + "Name: aCow\n"
                   + "Born: 04-08-2000\n"
                   + "Classification: Date = 01-02-2010 GA = 30 DC = 20 BC = 20"
                   + " MS = 30 Total = 100\n"
                   + "No Milk Records\n" );
        System.out.println();
        System.out.println(cow2);
        System.out.println("Expected Output:\nRegistration number : 0001\n"
                   + "Name: anotherCow\n"
                   + "Born: 04-08-2000\n"
                   + "Classification: n/a\n"
                   + "Milk Records \n"
                   + "02-10  228  17232  5.3   913\n"
                   + "03-02  178   3290  4.0   131\n"
                   + "04-03  260    266  3.2     8 \n" );
    }
}