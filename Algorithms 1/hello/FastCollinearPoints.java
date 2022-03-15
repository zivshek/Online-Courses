/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> lineSegs = new ArrayList<>();

    public FastCollinearPoints(
            Point[] points)     // finds all line segments containing 4 or more points
    {
        if (points == null)
            throw new IllegalArgumentException();

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            if (p == null)
                throw new IllegalArgumentException();

            for (int j = i + 1; j < points.length; j++) {
                Point q = points[j];
                if (q == null || p == q)
                    throw new IllegalArgumentException();
            }

            Arrays.sort(points, (a, b) -> Double.compare(a.slopeTo(p), b.slopeTo(p)));

            double s = points[0].slopeTo(p);
            int count = 1;
            Point[] seg = new Point[4];
            seg[0] = points[0];
            for (int j = 1; j < points.length; j++) {
                double sx = points[j].slopeTo(p);
                if (sx == s) {
                    seg[count++] = points[j];
                }
                else {
                    if (count >= 4) {
                        Arrays.sort(seg, Point::compareTo);
                        lineSegs.add(new LineSegment(seg[0], seg[3]));
                    }
                    s = sx;
                    count = 0;
                }
            }
        }
    }

    public int numberOfSegments()        // the number of line segments
    {
        return lineSegs.size();
    }

    public LineSegment[] segments() {
        return (LineSegment[]) lineSegs.toArray();
    }

    public static void main(String[] args) {

    }
}
