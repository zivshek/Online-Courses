/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegs = new ArrayList<>();

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null)
            throw new IllegalArgumentException();

        for (int a = 0; a < points.length - 3; a++) {
            for (int b = a + 1; b < points.length - 2; b++) {
                for (int c = b + 1; c < points.length - 1; c++) {
                    for (int d = c + 1; d < points.length; d++) {
                        Point p = points[a];
                        Point q = points[b];
                        Point r = points[c];
                        Point s = points[d];

                        if (p == null || q == null || r == null || s == null)
                            throw new IllegalArgumentException();
                        if (p == q || p == r || p == s || q == r || q == s || r == s)
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
        return (LineSegment[]) lineSegs.toArray();
    }

    public static void main(String[] args) {

    }
}
