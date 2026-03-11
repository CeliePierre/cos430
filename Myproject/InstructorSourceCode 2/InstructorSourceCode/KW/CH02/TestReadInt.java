/*<exercise chapter="2" section="11" type="programming" number="1">*/
package KW.CH02;

import java.util.Scanner;

/** Program to test readInt method */
public class TestReadInt {

    private static class TestCase {

        String prompt;
        int minN;
        int maxN;
        int value;
        Class<? extends Throwable> exception;

        public TestCase(String prompt, int minN, int maxN, int value,
                Class<? extends Throwable> exception) {
            this.prompt = prompt;
            this.minN = minN;
            this.maxN = maxN;
            this.value = value;
            this.exception = exception;
        }
    }
    private static TestCase[] testCases = {
        new TestCase(null, 1, 5, 2, null),
        new TestCase("", 1, 5, 6, null),
        new TestCase("MyPrompt", 0, 0, 0, null),
        new TestCase("MyPrompt", 5, 1, 0, IllegalArgumentException.class),
        new TestCase("MyPrompt", -10, 5, 0, null)
    };
    private static Scanner in = new Scanner(System.in);

    /**
     * Method to conduct a test
     * @param prompt The prompt to be displayed
     * @param minN The minimum value allowed
     * @param maxN The maximum value allowed
     * @param value The value to be entered and the expected result
     * @param exception The exception that is expected to be thrown
     * @return true If the test passes
     */
    private static boolean doTest(String prompt, int minN, int maxN,
            int value, Class<? extends Throwable> exception) {
        boolean pass = true;
        if (exception == null) {
            try {
                if (minN <= value && value <= maxN) {
                    System.out.println("The prompt should be " + prompt
                            + "\n Enter the value " + value
                            + "\n Normal return is expected");
                    int result = MyInput.readInt(prompt, minN, maxN);
                    pass = pass && (result == value);
                    System.out.print("Was the prompt properly displayed (y/N)?");
                    String answer = in.next();
                    pass = pass && Character.toUpperCase(answer.charAt(0)) == 'Y';
                    System.out.print("Was the input accepted? (y/N?)");
                    answer = in.next();
                    pass = pass && Character.toUpperCase(answer.charAt(0)) == 'Y';
                    return pass;
                } else {
                    System.out.println("The prompt should be " + prompt
                            + "\n Enter the value " + value
                            + "\n prompt should be re-displayed"
                            + "\n Enter " + minN + " to the second prompt");
                    int result = MyInput.readInt(prompt, minN, maxN);
                    pass = pass && result == minN;
                    System.out.print("Was the prompt properly displayed (y/N)?");
                    String answer = in.next();
                    pass = pass && Character.toUpperCase(answer.charAt(0)) == 'Y';
                    System.out.print("Was the prompt re-displayed (y/N)?");
                    answer = in.next();
                    pass = pass && Character.toUpperCase(answer.charAt(0)) == 'Y';
                    return pass;
                }
            } catch (Throwable ex) {
                System.err.println("Unexpected exception thrown");
                ex.printStackTrace();
                return false;
            }
        } else {
            try {
                int result = MyInput.readInt(prompt, minN, maxN);
                // Should not get here
                return false;
            } catch (Throwable ex) {
                if (ex.getClass() == exception) {
                    return true;
                } else {
                    System.err.println("Unexpected exception thrown");
                    ex.printStackTrace();
                    return false;
                }
            }
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < testCases.length; i++) {
            if (doTest(testCases[i].prompt,
                    testCases[i].minN,
                    testCases[i].maxN,
                    testCases[i].value,
                    testCases[i].exception)) {
                System.out.println("Test " + i + " passed");
            } else {
                System.out.println("Test " + i + " failed");
            }
        }
        System.exit(0);
    }
}
/*</exercise>*/
