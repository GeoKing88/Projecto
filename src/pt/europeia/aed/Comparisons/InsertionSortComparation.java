package pt.europeia.aed.Comparisons;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class InsertionSortComparation {

    private static int swaps;
    private static int comparisons;
    private static int readings;
    private static int writings;

    private InsertionSortComparation() {
        throw new RuntimeException("Attempt to instantiate package-class");
    }


    public static <Item extends Comparable<? super Item>> ArrayList<Integer> sort(
            final Item[] values) {

        swaps = 0;
        comparisons = 0;
        readings = 0;
        writings = 0;


        for (int numberOfSortedItems = 1; numberOfSortedItems < values.length; numberOfSortedItems++) {
            for (int i = numberOfSortedItems; i != 0 && isLess(values[i], values[i - 1]); i--) {

                swap(values, i - 1, i);

                assert isIncreasing(values) :
                        "Array should be increasing after sorting.";
            }
        }

        ArrayList<Integer> comparisonsTest = new ArrayList<>();
        comparisonsTest.add(comparisons);
        comparisonsTest.add(swaps);
        comparisonsTest.add(readings);
        comparisonsTest.add(writings);
        comparisonsTest.add(writings + readings);
        return comparisonsTest;
    }

    private static <Value extends Comparable<? super Value>> boolean isLess(
            final Value first, final Value second) {
        comparisons += 1;
        readings += 1;
        return first.compareTo(second) < 0;
    }

    private static void swap(final Object[] values, final int firstPosition,
                             final int secondPosition) {
        final Object temporary = values[firstPosition];
        values[firstPosition] = values[secondPosition];
        values[secondPosition] = temporary;
        swaps += 1;
        readings += 2;
        writings += 2;
    }

    private static <Item extends Comparable<? super Item>> boolean isIncreasing(
            final Item[] values) {
        for (int i = 1; i < values.length; i++)
            if (isLess(values[i], values[i - 1]))
                return false;
        return true;
    }


}


