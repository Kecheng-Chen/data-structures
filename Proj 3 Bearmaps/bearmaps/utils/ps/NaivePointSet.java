package bearmaps.utils.ps;
import java.util.*;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    /**
     * Given x and y of target point
     * Return the nearest point in points to that target point
     * */
    public Point nearest(double x, double y) {
        Point targetPoint = new Point(x, y);
        Point nearestPoint = points.get(0);
        double shortestDist = Point.distance(targetPoint, nearestPoint);
        for (int i = 1; i < points.size(); i += 1) {
            double currDist = Point.distance(targetPoint, points.get(i));
            if (currDist < shortestDist) {
                shortestDist = currDist;
                nearestPoint = points.get(i);
            }
        }
        return nearestPoint;
    }
}
