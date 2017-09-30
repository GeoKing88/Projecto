package pt.europeia.aed.files;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.util.Scanner;

public class ExtractFiles {


    private Double[][] partiallySorted;
    private Double[][] shuffle;
    private Double[][] sorted;
    private Double[][] invParSorted;
    private Double[][] invertedSorted;

    public ExtractFiles(final int maxExponent) throws FileNotFoundException {

        partiallySorted = createPartiallySorted(maxExponent);
        shuffle = createShuffle(maxExponent);
        sorted = createSorted(maxExponent);
        invParSorted = createInvParSorted(maxExponent);
        invertedSorted = createInvertedSorted(maxExponent);

    }

    private Double[][] createPartiallySorted(int maxExponent) throws FileNotFoundException {

        Double[][] createPartiallySorted = new Double[maxExponent][];
        for (int i = 0; i < maxExponent; i++) {
            File file = new File("/home/geoking88/Documents/Necessito de Guardar/Estrutura de Dados e Algoritmos/TrabalhadorEstudante/Ficheiros/Partially Sorted/partially_sorted_" + (int) Math.pow(2, i + 1) + ".txt");
            Double[] putArray = new Double[(int) Math.pow(2, i + 1)];
            int index = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Double doubleData = Double.parseDouble(data);
                putArray[index++] = doubleData;
            }
            createPartiallySorted[i] = putArray;
        }
        return createPartiallySorted;
    }

    private Double[][] createShuffle(int maxExponent) throws FileNotFoundException {

        Double[][] createPartiallySorted = new Double[maxExponent][];
        for (int i = 0; i < maxExponent; i++) {
            File file = new File("/home/geoking88/Documents/Necessito de Guardar/Estrutura de Dados e Algoritmos/TrabalhadorEstudante/Ficheiros/Shuffle/shuffled_" + (int) Math.pow(2, i + 1) + ".txt");
            Double[] putArray = new Double[(int) Math.pow(2, i + 1)];
            int index = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Double doubleData = Double.parseDouble(data);
                putArray[index++] = doubleData;
            }

            createPartiallySorted[i] = putArray;
        }
        return createPartiallySorted;
    }

    private Double[][] createSorted(int maxExponent) throws FileNotFoundException {

        Double[][] createPartiallySorted = new Double[maxExponent][];
        for (int i = 0; i < maxExponent; i++) {
            File file = new File("/home/geoking88/Documents/Necessito de Guardar/Estrutura de Dados e Algoritmos/TrabalhadorEstudante/Ficheiros/Sorted/sorted_" + (int) Math.pow(2, i + 1) + ".txt");
            Double[] putArray = new Double[(int) Math.pow(2, i + 1)];
            int index = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Double doubleData = Double.parseDouble(data);
                putArray[index++] = doubleData;
            }

            createPartiallySorted[i] = putArray;
        }
        return createPartiallySorted;
    }

    private Double[][] createInvParSorted(int maxExponent) throws FileNotFoundException {

        Double[][] createPartiallySorted = new Double[maxExponent][];
        for (int i = 0; i < maxExponent; i++) {
            File file = new File("/home/geoking88/Documents/Necessito de Guardar/Estrutura de Dados e Algoritmos/TrabalhadorEstudante/Ficheiros/Partially Sorted/partially_sorted_" + (int) Math.pow(2, i + 1) + ".txt");
            Double[] putArray = new Double[(int) Math.pow(2, i + 1)];
            int index = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Double doubleData = Double.parseDouble(data);
                putArray[index++] = 1 - doubleData;
            }
            createPartiallySorted[i] = putArray;
        }
        return createPartiallySorted;
    }

    private Double[][] createInvertedSorted(int maxExponent) throws FileNotFoundException {

        Double[][] createPartiallySorted = new Double[maxExponent][];
        for (int i = 0; i < maxExponent; i++) {
            File file = new File("/home/geoking88/Documents/Necessito de Guardar/Estrutura de Dados e Algoritmos/TrabalhadorEstudante/Ficheiros/Sorted/sorted_" + (int) Math.pow(2, i + 1) + ".txt");
            Double[] putArray = new Double[(int) Math.pow(2, i + 1)];
            int index = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Double doubleData = Double.parseDouble(data);
                putArray[index++] = 1 - doubleData;
            }
            createPartiallySorted[i] = putArray;
        }
        return createPartiallySorted;
    }


    public Double[][] getPartiallySorted() {
        return partiallySorted;
    }

    public Double[][] getShuffle() {
        return shuffle;
    }

    public Double[][] getSorted() {
        return sorted;
    }

    public Double[][] getInvParSorted() {
        return invParSorted;
    }

    public Double[][] getInvertedSorted() {
        return invertedSorted;
    }

   /* public static void main(String[] args) throws IOException, InvalidFormatException {
        ExtractFiles extractFiles = new ExtractFiles(20);
        Excel excelInsertion = new Excel("Insertion", "Shuffle");

        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(extractFiles.getPartiallySorted()[exponent], limit, true, excelInsertion);
        }

        for (int exponent = 0, limit = 1; exponent != 15; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(extractFiles.getPartiallySorted()[exponent], limit, false, excelInsertion);
        }
        excelInsertion.close();

        Excel excelSuffle = new Excel("Suffle", "suffle");
        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(extractFiles.getShuffle()[exponent], limit, true, excelSuffle);
        }

        for (int exponent = 0, limit = 1; exponent != 15; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(extractFiles.getShuffle()[exponent], limit, false, excelSuffle);
        }
        excelSuffle.close();

        Excel excelSorted = new Excel("Sorted", "sorted");
        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(extractFiles.getPartiallySorted()[exponent], limit, true, excelSorted);
        }

        for (int exponent = 0, limit = 1; exponent != 15; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(extractFiles.getPartiallySorted()[exponent], limit, false, excelSorted);
        }
        excelSorted.close();

        Excel excelInvParSorted = new Excel("ParInvertedSorted", "ParInvertedSorted");
        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(extractFiles.getInvParSorted()[exponent], limit, true, excelInvParSorted);
        }

        for (int exponent = 0, limit = 1; exponent != 15; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(extractFiles.getInvParSorted()[exponent], limit, false, excelInvParSorted);
        }
        excelInvParSorted.close();

        Excel excelInvertedSorted = new Excel("InvertedSorted", "InvertedSorted");
        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(extractFiles.getInvertedSorted()[exponent], limit, true, excelInvertedSorted);
        }

        for (int exponent = 0, limit = 1; exponent != 15; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(extractFiles.getInvertedSorted()[exponent], limit, false, excelInvertedSorted);
        }
        excelInvertedSorted.close();
    }*/
}



