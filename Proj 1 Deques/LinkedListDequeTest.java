import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Performs some basic linked list tests.
 */
public class LinkedListDequeTest {

    /**
     * Adds a few things to the deque, checking isEmpty() and size() are correct,
     * finally printing the results.
     */
    @Test
    public void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines below (and delete this line).");

        LinkedListDeque<String> lld1 = new LinkedListDeque<>();

        // Java will try to run the below code.
        // If there is a failure, it will jump to the finally block before erroring.
        // If all is successful, the finally block will also run afterwards.
        try {

            assertTrue(lld1.isEmpty());

            lld1.addFirst("front");
            assertEquals(1, lld1.size());
            assertFalse(lld1.isEmpty());

            lld1.addLast("middle");
            assertEquals(2, lld1.size());

            lld1.addLast("back");
            assertEquals(3, lld1.size());

        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }

    }

    /**
     * Adds an item, then removes an item, and ensures that deque is empty afterwards.
     */
    @Test
    public void addRemoveTest() {
        System.out.println("Running add/remove test.");
        System.out.println("Make sure to uncomment the lines below (and delete this line).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

        try {
            assertTrue(lld1.isEmpty());

            lld1.addFirst(10);
            assertFalse(lld1.isEmpty());

            lld1.removeFirst();
            assertTrue(lld1.isEmpty());
        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }

    }

    @Test
    public void testaddplusremove() {
        Deque test = new LinkedListDeque<>();
        for (int i = 0; i < 5; i++) {
            test.addLast(i);
            test.addFirst(i * 3);
        }
        test.removeFirst();
        test.removeFirst();
        test.addLast(3.1415);
        test.addLast("happy");
        test.addLast("not happy");
        test.removeLast();
        test.removeFirst();
        test.removeFirst();
        assertEquals(test.size(), 8);
        test.removeFirst();
        assertEquals(test.size(), 7);
        assertEquals(test.get(1), 1);
        assertEquals(test.get(5), 3.1415);
        test.printDeque();
        for (int i = 0; i < 7; i++) {
            test.removeLast();
        }
        assertNull(test.removeLast());
        assertTrue(test.isEmpty());
    }

    @Test
    public void testEmpty() {
        Deque test = new LinkedListDeque<>();
        assertTrue(test.isEmpty());
        assertEquals(0, test.size());
    }

    @Test
    public void testaddFirst() {
        Deque test = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            test.addFirst(i);
        }
        assertEquals(test.get(3), 6);
        assertEquals(test.get(5), 4);
        assertEquals(test.get(9), 0);
        assertEquals(test.get(0), 9);
        assertNull(test.get(10));
        assertNull(test.get(-1));
    }

    @Test
    public void testaddLast() {
        Deque test = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            test.addLast(i);
        }
        assertEquals(test.get(3), 3);
        assertEquals(test.get(5), 5);
        assertEquals(test.get(9), 9);
        assertEquals(test.get(0), 0);
        assertNull(test.get(10));
        assertNull(test.get(-1));
    }

    @Test
    public void getTest() {
        System.out.println("Running add/remove test.");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        LinkedListDeque<String> lld2 = new LinkedListDeque<>();

        try {
            assertTrue(lld1.isEmpty());

            lld1.addFirst(1);
            lld1.addLast(2);
            lld1.addLast(3);

            assertEquals(3, lld1.size());
            assertEquals(1, (int) lld1.get(0));
            assertEquals(2, (int) lld1.get(1));
            assertEquals(3, (int) lld1.get(2));
            assertEquals(1, (int) lld1.getRecursive(0));
            assertEquals(2, (int) lld1.getRecursive(1));
            assertEquals(3, (int) lld1.getRecursive(2));

            assertEquals(null, lld2.get(0));
            assertEquals(null, lld2.getRecursive(0));

            lld2.addFirst("a");
            assertEquals("a", lld2.get(0));
            assertEquals("a", lld2.getRecursive(0));
        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }
    }
}
