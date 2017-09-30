package pt.europeia.aed.book.chapter2.section3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public final class QuickSort2 {

    private QuickSort2() {
        throw new RuntimeException("Attempt to instantiate package-class");
    }

    public static void sort(
            final double[] values) {
        StdRandom.shuffle(values);

        sort(values, 0, values.length - 1);

        assert isIncreasing(
                values) : "Array should be increasing after sorting.";
    }

    private static void sort(
            final double[] values, final int first, final int last) {
        if (last <= first)
            return;

        final int j = partition(values, first, last);
        sort(values, first, j - 1);
        sort(values, j + 1, last);

        assert isIncreasing(values, first,
                last) : "Array segment should be increasing after sorting.";
    }

    private static int partition(
            final double[] values, final int first, final int last) {
        int i = first;
        int j = last + 1;

        final double pivot = values[first];

        while (true) {

            do
                i++;
            while (isLess(values[i], pivot) && i != last);

            do
                j--;
            while (isLess(pivot, values[j]) && j != first);

            if (i >= j)
                break;

            swap(values, i, j);
        }

        swap(values, first, j);

        return j;
    }

    public static double select(
            final double[] values, final int k) {
        if (k < 0 || values.length <= k)
            throw new IndexOutOfBoundsException(
                    "Selected element out of bounds");

        StdRandom.shuffle(values);

        int first = 0;
        int last = values.length - 1;

        while (first < last) {
            final int j = partition(values, first, last);
            if (j > k)
                last = j - 1;
            else if (j < k)
                first = j + 1;
            else
                return values[j];
        }

        return values[first];
    }

    private static boolean isLess(final double first, final double second) {
        return first < second;
    }

    private static void swap(final double[] values, final int i, final int j) {
        final double temporary = values[i];
        values[i] = values[j];
        values[j] = temporary;
    }

    private static boolean isIncreasing(
            final double[] values) {
        return isIncreasing(values, 0, values.length - 1);
    }

    private static boolean isIncreasing(
            final double[] values, final int first, final int last) {
        for (int i = first + 1; i <= last; i++)
            if (isLess(values[i], values[i - 1]))
                return false;
        return true;
    }

    private static void show(final double[] values) {
        for (int i = 0; i < values.length; i++)
            StdOut.println(values[i]);
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