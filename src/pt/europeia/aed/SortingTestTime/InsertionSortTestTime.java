package pt.europeia.aed.SortingTestTime;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.Stopwatch;
import pt.europeia.aed.files.Excel;

import java.io.*;
import java.util.ArrayList;

import static java.lang.System.out;

public class InsertionSortTestTime {


    public static final double timeBudgetPerExperiment = 0.1 /* seconds */;

    public static final double minimumTimePerContiguousRepetitions = 1e-5 /* seconds */;


    public static double medianOf(final ArrayList<Double> values) {
        final int size = values.size();

        values.sort(null);

        if (size % 2 == 0)
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        else
            return values.get(size / 2);
    }

    public static int contiguousRepetitionsFor(Double[][] doubles) {
        int contiguousRepetitions = 0;
        final Stopwatch stopwatch = new Stopwatch();
        do {
            if (doubles.length <= contiguousRepetitions) {
                return 0;
            }
            Insertion.sort(doubles[contiguousRepetitions]);
            contiguousRepetitions++;
        } while (stopwatch.elapsedTime() < minimumTimePerContiguousRepetitions);
        return contiguousRepetitions;
    }


    public static double executionTimeFor(final Double[][] doubleArray) {


        final Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i != doubleArray.length; i++) {
            Insertion.sort(doubleArray[i]);
        }
        return stopwatch.elapsedTime() / doubleArray.length;
    }


    public static void performExperimentsFor(final Double[] array, final int limit, final boolean isWarmup, final Excel excel) {


        final ArrayList<Double> executionTimes = new ArrayList<>();
        int size = 8;
        int contiguousRepetitions;
        do {
            Double[][] cloneArrays = cloneArrayToContiguousRepetitions(size * 2, array);
            contiguousRepetitions = contiguousRepetitionsFor(cloneArrays);
        } while (contiguousRepetitions == 0);
        long repetitions = 0;
        Double[][] cloneArrayToContiguousRepetitions = cloneArrayToContiguousRepetitions(contiguousRepetitions, array);
        double time = 0;
        do {
            Double[][] arrayToTest = (Double[][]) copy(cloneArrayToContiguousRepetitions);
            final Stopwatch stopwatch = new Stopwatch();
            executionTimes.add(executionTimeFor(arrayToTest));
            time += stopwatch.elapsedTime();
            repetitions++;
        } while (time < timeBudgetPerExperiment);

        final double median = medianOf(executionTimes);

        if (!isWarmup)
            out.println(
                    limit + "\t" + median + "\t" + repetitions + "\t");
        excel.writeDataTimes(limit, median, executionTimes.get(0), repetitions);
        /*-
        out.println("Sum from 1 to " + limit + " = " + sum + " [" + median
                + "s median time based on " + repetitions
                + " repetitions of " + contiguousRepetitions
                + " contiguous repetitions]");
        */
    }


    private static Double[][] cloneArrayToContiguousRepetitions(int size, Double[] array) {
        Double[][] clone = new Double[size][];
        for (int i = 0; i < size; i++) {
            clone[i] = (Double[]) copy(array);
        }
        return clone;
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

    public static void main(final String[] arguments) throws IOException, InvalidFormatException {

        ArrayList<Double[]> arraysOrdemCrescente = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Double[] array = new Double[(int) Math.pow(2, i)];
            for (int j = 0; j < Math.pow(2, i); j++) {
                array[j] = Double.valueOf(j);
            }
            arraysOrdemCrescente.add(array);
        }


        Excel excelInsertion = new Excel("Insertion", "Shuffle");
        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2) {
            performExperimentsFor(arraysOrdemCrescente.get(exponent), limit, true, excelInsertion);
        }

        for (int exponent = 0, limit = 1; exponent != 20; exponent++, limit *= 2) {
            performExperimentsFor(arraysOrdemCrescente.get(exponent), limit, false, excelInsertion);
        }
        excelInsertion.close();

        Excel excelQuick = new Excel("Quick", "Shuffle");
        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2) {
            QuickSortTestTime.performExperimentsFor(arraysOrdemCrescente.get(exponent), limit, true, excelQuick);
        }

        for (int exponent = 0, limit = 1; exponent != 20; exponent++, limit *= 2) {
            QuickSortTestTime.performExperimentsFor(arraysOrdemCrescente.get(exponent), limit, false, excelQuick);
        }
        excelQuick.close();

    }
}



