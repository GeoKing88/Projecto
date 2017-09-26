package pt.europeia.aed.BstTestTime;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.book.chapter3.section2.BstOrderedTable;
import pt.europeia.aed.files.Excel;
import pt.europeia.aed.files.ExtractFilesGeneric;

import java.io.IOException;
import java.util.ArrayList;


public class BstTestTimePut {


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

    private static void experiment(final Double[] array) {
        BstOrderedTable<Double, String> bst = new BstOrderedTable<>();
        for (int i = 0; i < array.length; i++) {
            bst.put(array[i], "Benfica");
        }
    }


    private static int contiguousRepetitionsFor(final Double[] arrayForPut) {

        int repetitions = 0;
        Stopwatch stopwatch = new Stopwatch();
        do {
            experiment(arrayForPut);
            repetitions++;
        } while (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions);
        return repetitions;

    }


    private static double executionTimesFor(final Double[] arrayForPut, final int contiguousRepetitions) {


        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i != contiguousRepetitions; i++) {
            experiment(arrayForPut);
        }
        return stopwatch.elapsedTime() / contiguousRepetitions;
    }

    public static void performanceExperimentsForPut(final Double[] array, boolean isWarmup, final int limit, final Excel excel) {
        int contiguousRepetitions = contiguousRepetitionsFor(array);

        final ArrayList<Double> executionTimes = new ArrayList<>();
        final Stopwatch stopwatch = new Stopwatch();

        long repetitions = 0;
        do {
            executionTimes.add(executionTimesFor(array, contiguousRepetitions));
            repetitions++;
        } while (stopwatch.elapsedTime() < timeBudgetPerExperiment);

        final double median = medianOf(executionTimes);

        if (!isWarmup)
            excel.writeDataTimes(limit, median, executionTimes.get(0), repetitions);
        System.out.println(
                limit + "\t" + median + "\t" + repetitions);

    }

    public static void main(String[] args) throws IOException, InvalidFormatException {


        ExtractFilesGeneric extractFilesGeneric = new ExtractFilesGeneric(20);

        Excel excelShuffle = new Excel("PutShuffle", "Shuffle");

        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            BstTestTimePut.performanceExperimentsForPut(extractFilesGeneric.getSuffleArray()[exponent], true, limit, excelShuffle);
        }
        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            BstTestTimePut.performanceExperimentsForPut(extractFilesGeneric.getSuffleArray()[exponent], false, limit, excelShuffle);
        }

        excelShuffle.close();


        Excel excelSorted = new Excel("PutSorted", "Sorted");
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            BstTestTimePut.performanceExperimentsForPut(extractFilesGeneric.getSortedArray()[exponent], true, limit, excelSorted);
        }
        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            BstTestTimePut.performanceExperimentsForPut(extractFilesGeneric.getSortedArray()[exponent], false, limit, excelSorted);
        }
        excelSorted.close();
    }

}
