import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testEmpty() {
        Deque test = new ArrayDeque<>();
        assertTrue(test.isEmpty());
        assertEquals(0, test.size());
    }

    @Test
    public void testaddFirst() {
        Deque test = new ArrayDeque<>();
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
        Deque test = new ArrayDeque<>();
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
    public void testaddplusremove() {
        Deque test = new ArrayDeque<>();
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
    public void testfillup() {
        Deque test = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            test.addLast(i);
            test.addFirst(i);
        }
    }

    @Test
    public void testresizenplus() {
        Deque test = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            test.addLast(i);
            test.addFirst(i);
        }
        test.addLast("happy");
    }

    @Test
    public void testlarge() {
        Deque test = new ArrayDeque<>();
        for (int i = 0; i < 500; i++) {
            test.addLast(i);
            test.addFirst(i);
        }
        for (int i = 0; i < 999; i++) {
            test.removeLast();
        }
    }

    @Test
    public void testfuzz() {
        Deque test1 = new ArrayDeque<>();
        Deque test2 = new LinkedListDeque<>();
        Random generator = new Random(10);
        double random;
        for (int i = 0; i < 1000; i++) {
            random = generator.nextDouble();
            test1.addLast(random);
            test1.addFirst(random * random);
            test2.addLast(random);
            test2.addFirst(random * random);
        }
        assertEquals(test1.size(), test2.size());
        for (int i = 0; i < 950; i++) {
            test1.removeLast();
            test1.removeFirst();
            test2.removeLast();
            test2.removeFirst();
        }
        assertEquals(test1.size(), test2.size());
        for (int i = 0; i < test1.size(); i++) {
            assertEquals(test1.get(i), test2.get(i));
        }
    }
}
