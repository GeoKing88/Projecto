package pt.europeia.aed.Comparisons;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import pt.europeia.aed.book.chapter2.section3.Quick;

import java.util.ArrayList;

public final class QuickSortComparation {


    private static int swaps;
    private static int comparisons;
    private static int readings;
    private static int writings;

    private QuickSortComparation() {
        throw new RuntimeException("Attempt to instantiate package-class");
    }

    public static <Item extends Comparable<? super Item>> ArrayList<Integer> sort(
            final Item[] values) {

        swaps = 0;
        comparisons = 0;
        readings = 0;
        writings = 0;

        StdRandom.shuffle(values);
        swaps += values.length; // The swap occurs one time for every value.
        writings += 2 * swaps;


        sort(values, 0, values.length - 1);

        ArrayList<Integer> comparisonsTest = new ArrayList<>();
        comparisonsTest.add(comparisons);
        comparisonsTest.add(swaps);
        comparisonsTest.add(readings);
        comparisonsTest.add(writings);
        comparisonsTest.add(writings + readings);

        assert isIncreasing(
                values) : "Array should be increasing after sorting.";
        return comparisonsTest;
    }


    private static <Item extends Comparable<? super Item>> void sort(
            final Item[] values, final int first, final int last) {
        if (last <= first)
            return;

        final int j = partition(values, first, last);
        sort(values, first, j - 1);
        sort(values, j + 1, last);

        assert isIncreasing(values, first,
                last) : "Array segment should be increasing after sorting.";
    }


    private static <Item extends Comparable<? super Item>> int partition(
            final Item[] values, final int first, final int last) {
        int i = first;
        int j = last + 1;

        readings += 1;
        final Item pivot = values[first];

        while (true) {

            do {
                comparisons += 1;
                readings += 1;
                i++;
            } while (isLess(values[i], pivot) && i != last);

            do {
                comparisons += 1;
                readings += 1;
                j--;
            } while (isLess(pivot, values[j]) && j != first);

            if (i >= j)
                break;
            swaps += 1;
            readings += 2;
            writings += 2;
            swap(values, i, j);
        }
        swaps += 1;
        readings += 2;
        writings += 2;
        swap(values, first, j);

        return j;
    }

    public static <Item extends Comparable<? super Item>> Item select(
            final Item[] values, final int k) {
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

    private static <Value extends Comparable<? super Value>> boolean isLess(
            final Value first, final Value second) {
        return first.compareTo(second) < 0;
    }

    private static void swap(final Object[] values, final int i, final int j) {
        final Object temporary = values[i];
        values[i] = values[j];
        values[j] = temporary;
    }

    private static <Item extends Comparable<? super Item>> boolean isIncreasing(
            final Item[] values) {
        return isIncreasing(values, 0, values.length - 1);
    }

    private static <Item extends Comparable<? super Item>> boolean isIncreasing(
            final Item[] values, final int first, final int last) {
        for (int i = first + 1; i <= last; i++)
            if (isLess(values[i], values[i - 1]))
                return false;
        return true;
    }

    private static void show(final Object[] values) {
        for (Object value : values)
            StdOut.println(value);
    }

    public static void main(final String[] arguments) {
        final In in = new In(arguments[0]);
        final String[] words = in.readAllStrings();
        Quick.sort(words);
        show(words);

        StdRandom.shuffle(words);

        StdOut.println();
        for (int i = 0; i != words.length; i++) {
            final String ith = Quick.select(words, i);
            StdOut.println(ith);
        }
    }
}
