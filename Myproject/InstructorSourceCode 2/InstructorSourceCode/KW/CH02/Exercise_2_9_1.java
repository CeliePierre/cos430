package KW.CH02;
/*<exercise chapter="2" section="9" type="programming" number="1">*/
// Verification that KWLinkedList and KWArrayList implement the
// Collection interface. This assumes that all other exercises have been 
// implemented

import java.util.Collection;

public class Exercise_2_9_1 {

    public static void main(String[] args) {
        Collection<String> textCollection1 = new KWArrayList<String>();
        Collection<String> testCollection2 = new KWLinkedList<String>();
    }
}
/*</exercise>*/
