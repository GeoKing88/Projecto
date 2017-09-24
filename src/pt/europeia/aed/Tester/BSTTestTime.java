package pt.europeia.aed.Tester;

import edu.princeton.cs.algs4.StdRandom;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.book.chapter3.section2.BstOrderedTable;
import pt.europeia.aed.files.Excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.out;

public class BSTTestTime {


    public static final double timeBudgetPerExperiment = 10 /* seconds */;
    public static final double minimumTimePerContiguousRepetitions = 1e-5 /* seconds */;
    /*
        public static double medianOf(final ArrayList<Double> values) {
            final int size = values.size();

            values.sort(null);

            if (size % 2 == 0)
                return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
            else
                return values.get(size / 2);
        }

        public static void experiment(final Double[] array, BSTMethod bstMethod, BstOrderedTable<Double, String> bstOrderedTable) {

            if (bstMethod.equals(BSTMethod.put)) {
                for (int i = 0; i < array.length; i++) {
                    BstOrderedTable<Double, String> bst = new BstOrderedTable<>();
                    bst.put(array[i], "Benfica");
                }
            } else if (bstMethod.equals(BSTMethod.search)) {
                bstOrderedTable.valueFor(array[StdRandom.uniform(array.length - 1)]);
            } else if (bstMethod.equals(BSTMethod.searchAll)) {
                for (int i = 0; i < array.length - 1; i++) {
                    bstOrderedTable.valueFor(array[i]);
                }
            } else {


            }
        }


        public static int contiguousRepetitionsFor(final Double[][] arrayForPut, BSTMethod bstMethod, BstOrderedTable<Double, String> bstOrderedTable, Double[] arrayForSearch,
                                                   HashMap<Double, BstOrderedTable<Double, String>> hashMap) {

            if (bstMethod.equals(BSTMethod.put)) {
                int repetitions = 0;
                Stopwatch stopwatch = new Stopwatch();
                do {
                    if (arrayForPut.length <= repetitions) {
                        repetitions = 0;
                        break;
                    }
                    experiment(arrayForPut[repetitions], bstMethod, null);
                    repetitions++;
                } while (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions);
                return repetitions;
            } else if (bstMethod.equals(BSTMethod.search) || bstMethod.equals(BSTMethod.searchAll)) {
                int repetitions = 0;
                Stopwatch stopwatch = new Stopwatch();
                do {
                    experiment(arrayForSearch, bstMethod, bstOrderedTable);
                    repetitions++;
                } while (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions);
                return repetitions;

            } else {

                int repetitions = 0;
                Stopwatch stopwatch = new Stopwatch();
                do {
                    if (arrayForPut.length <= repetitions) {
                        repetitions = 0;
                        break;
                    }
                    experiment(arrayForPut[repetitions], bstMethod, hashMap.keySet().);



                }
            }

        public static double executionTimesFor(final Double[][] arrayForPut, final int contiguousRepetitions, BSTMethod
                bstMethod, BstOrderedTable<Double, String> bstOrderedTable, Double[] arrayForSearch) {

            if (bstMethod.equals(BSTMethod.put)) {
                final Stopwatch stopwatch = new Stopwatch();
                for (int i = 0; i != contiguousRepetitions; i++)
                    experiment(arrayForPut[i], bstMethod, null, null);
                return stopwatch.elapsedTime() / contiguousRepetitions;
            } else if (bstMethod.equals(BSTMethod.search) || bstMethod.equals(BSTMethod.searchAll)) {
                final Stopwatch stopwatch = new Stopwatch();
                for (int i = 0; i != contiguousRepetitions; i++) {
                    experiment(arrayForSearch, bstMethod, bstOrderedTable, null);
                }
                return stopwatch.elapsedTime() / contiguousRepetitions;
            } else {
                return 0;
            }
        }


        public static void performExperimentsFor(final Double[] array, boolean isWarmup, final int limit, final Excel excel, final BSTMethod bstMethod) throws IOException {

            if (bstMethod.equals(BSTMethod.search) || bstMethod.equals(BSTMethod.searchAll)) {
                performExperimentsForSearch(array, isWarmup, limit, excel, bstMethod);
            } else if (bstMethod.equals(BSTMethod.delete)) {


            } else {
                performanceExperimentsForPut(array, isWarmup, limit, excel, bstMethod);
            }
        }

        private static void performanceExperimentsForDelete(final Double[] array, boolean isWarmup, final int limit, final Excel excel, final BSTMethod bstMethod) {
            int size = 8;
            int contiguousRepetitions;
            do {
                Double[][] cloneArrays = cloneArrayToContiguousRepetitions(array, size * 2);
                HashMap<Integer, BstOrderedTable<Double, String>> putBst = putBST(size * 2, array);
                contiguousRepetitions = contiguousRepetitionsFor(cloneArrays, bstMethod, )

            }
        }


        private static void performanceExperimentsForPut(final Double[] array, boolean isWarmup, final int limit, final Excel excel, final BSTMethod bstMethod) {
            int size = 8;
            int contiguousRepetitions;
            do {
                Double[][] cloneArrays = cloneArrayToContiguousRepetitions(array, size * 2);
                contiguousRepetitions = contiguousRepetitionsFor(cloneArrays, bstMethod, null, null);
            } while (contiguousRepetitions == 0);

            Double[][] cloneArray = cloneArrayToContiguousRepetitions(array, contiguousRepetitions);

            final ArrayList<Double> executionTimes = new ArrayList<>();
            final Stopwatch stopwatch = new Stopwatch();

            long repetitions = 0;
            do {
                executionTimes.add(executionTimesFor(cloneArray, contiguousRepetitions, bstMethod, null, null));
                repetitions++;
            } while (stopwatch.elapsedTime() < timeBudgetPerExperiment);

            final double median = medianOf(executionTimes);

            if (!isWarmup)
                excel.writeDataTimes(limit, median, executionTimes.get(0), repetitions);
            out.println(
                    limit + "\t" + median + "\t" + repetitions);

        }

        private static void performExperimentsForSearch(final Double[] arrayForSearch, boolean isWarmup, final int limit, final Excel excel, final BSTMethod bstMethod) throws IOException {

            int contiguousRepetitions;
            BstOrderedTable<Double, String> bstOrderedTable = fillBstOrderedTable(arrayForSearch);
            Double[] randomIndex = randomIndex(arrayForSearch);
            contiguousRepetitions = contiguousRepetitionsFor(null, bstMethod, bstOrderedTable, randomIndex);
            final ArrayList<Double> executionTimes = new ArrayList<>();
            final Stopwatch stopwatch = new Stopwatch();

            long repetitions = 0;
            do {
                executionTimes.add(executionTimesFor(null, contiguousRepetitions, bstMethod, bstOrderedTable, randomIndex));
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


        private static HashMap<Integer, BstOrderedTable<Double, String>> putBST(int size, Double[] array) {
            HashMap<Integer, BstOrderedTable<Double, String>> hashMap = new HashMap<>();

            for (int i = 0; i < size; i++) {
                BstOrderedTable<Double, String> bst = new BstOrderedTable<>();
                for (int j = 0; j < array.length; j++) {
                    bst.put(array[j], "Benfica");
                }
                hashMap.put(i, bst);
            }
            return hashMap;
        }


        private static Double[][] cloneArrayToContiguousRepetitions(Double[] array, int size) {
            Double[][] clone = new Double[size][];
            for (int i = 0; i < size; i++) {
                clone[i] = array.clone();
            }
            return clone;
        }
    */
    public static void main(String[] args) throws IOException, InvalidFormatException {
        /*

        ExtractFilesGeneric extractFilesGeneric = new ExtractFilesGeneric(20);
       Excel put = new Excel("Put", "bst");

        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            BSTTestTester.performExperimentsFor(extractFilesGeneric.getSuffleArray()[exponent], true, limit, put, BSTMethod.put);
        }

        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            BSTTestTester.performExperimentsFor(extractFilesGeneric.getSuffleArray()[exponent], false, limit, put, BSTMethod.put);
        }
        put.close();

        Excel search = new Excel("Search", "Search");
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            BSTTestTester.performExperimentsFor(extractFilesGeneric.getSuffleArray()[exponent], true, limit, search, BSTMethod.search);
        }

        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            BSTTestTester.performExperimentsFor(extractFilesGeneric.getSuffleArray()[exponent], false, limit, search, BSTMethod.search);
        }
        search.close();
*/
    }

}
