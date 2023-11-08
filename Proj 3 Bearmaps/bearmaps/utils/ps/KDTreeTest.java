package bearmaps.utils.ps;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import bearmaps.utils.ps.*;

public class KDTreeTest {
    @Test
    public void testKDTree() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.5, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(3.0, 4.5);

        KDTree nn = new KDTree(List.of(p1, p2, p3,p4));
        Point ret = nn.nearest(3.0, 4.0); // returns p2

        assertEquals(ret.getX(), 3.0, 0.0001);
        assertEquals(ret.getY(), 4.5, 0.0001);
    }

}