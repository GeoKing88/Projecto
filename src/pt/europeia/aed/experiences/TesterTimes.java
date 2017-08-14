package pt.europeia.aed.experiences;

import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.files.Excel;

import java.util.ArrayList;

import static java.lang.System.out;


public class TesterTimes {
    // A time budget is established per experiment. Each experiment is repeated
    // as many times as necessary to expend this budget. That is, each
    // experiment is repeated until the total time spent repeating it exceeds
    // the budget.
    public static final double timeBudgetPerExperiment = 10 /* seconds */;

    // Small execution times are very "noisy", since the System.nanoTime()
    // method does not have sufficient precision to measure them. In some
    // systems, smaller execution times may even be measured as 0.0! Hence, in
    // many cases it is preferable to perform a run of contiguous repetitions of
    // an experiment, instead of a single experiment. The total
    // execution time of that run of contiguous repetitions is measured. Then,
    // the execution time of a single experiment is estimated as the average
    // execution time, that is, the total execution time of the contiguous
    // repetitions divided by the number of contiguous repetitions of the
    // experiment performed. Instead of using always the same number of
    // contiguous repetitions, however, it is preferable to establish the
    // minimum
    // duration of a run to value which is clearly long enough for
    // System.nanoTime() to measure with acceptable accuracy.
    public static final double minimumTimePerContiguousRepetitions = 1e-5 /* seconds */;

    // A simple, inefficient way to calculate the median of the values in an
    // ArrayList:
    public static double medianOf(final ArrayList<Double> values) {
        final int size = values.size();

        values.sort(null);

        if (size % 2 == 0)
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        else
            return values.get(size / 2);
    }

    // The method whose execution times are wanted:
    public static void runnable(final Runnable runnable) {

        runnable.run();
    }
    // Used to store the results of the sums, so that the Java compiler does not
    // optimize away our calls to runnable() (this variable is used when
    // showing the experimental results, so we don't get warnings
    // about unused variables):

    // Estimate the number of contiguous repetitions to perform for a given
    // limit of the numbers to sum in the experiment:
    public static int contiguousRepetitionsFor(final Runnable runnable) {
        final Stopwatch stopwatch = new Stopwatch();
        int contiguousRepetitions = 0;
        do {
            runnable(runnable);
            contiguousRepetitions++;
        } while (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions);

        // The loop stops when the minimum time per contiguous repetitions is
        // reached. For longer experiments, this will mostly turn out to be one,
        // which is what we would expect, since contiguous repetitions are
        // useful only for small execution times.

        return contiguousRepetitions;
    }

    // Performs a run of contiguous repetitions of an experiment to obtain the
    // execution time of the method to calculate the sum of the integers from 1
    // to a given limit. The number of contiguous experiments is also passed as
    // argument.
    public static double executionTimeFor(final Runnable runnable,
                                          final int contiguousRepetitions) {
        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i != contiguousRepetitions; i++)
            runnable(runnable);
        return stopwatch.elapsedTime() / contiguousRepetitions;
    }

    // Performs experiments to obtain a sequence of estimates of the execution
    // time of the method to calculate the sum of the integers from 1
    // to a given limit. The number of experiments to performed is not fixed.
    // Rather, a time budget is used and the experiments are repeated until the
    // budged is spent. The sequence of the execution times obtained is then
    // used to calculate the median execution time, which is a reasonably robust
    // statistic. The results are shown, except if this is a warm up run.
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
