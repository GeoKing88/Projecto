package pt.europeia.aed.experiences;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.book.chapter1.section3.iterable.ResizingArrayQueue;
import pt.europeia.aed.excel.Excel;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.System.out;


public class ResizingArrayQueueExperiences {
    // A time budget is established per experiment. Each experiment is repeated
    // as many times as necessary to expend this budget. That is, each
    // experiment is repeated until the total time spent repeating it exceeds
    // the budget.
    public static final double timeBudgetPerExperiment = 5 /* seconds */;

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
    public static void sumFrom1To(final int limit, ResizingArrayQueue<String> resizingArrayQueue) {
        for (int i = 0; i < limit; i++) {
            resizingArrayQueue.enqueue("Benfica");
        }

    }

    // Estimate the number of contiguous repetitions to perform for a given
    // limit of the numbers to sum in the experiment:
    public static long contiguousRepetitionsFor(final int limit, final long repetitions) {


        ArrayList<ResizingArrayQueue<String>> resizingArrayQueues = new ArrayList<ResizingArrayQueue<String>>();

        for (int i = 0; i < repetitions; i++) {
            ResizingArrayQueue<String> resizingArrayQueue = new ResizingArrayQueue<>();
            resizingArrayQueues.add(resizingArrayQueue);
        }
        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < resizingArrayQueues.size(); i++) {
            sumFrom1To(limit, resizingArrayQueues.get(i));
        }
        if (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions) {
            return 10000;
        }

        // The loop stops when the minimum time per contiguous repetitions is
        // reached. For longer experiments, this will mostly turn out to be one,
        // which is what we would expect, since contiguous repetitions are
        // useful only for small execution times.

        return repetitions;
    }

    // Performs a run of contiguous repetitions of an experiment to obtain the
    // execution time of the method to calculate the sum of the integers from 1
    // to a given limit. The number of contiguous experiments is also passed as
    // argument.
    public static double executionTimeFor(final int limit, final long contiguousRepetitions, ResizingArrayQueue<String> resizingArrayQueue) {
        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i != contiguousRepetitions; i++)
            sumFrom1To(limit, resizingArrayQueue);
        return stopwatch.elapsedTime() / contiguousRepetitions;
    }

    final static int numberArrays = 20000000;

    // Performs experiments to obtain a sequence of estimates of the execution
    // time of the method to calculate the sum of the integers from 1
    // to a given limit. The number of experiments to performed is not fixed.
    // Rather, a time budget is used and the experiments are repeated until the
    // budged is spent. The sequence of the execution times obtained is then
    // used to calculate the median execution time, which is a reasonably robust
    // statistic. The results are shown, except if this is a warm up run.
    public static void performExperimentsFor(final int limit, final boolean isWarmup, Excel excel) {
        final ArrayList<Double> executionTimes = new ArrayList<Double>();
        long repetition = 1;
        long contiguousRepetitions;
        while (true) {
            contiguousRepetitions = contiguousRepetitionsFor(limit, repetition);
            if (contiguousRepetitions == 10000) {
                repetition++;
            } else {
                break;
            }
        }
        long repetitions = 0;
        ArrayList<ResizingArrayQueue<String>> resizingArrayQueues = new ArrayList<>();
        for (int i = 0; i < Math.ceil(numberArrays / (Math.sqrt(limit))); i++) {
            ResizingArrayQueue<String> resizingArrayQueue = new ResizingArrayQueue<>();
            resizingArrayQueues.add(resizingArrayQueue);
        }
        int index = 0;
        Stopwatch stopwatch = new Stopwatch();
        do {
            executionTimes.add(executionTimeFor(limit, contiguousRepetitions, resizingArrayQueues.get(index)));
            repetitions++;
            index++;
        } while (timeBudgetPerExperiment > stopwatch.elapsedTime());

        final double median = medianOf(executionTimes);

        if (!isWarmup) {
            out.println("Position: " + limit + "\t" + "Median: " + median + "\t" + "Repetition: " + repetitions + "\t" + "minimum: " + executionTimes.get(0) +
                    "\t" + "Maximum: " + executionTimes.get(executionTimes.size() - 1));
            excel.writeDataTimes(limit, median, executionTimes.get(0), repetitions);

        }
    }

    public static void main(final String[] arguments)
            throws InterruptedException, IOException, InvalidFormatException {
        // The experiments are run for limits of the sums which increase
        //geometrically, through the powers of 2:

        // Warm up (this attempts to force the JIT compiler to do its work
        // before the experiments actually begin):
        Excel rezisingArrayExcel = new Excel("RezisingArray", "MelhorCaso");
        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2) {
            performExperimentsFor(limit, true, rezisingArrayExcel);
        }

        // The actual experiments are performed here, with limits going from 1
        // to 2^30:
        System.out.println("Melhor Caso");
        for (int exponent = 0, limit = 1; exponent != 20; exponent++, limit *= 2) {
            performExperimentsFor(limit, false, rezisingArrayExcel);
        }
        for (int exponent = 0, limit = 1; exponent != 20; exponent++, limit = (limit*2)) {
            performExperimentsFor(limit+1, false, rezisingArrayExcel);
        }

        rezisingArrayExcel.close();
    }
}