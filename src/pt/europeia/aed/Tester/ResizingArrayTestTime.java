package pt.europeia.aed.Tester;

import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.files.Excel;

import java.util.ArrayList;

import static java.lang.System.out;

public class ResizingArrayTestTime {

    public static final double timeBudgetPerExperiment = 1.0 /* seconds */;
    public static final double minimumTimePerContiguousRepetitions = 1e-5 /* seconds */;


    public static double medianOf(final ArrayList<Double> values) {
        final int size = values.size();

        values.sort(null);

        if (size % 2 == 0)
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        else
            return values.get(size / 2);
    }

    // The method whose execution times are wanted:
    public static void experiment(Runnable runnable, final int limit) {
        runnable.run();
    }

    public static int contiguousRepetitionsFor(Runnable runnable, final int limit) {
        final Stopwatch stopwatch = new Stopwatch();
        int contiguousRepetitions = 0;
        do {
            experiment(runnable, limit);
            contiguousRepetitions++;
        } while (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions);

        return contiguousRepetitions;
    }

    public static double executionTimeFor(Runnable runnable, final int limit, final int contiguousRepetitions) {
        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i != contiguousRepetitions; i++)
            experiment(runnable, limit);
        return stopwatch.elapsedTime() / contiguousRepetitions;
    }

    public static void performExperimentsFor(final Runnable runnable, final boolean isWarmup, final int limit, Excel excel) {
        final ArrayList<Double> executionTimes = new ArrayList<Double>();
        final Stopwatch stopwatch = new Stopwatch();
        final int contiguousRepetitions = contiguousRepetitionsFor(runnable, limit);
        long repetitions = 0;
        do {
            executionTimes.add(executionTimeFor(runnable, limit, contiguousRepetitions));
            repetitions++;
        } while (stopwatch.elapsedTime() < timeBudgetPerExperiment);

        final double median = medianOf(executionTimes);

        if (!isWarmup)
            excel.writeDataTimes(limit, median, executionTimes.get(0), repetitions);
        System.out.println(
                limit + "\t" + median + "\t" + repetitions);

    }
        /*-
        out.println("Sum from 1 to " + limit + " = " + sum + " [" + median
                + "s median time based on " + repetitions
                + " repetitions of " + contiguousRepetitions
                + " contiguous repetitions]");
        */
}
/*
    public static void main(final String[] arguments)
            throws InterruptedException {

        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2)
            performExperimentsFor(limit, true);

        for (int exponent = 0, limit = 1; exponent != 31; exponent++, limit *= 2)
            performExperimentsFor(limit, false);
    }*/


























