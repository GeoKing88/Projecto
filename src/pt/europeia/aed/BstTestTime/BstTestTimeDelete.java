package pt.europeia.aed.BstTestTime;

import edu.princeton.cs.algs4.StdRandom;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.book.chapter3.section2.BstOrderedTable;
import pt.europeia.aed.files.Excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BstTestTimeDelete {

    public static final double timeBudgetPerExperiment = 0.1 /* seconds */;
    public static final double minimumTimePerContiguousRepetitions = 1e-5 /* seconds */;

    private static double medianOf(final ArrayList<Double> values) {
        final int size = values.size();

        values.sort(null);

        if (size % 2 == 0)
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        else
            return values.get(size / 2);
    }


    private static void experiment(final Double[] randomIndex, BstOrderedTable<Double, String> bstOrderedTable) {
        for (int i = 0; i < bstOrderedTable.size(); i++) {
            bstOrderedTable.delete(randomIndex[i]);
            System.out.println(bstOrderedTable.size());
        }
    }

    private static int contiguousRepetitionsFor(final Double[] randomIndex, LinkedHashMap<Integer, BstOrderedTable<Double, String>> linkedHashMapBstOrderTables) {

        int repetitions = 0;
        Stopwatch stopwatch = new Stopwatch();
        do {
            if (randomIndex.length <= repetitions) {
                repetitions = 0;
                break;
            }
            experiment(randomIndex, linkedHashMapBstOrderTables.get(repetitions));
            repetitions++;
        } while (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions);
        return repetitions;
    }

    private static double executionTimesFor(final Double[] randomIndex, final int contiguousRepetitions, LinkedHashMap<Integer, BstOrderedTable<Double, String>> linkedHashMapBstOrderTables) {


        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i != contiguousRepetitions; i++)
            experiment(randomIndex, linkedHashMapBstOrderTables.get(i));
        return stopwatch.elapsedTime() / contiguousRepetitions;
    }


    public static void performanceExperimentsForDelete(final Double[] array, boolean isWarmup, final int limit, final Excel excel) {
        int size = 8;
        int contiguousRepetitions;
        do {
            LinkedHashMap<Integer, BstOrderedTable<Double, String>> bstOrderedTableLinkedHashMap = createBstOrderTables(size * 2, array);
            contiguousRepetitions = contiguousRepetitionsFor(randomIndex(array), bstOrderedTableLinkedHashMap);
        } while (contiguousRepetitions == 0);
        LinkedHashMap<Integer, BstOrderedTable<Double, String>> bstOrderedTableLinkedHashMap = createBstOrderTables(size * 2, array);
        Double[] randomIndex = randomIndex(array);

        final ArrayList<Double> executionTimes = new ArrayList<>();
        final Stopwatch stopwatch = new Stopwatch();

        long repetitions = 0;
        do {
            executionTimes.add(executionTimesFor(randomIndex, contiguousRepetitions, bstOrderedTableLinkedHashMap));
            repetitions++;
        } while (stopwatch.elapsedTime() < timeBudgetPerExperiment);

        final double median = medianOf(executionTimes);

        if (!isWarmup)
            excel.writeDataTimes(limit, median, executionTimes.get(0), repetitions);
        System.out.println(
                limit + "\t" + median + "\t" + repetitions);

    }

    private static Double[] randomIndex(Double[] array) {

        Double[] randomIndex = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            randomIndex[i] = array[i];
        }
        StdRandom.shuffle(randomIndex);
        return randomIndex;
    }


    private static LinkedHashMap<Integer, BstOrderedTable<Double, String>> createBstOrderTables(int size, Double[] array) {

        LinkedHashMap<Integer, BstOrderedTable<Double, String>> linkedHashMap = new LinkedHashMap<>();
        BstOrderedTable<Double, String> bstOrderedTable = new BstOrderedTable<>();
        for (int j = 0; j < array.length; j++) {
            bstOrderedTable.put(array[j], "Benfica");
        }
        for (int i = 0; i < size; i++) {
            linkedHashMap.put(i, new BstOrderedTable<>(bstOrderedTable));
        }
        return linkedHashMap;
    }

   /* public static void main(String[] args) throws IOException, InvalidFormatException {





        ExtractFilesGeneric2 extractFilesGeneric = new ExtractFilesGeneric2(20);
        Excel excelShuffle = new Excel("DeleteShuffle", "Shuffle");

        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            BstTestTimeDelete.performanceExperimentsForDelete(extractFilesGeneric.getSuffleArray()[exponent], true, limit, excelShuffle);
        }
        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            BstTestTimeDelete.performanceExperimentsForDelete(extractFilesGeneric.getSuffleArray()[exponent], false, limit, excelShuffle);
        }

        excelShuffle.close();


        Excel excelSorted = new Excel("DeleteSorted", "Sorted");
        for (int exponent = 0, limit = 2; exponent != 3; exponent++, limit *= 2) {
            BstTestTimeDelete.performanceExperimentsForDelete(extractFilesGeneric.getSortedArray()[exponent], true, limit, excelSorted);
        }
        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            BstTestTimeDelete.performanceExperimentsForDelete(extractFilesGeneric.getSortedArray()[exponent], false, limit, excelSorted);
        }
        excelSorted.close();

    }*/

}



