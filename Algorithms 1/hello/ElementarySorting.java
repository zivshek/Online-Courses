/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;

public class ElementarySorting {

    public static void selectionSort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    public static void insertionSort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            while (j > 0 && less(a[j], a[j - 1])) {
                exch(a, j, j - 1);
                j--;
            }
        }
    }

    public static void shellSort(Comparable[] a) {
        int n = a.length;
        int gap = 1;
        while (gap < n / 3) {
            gap = gap * 3 + 1;
        }

        while (gap >= 1) {
            for (int i = gap; i < n; i++) {
                for (int j = i; j >= gap && less(a[j], a[j - gap]); j -= gap) {
                    exch(a, j, j - gap);
                }
            }
            gap /= 3;
        }
    }

    public static void shuffle(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = StdRandom.uniform(0, i + 1);
            exch(a, i, j);
        }
    }

    public static void convexHull(Point2D[] points) {
        
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[] { 5, 2, 4, 1, 3 };
        shellSort(a);
        shuffle(a);

        for (Integer x : a) {
            System.out.print(x);
        }
    }
}
