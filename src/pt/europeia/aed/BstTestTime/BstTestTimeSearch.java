package pt.europeia.aed.BstTestTime;

import edu.princeton.cs.algs4.StdRandom;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.book.chapter3.section2.BstOrderedTable;
import pt.europeia.aed.files.Excel;
import pt.europeia.aed.files.ExtractFilesGeneric;

import java.io.IOException;
import java.util.ArrayList;

public class BstTestTimeSearch {

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

    private static void experiment(final Double[] array, BstOrderedTable<Double, String> bstOrderedTable) {

        for (int i = 0; i < array.length - 1; i++) {
            bstOrderedTable.valueFor(array[i]);
        }

    }

    public static int contiguousRepetitionsFor(BstOrderedTable<Double, String> bstOrderedTable, Double[] arrayForSearch) {

        int repetitions = 0;
        Stopwatch stopwatch = new Stopwatch();
        do {
            experiment(arrayForSearch, bstOrderedTable);
            repetitions++;
        } while (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions);
        return repetitions;
    }

    private static double executionTimesFor(final int contiguousRepetitions, BstOrderedTable<Double, String> bstOrderedTable, Double[] arrayForSearch) {

        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i != contiguousRepetitions; i++) {
            experiment(arrayForSearch, bstOrderedTable);
        }
        return stopwatch.elapsedTime() / contiguousRepetitions;
    }

    public static void performExperimentsForSearch(final Double[] arrayForSearch, boolean isWarmup, final int limit, final Excel excel) throws IOException {

        int contiguousRepetitions;
        BstOrderedTable<Double, String> bstOrderedTable = fillBstOrderedTable(arrayForSearch);
        Double[] randomIndex = randomIndex(arrayForSearch);
        contiguousRepetitions = contiguousRepetitionsFor(bstOrderedTable, randomIndex);
        final ArrayList<Double> executionTimes = new ArrayList<>();
        final Stopwatch stopwatch = new Stopwatch();

        long repetitions = 0;
        do {
            executionTimes.add(executionTimesFor(contiguousRepetitions, bstOrderedTable, randomIndex));
            repetitions++;
        } while (stopwatch.elapsedTime() < timeBudgetPerExperiment);

        final double median = medianOf(executionTimes);
        if (!isWarmup) {
            excel.writeDataTimes(limit, median, executionTimes.get(0), repetitions);
            System.out.println(limit + "\t" + median + "\t" + repetitions);
        }
    }

    private static BstOrderedTable<Double, String> fillBstOrderedTable(Double[] array) {

        BstOrderedTable<Double, String> bstOrderedTable = new BstOrderedTable<>();
        for (int i = 0; i < array.length; i++) {
            bstOrderedTable.put(array[i], "Benfica");
        }
        return bstOrderedTable;
    }

    private static Double[] randomIndex(Double[] array) {

        Double[] randomIndex = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            randomIndex[i] = array[i];
        }
        StdRandom.shuffle(randomIndex);
        return randomIndex;
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {


        ExtractFilesGeneric extractFilesGeneric = new ExtractFilesGeneric(20);

        Excel excelShuffle = new Excel("SearchShuffle", "Shuffle");

        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            BstTestTimeSearch.performExperimentsForSearch(extractFilesGeneric.getSuffleArray()[exponent], true, limit, excelShuffle);
        }
        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            BstTestTimeSearch.performExperimentsForSearch(extractFilesGeneric.getSuffleArray()[exponent], false, limit, excelShuffle);
        }

        excelShuffle.close();


        Excel excelSorted = new Excel("SearchSorted", "Sorted");
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            BstTestTimeSearch.performExperimentsForSearch(extractFilesGeneric.getSortedArray()[exponent], true, limit, excelShuffle);
        }
        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            BstTestTimeSearch.performExperimentsForSearch(extractFilesGeneric.getSortedArray()[exponent], false, limit, excelShuffle);
        }
        excelSorted.close();

    }
}
