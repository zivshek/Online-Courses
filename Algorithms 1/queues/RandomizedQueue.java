/* *****************************************************************************
 *  Name:              Hao Shi
 *  Coursera User ID:  ziv.hao.shek@gmail.com
 *  Last modified:     Jan 24, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class RandomizedQIterator implements Iterator<Item> {
        private int current = 0;

        public boolean hasNext() {
            return current < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Item[] s;
    private int n = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[5]; // start out with a capacity of 5
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isFull()) resize(s.length * 2);
        s[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (shouldDownsize()) resize(s.length / 2);
        int random = StdRandom.uniform(0, n);
        Item result = s[random];
        s[random] = s[n - 1];
        n--;
        return result;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = StdRandom.uniform(0, n);
        return s[random];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQIterator();
    }

    private boolean isFull() {
        return n == s.length;
    }

    private boolean shouldDownsize() {
        return n == s.length / 4;
    }

    private void resize(int newSize) {
        Item[] newS = (Item[]) new Object[newSize];
        int smaller = newSize > s.length ? s.length : newSize;
        for (int i = 0; i < smaller; i++) {
            newS[i] = s[i];
        }
        s = newS;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        System.out.println("The queue is empty: " + queue.isEmpty());
        try {
            System.out.println(queue.dequeue());
        }
        catch (NoSuchElementException e) {
            System.out.println("Dequeue on an empty queue is not allowed");
        }
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.print(); // Should print 0, 1, 2, 3, 4, 5,
        System.out.println("Sample: " + queue.sample());
        queue.print(); // Should print 0, 1, 2, 3, 4, 5,
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Size should be 5: " + queue.size());
        queue.print();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println("Empty: " + queue.isEmpty());
    }

    private void print() {
        for (Item item : this) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }
}
