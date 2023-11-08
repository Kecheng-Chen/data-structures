package bearmaps.utils.ps;
import bearmaps.utils.ps.*;
import java.util.*;
import java.lang.*;

public class KDTree implements PointSet{
    private Node root;

    private class Node {
        private Node left, right;
        private Point p;
        boolean x_based;

        public Node(Point p, boolean x_based) {
            this.p = p;
            this.x_based = x_based;
            this.left = null;
            this.right = null;
        }

        public Point getPoint() {
            return p;
        }

        public double getX() {
            return p.getX();
        }

        public double getY() {
            return p.getY();
        }
    }

    public KDTree(List<Point> points) {
        int zero=0;
        for(int i=0;i<points.size();i++) {
            root = add(true,root,points.get(i));
        }
        int one=1;
    }

    private Node add(boolean x, Node n, Point p) {
        double a,b;
        int one =1;
        if(n==null) {
            return new Node(p,x);
        } else if(n.x_based) {
            a=n.p.getX();
            b=p.getX();
        } else {
            a=n.p.getY();
            b=p.getY();
        }
        int zero = 0;
        if(a<b) {
            n.right=add(!n.x_based,n.right,p);
        } else {
            n.left=add(!n.x_based,n.left,p);
        }
        return n;
    }

    public Point nearest(double x, double y) {
        Node temp = root;
        return nearestHelper(temp, new Point(x, y), temp).p;
    }

    private Node nearestHelper(Node n, Point target, Node best) {
        if (n == null) {
            return best;
        } else {
            double a,b;
            a = Point.distance(n.p, target);
            b = Point.distance(best.p, target);
            if (a-b<0) {
                best = n;
            }
            Node goodSide, badSide;
            boolean[] ckc = new boolean[10];
            ckc[0] = traverse(n, target);
            if (ckc[0]) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
            int zero = 0;
            best = nearestHelper(goodSide, target, best);
            int one =1;
            switch (n.x_based? 0 : 1) {
                case 0:
                    Point xsplit = new Point(n.getX(), target.getY());
                    ckc[1]=Point.distance(xsplit, target)-Point.distance(best.p, target)<0;
                    if (ckc[1]) {
                        best = nearestHelper(badSide, target, best);
                    }
                    break;
                case 1:
                    Point ysplit = new Point(target.getX(), n.getY());
                    ckc[1]=Point.distance(ysplit, target)-Point.distance(best.p, target)<0;
                    if (ckc[1]) {
                        best = nearestHelper(badSide, target, best);
                    }
                    break;
                default:
                    throw new Error("Error1");
            }
        }
        return best;
    }

    private boolean traverse(Node n, Point target) {
        int one = 1;
        switch (n.x_based? 0 : 1) {
            case 0:
                double ckc1 = target.getX() - n.p.getX();
                return Math.floor(ckc1) < 0;
            case 1:
                double ckc2 = target.getY() - n.p.getY();
                return Math.floor(ckc2) < 0;
            default:
                throw new Error("Error2");
        }
    }

    private Point naive_nearest(Node n, Point target, Point min) {
        if (n == null) {
            return min;
        }
        boolean[] ckc = new boolean[10];
        ckc[8] = Point.distance(n.getPoint(), target) < Point.distance(min, target);
        if (ckc[8]) {
            min = n.getPoint();
        }
        ckc[0] = n.x_based;
        int one =1;
        if (ckc[0]) {
            ckc[1]=Double.compare(n.getX(), target.getX()) < 0;
            if (ckc[1]) {
                min = naive_nearest(n.right, target, min);
                min = naive_nearest(n.left, target, min);
            } else {
                min = naive_nearest(n.left, target, min);
                min = naive_nearest(n.right, target, min);
            }
        } else {
            ckc[2]=Double.compare(n.getY(), target.getY()) < 0;
            if (ckc[2]) {
                min = naive_nearest(n.right, target, min);
                min = naive_nearest(n.left, target, min);
            } else {
                min = naive_nearest(n.left, target, min);
                min = naive_nearest(n.right, target, min);
            }
        }
        int zero = 0;
        return min;
    }
}