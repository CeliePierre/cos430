/*<exercise chapter="2" section="11" type="programming" number="3">*/
package KW.CH02;

/**
 * Class to encapsulate searchArray method and its test
 */
public class SearchArray {

    /**
     * Method to search part of an array for a target value
     * @param array The array to search
     * @param target The target value
     * @param minIndex The index to start the search
     * @param maxIndex The index to end the search
     * @return The index of the target or -1 if not found
     * @throws ArrayIndexOutOfBoundsException if minIndex is &lt; 0
     * or if the target is not found and maxIndex &gt; array.length-1
     * @throws IllegalArgumentException if maxIndex &lt; minIndex
     * @throws NullPointerException if array or target are null
     */
    public static <T> int search(T[] array, T target, int minIndex, int maxIndex) {
        if (maxIndex < minIndex) {
            throw new IllegalArgumentException();
        }
        for (int i = minIndex; i <= maxIndex; i++) {
            if (target.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    private static class TestCase {

        private Integer[] array;
        private Integer target;
        private int minIndex;
        private int maxIndex;
        private int result;
        private Class<? extends Throwable> exception;

        public TestCase(Integer[] array,
                Integer target,
                int minIndex,
                int maxIndex,
                int result,
                Class<? extends Throwable> exception) {
            this.array = array;
            this.target = target;
            this.minIndex = minIndex;
            this.maxIndex = maxIndex;
            this.result = result;
            this.exception = exception;
        }
    }

    private static boolean doTest(TestCase t) {
        boolean pass = true;
        try {
            int result = search(t.array, t.target, t.minIndex, t.maxIndex);
            pass = pass && (result == t.result) && (t.exception == null);
        } catch (Throwable ex) {
            if (ex.getClass() == t.exception) {
                return true;
            } else {
                System.err.println("Unexpected Exception");
                ex.printStackTrace();
                return false;
            }
        }
        return pass;
    }
    private static Integer[] array1 = {10, 5, -2, 20, 30, -9};
    private static Integer[] array2 = {99};
    private static Integer[] array3 = new Integer[0];
    private static Integer[] array4 = null;
    private static TestCase[] tests = {
        new TestCase(array1, -2, 0, 5, 2, null),
        new TestCase(array1, -2, 1, 4, 2, null),
        new TestCase(array1, -1, 0, 5, -1, null),
        new TestCase(array1, -1, 1, 4, -1, null),
        new TestCase(array1, -2, 2, 2, 2, null),
        new TestCase(array1, -1, 2, 2, -1, null),
        new TestCase(array1, -1, 2, -1, -1, IllegalArgumentException.class),
        new TestCase(array1, -1, -1, 5, -1, ArrayIndexOutOfBoundsException.class),
        new TestCase(array1, -2, 0, 10, 2, null),
        new TestCase(array1, -1, 0, 10, -1, ArrayIndexOutOfBoundsException.class),
        new TestCase(array2, 99, 0, 0, 0, null),
        new TestCase(array2, -1, 0, 0, -1, null),
        new TestCase(array3, -1, 0, 0, -1, ArrayIndexOutOfBoundsException.class),
        new TestCase(array4, -1, 0, 0, -1, NullPointerException.class),
        new TestCase(array1, null, 0, 0, -1, NullPointerException.class)
    };

    public static void main(String[] args) {
        boolean allPass = true;
        for (int i = 0; i < tests.length; i++) {
            boolean pass = doTest(tests[i]);
            if (pass) {
                System.out.println("Test " + i + " passed");
            } else {
                System.out.println("Test " + i + "       FAILED");
            }
            allPass = allPass && pass;
        }
        if (allPass) {
            System.out.println("All tests passed");
        }
    }
}
/*</exercise>*/
