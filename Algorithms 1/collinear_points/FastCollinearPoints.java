/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> lineSegs = new ArrayList<>();

    public FastCollinearPoints(
            Point[] points)     // finds all line segments containing 4 or more points
    {
        if (points == null)
            throw new IllegalArgumentException();

        for (int i = 0; i < points.length - 3; i++) {
            Point p = points[i];
            if (p == null)
                throw new IllegalArgumentException();

            Point[] rest = new Point[points.length - i - 1];
            Double[] slopes = new Double[points.length - i - 1];
            int current = 0;
            for (int j = i + 1; j < points.length; j++) {
                Point q = points[j];
                if (q == null || p.compareTo(q) == 0)
                    throw new IllegalArgumentException();
                slopes[current] = q.slopeTo(p);
                rest[current++] = q;
            }

            Arrays.sort(rest, (a, b) -> Double.compare(a.slopeTo(p), b.slopeTo(p)));
            Arrays.sort(slopes, Double::compareTo);

            double s = rest[0].slopeTo(p);
            ArrayList<Point> seg = new ArrayList<>();
            seg.add(rest[0]);
            for (int j = 1; j < rest.length; j++) {
                double sx = rest[j].slopeTo(p);
                if (sx != s) {
                    if (seg.size() >= 3) {
                        seg.add(p);
                        seg.sort(Point::compareTo);
                        lineSegs.add(new LineSegment(seg.get(0), seg.get(seg.size() - 1)));
                    }
                    s = sx;
                    seg.clear();
                }
                seg.add(rest[j]);
            }

            if (seg.size() >= 3) {
                seg.add(p);
                seg.sort(Point::compareTo);
                lineSegs.add(new LineSegment(seg.get(0), seg.get(seg.size() - 1)));
            }
        }
    }

    public int numberOfSegments()        // the number of line segments
    {
        return lineSegs.size();
    }

    public LineSegment[] segments() {
        LineSegment[] ret = new LineSegment[lineSegs.size()];
        return lineSegs.toArray(ret);
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
