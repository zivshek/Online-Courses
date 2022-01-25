/* *****************************************************************************
 *  Name:              Hao Shi
 *  Coursera User ID:  ziv.hao.shek@gmail.com
 *  Last modified:     Jan 24, 2022
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class DequeIterator implements Iterator<Item> {
        private Node current = null;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        Item item;
        Node prev = null;
        Node next = null;
    }

    private Node head = null;
    private Node tail = null;
    private int count = 0;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            tail = node;
        }
        else {
            node.next = head;
            head.prev = head;
        }
        head = node;
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            head = node;
        }
        else {
            tail.next = node;
            tail.prev = tail;
            node.prev = tail;
        }
        tail = node;
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Node oldHead = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        count--;
        if (count == 0) {
            tail = null;
        }
        return oldHead.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Node oldTail = tail;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        count--;
        if (count == 0) {
            head = null;
        }
        return oldTail.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        DequeIterator it = new DequeIterator();
        it.current = head;
        return it;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        System.out.println("The deque is empty: " + deque.isEmpty());
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(0);
        deque.addLast(3);
        deque.print(); // Should print 0, 1, 2, 3,
        System.out.println("Size: " + deque.size()); // Should print 4
        deque.removeFirst();
        deque.print(); // Should print 1, 2, 3
        deque.removeFirst();
        deque.print(); // Should print 2, 3
        System.out.println("Removed: " + deque.removeLast());
        deque.print(); // Should print 2
        deque.removeLast();
        System.out.println("Size: " + deque.size()); // Should print 0
        deque.print(); // Should print an empty line
        // deque.removeLast(); // Exception
    }

    private void print() {
        for (Item item : this) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }
}
