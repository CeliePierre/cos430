package KW.CH04;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Random;

/** Program to test a Queue class */
public class TestQueue {

    public static void main(String[] args) {

        Queue<Integer> testQueue = new ListQueue<Integer>();
// 	Queue<Integer> testQueue = new ListQueueReverse<Integer>();
// 	Queue<Integer> testQueue = new ArrayQueue<Integer>();
// 	Queue<Integer> testQueue = new ArrayQueue2<Integer>();
        Queue<Integer> knownQueue = new ArrayDeque<Integer>();
        Random rand = new Random();

        // Fill both queues with 10 random integers
        for (int i = 0; i < 10; i++) {
            Integer next = new Integer(rand.nextInt());
            testQueue.offer(next);
            knownQueue.offer(next);
        }
        // See if they both are the same
        verifyQueues(testQueue, knownQueue);

        // Remove 5 items
        for (int i = 0; i < 5; i++) {
            testQueue.poll();
            knownQueue.poll();
        }

        // See if they are both the same
        verifyQueues(testQueue, knownQueue);

        // Add 10 more items (forces reallocate of ArrayQueue)
        for (int i = 0; i < 10; i++) {
            Integer next = new Integer(rand.nextInt());
            testQueue.offer(next);
            knownQueue.offer(next);
        }

        // See if they are both the same
        verifyQueues(testQueue, knownQueue);

        System.out.println("Test finished");
    }

    private static void verifyQueues(Queue<Integer> q1, Queue<Integer> q2) {
        if (q1.size() != q2.size()) {
            System.out.printf("Queues different sizes %d %d%n",
                    q1.size(), q2.size());
        }
        for (int i = 0; i < q1.size(); i++) {
            Integer v1 = q1.poll();
            Integer v2 = q2.poll();
            if (!v1.equals(v2)) {
                System.out.printf("At index %d q1 is %d and q2 is %d%n",
                        i, v1, v2);
            }
            q1.offer(v1);
            q2.offer(v2);
        }
    }
}
