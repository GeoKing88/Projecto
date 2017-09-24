package pt.europeia.aed.SortingTestTime;

import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.files.Excel;

import java.util.ArrayList;

import static java.lang.System.out;


public class ResizingArrayTestTime {


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


    public static void runnable(final Runnable runnable) {

        runnable.run();
    }


    public static int contiguousRepetitionsFor(final Runnable runnable) {
        final Stopwatch stopwatch = new Stopwatch();
        int contiguousRepetitions = 0;
        do {
            runnable(runnable);
            contiguousRepetitions++;
        } while (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions);


        return contiguousRepetitions;
    }


        public static double executionTimeFor(final Runnable runnable,
                                          final int contiguousRepetitions) {
        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i != contiguousRepetitions; i++)
            runnable(runnable);
        return stopwatch.elapsedTime() / contiguousRepetitions;
    }


    public static void performExperimentsFor(Runnable runnable, boolean isWarmup, final int limit, Excel excel) {
        final ArrayList<Double> executionTimes = new ArrayList<Double>();
        final Stopwatch stopwatch = new Stopwatch();
        final int contiguousRepetitions = contiguousRepetitionsFor(runnable);
        long repetitions = 0;
        do {
            executionTimes.add(executionTimeFor(runnable, contiguousRepetitions));
            repetitions++;
        } while (stopwatch.elapsedTime() < timeBudgetPerExperiment);

        final double median = medianOf(executionTimes);

        if (!isWarmup)
            excel.writeDataTimes(limit, median, executionTimes.get(0), repetitions);
        out.println(
                limit + "\t" + median + "\t" + repetitions);
    }
}
