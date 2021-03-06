package pt.europeia.aed.book.chapter1.section3.iterable;

import static pt.europeia.aed.Tools.in;
import static pt.europeia.aed.Tools.out;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Item item;
        private Node next;
    }

    public LinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }
    
    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }
    
    public void enqueue(final Item item) {
        final Node oldLast = last;

        last = new Node();

        last.item = item;
        last.next = null;

        if (first == null)
            first = last;
        else
            oldLast.next = last;

        size++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow.");

        final Item item = first.item;

        first = first.next;

        if (first == null)
            last = null;

        size--;
        
        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node nextNode = first;

        public boolean hasNext() {
            return nextNode != null;
        }

        // Not necessary in recent Java versions:
        /*-
        public void remove() {
            throw new UnsupportedOperationException();
        }
        */

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("No more items in iteration.");

            final Item item = nextNode.item;

            nextNode = nextNode.next;

            return item;
        }

    }

    public static void main(final String[] arguments) {
        final LinkedQueue<String> words = new LinkedQueue<String>();

        while (in.hasNext()) {
            final String word = in.next();
            if (word.equals("-"))
                out.print(words.dequeue() + " ");
            else
                words.enqueue(word);
        }
        out.println();

        out.println("Remaining words:");
        for (String word : words)
            out.println(word);
    }

}

/*
 * Copyright 2015 and 2016, Robert Sedgewick and Kevin Wayne.
 * 
 * Copyright 2015 and 2016, Manuel Menezes de Sequeira.
 *
 * This file is a derivative work of the code which accompanies the textbook
 *
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 * Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 *
 * This code is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this code. If not, see http://www.gnu.org/licenses.
 *
 * Any errors found in this code should be assumed to be the responsibility of
 * the author of the modifications to the original code (viz. Manuel Menezes de
 * Sequeira).
 */
