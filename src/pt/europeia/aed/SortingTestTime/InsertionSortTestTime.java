package pt.europeia.aed.SortingTestTime;


import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.book.chapter2.section1.Insertion;
import pt.europeia.aed.files.Excel;

import java.util.ArrayList;

import static java.lang.System.out;

public class InsertionSortTestTime {
/*
    private static final double timeBudgetPerExperiment = 10;
    private static final double minimumTimePerContiguousRepetitions = 1e-5;


    private static int contiguousRepetitionsFor(double[] array) {

        int repetitions = 0;
        double time = 0;
        while (true) {
            double[] cloneArray = array.clone();
            Stopwatch stopwatch = new Stopwatch();
            InsertionSort.sort(cloneArray);
            time += stopwatch.elapsedTime();
            repetitions++;
            if (time > minimumTimePerContiguousRepetitions) {
                break;
            }
        }
        return repetitions;
    }

    private static double executionTimeFor(double[][] arrayToTest, int contiguousRepetitions) {
        Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < arrayToTest.length; i++) {
            InsertionSort.sort(arrayToTest[i]);
        }
        return stopwatch.elapsedTime() / contiguousRepetitions;
    }

    private static double medianOf(ResizingArrayQueueDouble resizingArrayQueue) {
        double[] array = resizingArrayQueue.getItems();
        InsertionSort.sort(resizingArrayQueue.getItems());
        if (resizingArrayQueue.getItems().length % 2 == 0) {
            return array[array.length / 2 - 1] + array[array.length / 2] / 2;
        } else {
            return array[array.length / 2];
        }
    }

    public static void performanceRepetitionsFor(final double[] array, final boolean isWarmup, final Excel excel, final int limit) {
        double[] cloneArray = array.clone();
        int contiguousRepetitionsFor = contiguousRepetitionsFor(cloneArray);
        double[][] arrayToTest = new double[contiguousRepetitionsFor][array.length];
        for (int i = 0; i < arrayToTest.length; i++) {
            arrayToTest[i] = cloneArray.clone();
        }
        long repetitions = 0;
        ResizingArrayQueueDouble resizingArrayQueue = new ResizingArrayQueueDouble();
        Stopwatch stopwatch = new Stopwatch();
        do {
            resizingArrayQueue.enqueue(executionTimeFor(arrayToTest, contiguousRepetitionsFor));
            repetitions++;
        } while (stopwatch.elapsedTime() < timeBudgetPerExperiment);

        final double median = medianOf(resizingArrayQueue);

        if (!isWarmup) {

            excel.writeDataTimes(limit, median, resizingArrayQueue.getItems()[0], repetitions);
            out.println(
                    limit + "\t" + median + "\t" + repetitions);
        } else {

            out.println(
                    limit + "\t" + median + "\t" + repetitions);
        }
    }

    public static void main(String args[]) throws IOException, InvalidFormatException {
        ExtractFilesDouble extractFilesDouble = new ExtractFilesDouble();
        Excel excel = new Excel("TesteInsertionSort", "InsertionSort");

        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            InsertionSortTestTime.performanceRepetitionsFor(extractFilesDouble.getSuffleArray()[exponent], true, excel, limit);
        }
        for (int exponent = 0, limit = 2; exponent != 21; exponent++, limit *= 2) {
            InsertionSortTestTime.performanceRepetitionsFor(extractFilesDouble.getSuffleArray()[exponent], false, excel, limit);

        }
        excel.close();
    }
**/

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
            Insertion.sort(array[repetitions]);
            repetitions++;
        } while (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions);
        return repetitions;
    }


    public static double executionTimeFor(final Double[][] array, final int contiguousRepetitions) {
        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i != contiguousRepetitions; i++)
            Insertion.sort(array[i]);
        return stopwatch.elapsedTime() / contiguousRepetitions;
    }


    public static void performExperimentsFor(final Double[] array, boolean isWarmup, final int limit, final Excel excel) {
        int size = 8;
        int contiguousRepetitions = 0;
        do {
            Double[][] cloneArrays = cloneArrayToContiguousRepetitions(array, size*2);
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
