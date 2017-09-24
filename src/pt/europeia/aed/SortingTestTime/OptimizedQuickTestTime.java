package pt.europeia.aed.SortingTestTime;

import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.book.chapter2.section3.OptimizedQuick;
import pt.europeia.aed.files.Excel;

import java.util.ArrayList;

import static java.lang.System.out;

public class OptimizedQuickTestTime {

    public static final double timeBudgetPerExperiment = 10 /* seconds */;

    public static final double minimumTimePerContiguousRepetitions = 1e-5 /* seconds */;

    public static double medianOf(final ArrayList<Double> values) {
        final int size = values.size();

        values.sort(null);

        if (size % 2 == 0)
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        else
            return values.get(size / 2);
    }

    public static int contiguousRepetitionsFor(final Double[][] array) {

        int repetitions = 0;
        Stopwatch stopwatch = new Stopwatch();
        do {
            if (array.length <= repetitions) {
                repetitions = 0;
                break;
            }
            OptimizedQuick.sort(array[repetitions]);
            repetitions++;
        } while (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions);
        return repetitions;
    }


    public static double executionTimeFor(final Double[][] array, final int contiguousRepetitions) {
        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i != contiguousRepetitions; i++)
            OptimizedQuick.sort(array[i]);
        return stopwatch.elapsedTime() / contiguousRepetitions;
    }


    public static void performExperimentsFor(final Double[] array, boolean isWarmup, final int limit, final Excel excel) {
        int size = 8;
        int contiguousRepetitions = 0;
        do {
            Double[][] cloneArrays = cloneArrayToContiguousRepetitions(array, size * 2);
            contiguousRepetitions = contiguousRepetitionsFor(cloneArrays);
        } while (contiguousRepetitions == 0);

        Double[][] cloneArray = cloneArrayToContiguousRepetitions(array, contiguousRepetitions);

        final ArrayList<Double> executionTimes = new ArrayList<Double>();
        final Stopwatch stopwatch = new Stopwatch();

        long repetitions = 0;
        do {
            executionTimes.add(executionTimeFor(cloneArray, contiguousRepetitions));
            repetitions++;
        } while (stopwatch.elapsedTime() < timeBudgetPerExperiment);

        final double median = medianOf(executionTimes);

        if (!isWarmup)
            excel.writeDataTimes(limit, median, executionTimes.get(0), repetitions);
        out.println(
                limit + "\t" + median + "\t" + repetitions);
    }

    private static Double[][] cloneArrayToContiguousRepetitions(Double[] array, int size) {
        Double[][] clone = new Double[size][];
        for (int i = 0; i < size; i++) {
            clone[i] = array.clone();
        }
        return clone;
    }

}
