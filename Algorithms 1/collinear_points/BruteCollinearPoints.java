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

public class BruteCollinearPoints {
    private final ArrayList<LineSegment> lineSegs = new ArrayList<>();

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null)
            throw new IllegalArgumentException();

        for (int a = 0; a < points.length - 3; a++) {
            Point p = points[a];
            if (p == null)
                throw new IllegalArgumentException();
            for (int b = a + 1; b < points.length - 2; b++) {
                Point q = points[b];
                if (q == null || p.compareTo(q) == 0)
                    throw new IllegalArgumentException();
                for (int c = b + 1; c < points.length - 1; c++) {
                    Point r = points[c];
                    if (r == null || r.compareTo(p) == 0 || r.compareTo(q) == 0)
                        throw new IllegalArgumentException();
                    for (int d = c + 1; d < points.length; d++) {
                        Point s = points[d];
                        if (s == null || s.compareTo(p) == 0 || s.compareTo(q) == 0
                                || s.compareTo(r) == 0)
                            throw new IllegalArgumentException();

                        if (p.slopeTo(q) == q.slopeTo(r) &&
                                q.slopeTo(r) == r.slopeTo(s) &&
                                r.slopeTo(s) == s.slopeTo(p)) {
                            Point[] ps = { p, q, r, s };
                            Arrays.sort(ps, Point::compareTo);
                            lineSegs.add(new LineSegment(ps[0], ps[3]));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments()        // the number of line segments
    {
        return lineSegs.size();
    }

    public LineSegment[] segments()                // the line segments
    {
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
