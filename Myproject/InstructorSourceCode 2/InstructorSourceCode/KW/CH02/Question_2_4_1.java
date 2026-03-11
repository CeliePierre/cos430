package KW.CH02;
/*<exercise chapter="2" section="4" type="programming" number="1">*/

public class Question_2_4_1 {

    public static void main(String[] args) {
        int n;
        int y1;
        int y2;
        System.out.printf("%10s%10s%10s%n", "n", "y1", "y2");
        for (n = 0; n <= 100; n += 10) {
            System.out.printf("%10d%10d%10d%n", n, 100 * n + 10, 5 * n * n + 2);
        }
    }
}
/*</exercise>*/

