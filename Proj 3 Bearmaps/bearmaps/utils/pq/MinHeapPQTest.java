package bearmaps.utils.pq;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class MinHeapPQTest {

    @Test
    public void test1() {
        MinHeapPQ<String> h = new MinHeapPQ<>();
        h.insert("ca", 3);
    }

    @Test
    public void testAdd() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("Test1", 3);
        pq.insert("Test2", 1);
        pq.insert("Test3", 2);
        pq.insert("Test4", 0);
        assertEquals(4, pq.size());
    }

    @Test
    public void testGetSmallest() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("Test1", 3);
        pq.insert("Test2", 1);
        pq.insert("Test3", 2);
        pq.insert("Test4", 0);
        assertEquals("Test4", pq.peek());
    }

    @Test
    public void testRemoveSmallest() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("Test1", 3);
        pq.insert("Test2", 1);
        pq.insert("Test3", 2);
        pq.insert("Test4", 0);
        assertEquals("Test4", pq.poll());
        assertEquals(3, pq.size());
    }

    @Test
    public void testChangePriority() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("Test1", 3);
        pq.insert("Test2", 1);
        pq.insert("Test3", 2);
        pq.insert("Test4", 2);
        pq.changePriority("Test3", 0);
        pq.changePriority("Test4", 5);
        assertEquals("Test3", pq.peek());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoSuchElement() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("Test1", 3);
        pq.insert("Test2", 1);
        pq.insert("Test3", 2);
        pq.changePriority("Test4", 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgument() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("Test1", 3);
        pq.insert("Test1", 2);
    }
}
