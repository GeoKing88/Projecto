package pt.europeia.aed.BstTestTime;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.book.chapter3.section2.BstOrderedTable;
import pt.europeia.aed.files.Excel;
import pt.europeia.aed.files.ExtractFiles;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static java.lang.System.out;


public class BstTestTimePut {


    public static final double timeBudgetPerExperiment = 10 /* seconds */;
    public static final double minimumTimePerContiguousRepetitions = 1e-5 /* seconds */;
    static int arraySize = 0;

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
       arraySize = array.length;
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
                limit + "\t" + median + "\t" + repetitions +"\t"+arraySize);

    }

    /**
     * This method was copy from  http://javatechniques.com/blog/faster-deep-copies-of-java-objects/
     * <p>
     * This permit to deep copy objects.
     *
     * @param orig
     * @return object clone
     */
    private static Object copy(Object orig) {
        Object obj = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();
            ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return obj;
    }


    public static void main(String[] args) throws IOException, InvalidFormatException {


        ExtractFiles extractFilesGeneric2 = new ExtractFiles(20);

        Excel excelShuffle = new Excel("PutShuffle", "Shuffle");

        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            BstTestTimePut.performanceExperimentsForPut(extractFilesGeneric2.getShuffle()[exponent], true, limit, excelShuffle);
        }
        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            BstTestTimePut.performanceExperimentsForPut(extractFilesGeneric2.getShuffle()[exponent], false, limit, excelShuffle);
        }

        excelShuffle.close();


        Excel excelSorted = new Excel("PutSorted", "Sorted");
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            BstTestTimePut.performanceExperimentsForPut(extractFilesGeneric2.getSorted()[exponent], true, limit, excelSorted);
        }
        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            BstTestTimePut.performanceExperimentsForPut(extractFilesGeneric2.getSorted()[exponent], false, limit, excelSorted);
        }
        excelSorted.close();
    }

}
