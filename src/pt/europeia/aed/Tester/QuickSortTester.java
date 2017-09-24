package pt.europeia.aed.Tester;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.SortingTestTime.OptimizedQuickTestTime;
import pt.europeia.aed.SortingTestTime.QuickNoShuffleTestTime;
import pt.europeia.aed.SortingTestTime.QuickSortTestTime;
import pt.europeia.aed.SortingTestTime.SystemQuickTestTime;
import pt.europeia.aed.files.Excel;
import pt.europeia.aed.files.ExtractFilesGeneric;

import java.io.IOException;

public class QuickSortTester {

    final int LIMIT = 20;

    public QuickSortTester() throws IOException, InvalidFormatException {

        ExtractFilesGeneric extractFilesGeneric = new ExtractFilesGeneric(LIMIT);

        Excel excelQuickSuffle = new Excel("QuickSortSuffle", "Sorted");
        quickSortSuffle(excelQuickSuffle, extractFilesGeneric.getSuffleArray());
        excelQuickSuffle.close();

        Excel excelQuickSortPartiallySorted = new Excel("QuickSortPartiallySorted", "PartiallySorted");
        quickWithoutSuffle(excelQuickSortPartiallySorted, extractFilesGeneric.getDataFromFilesPartiallySorted());
        excelQuickSortPartiallySorted.close();

        Excel excelQuickSortSortedData = new Excel("QuickSortSortedData", "Sorted");
        quickWithoutSuffle(excelQuickSortSortedData, extractFilesGeneric.getInvertedSorted());
        excelQuickSortSortedData.close();

        Excel excelQuickSortInvertedPartiallySorted = new Excel("QuickSortInvParSorted", "InvertedPartSorted");
        quickWithoutSuffle(excelQuickSortInvertedPartiallySorted, extractFilesGeneric.getInvertedPartiallySorted());
        excelQuickSortInvertedPartiallySorted.close();

        Excel excelQuickSortInvertedSorted = new Excel("QuickSortInvertedSort", "InvertedSort");
        quickWithoutSuffle(excelQuickSortInvertedSorted, extractFilesGeneric.getInvertedSorted());
        excelQuickSortInvertedSorted.close();

        Excel excelSystemQuickShuffleData = new Excel("SystemQuick", "SystemQuick");
        systemQuickSort(excelSystemQuickShuffleData, extractFilesGeneric.getSuffleArray());
        excelSystemQuickShuffleData.close();

        Excel excelOptimizedQuickShuffleData = new Excel("OptimizedQuick", "OptimizeQuick");
        optimizedQuickSort(excelOptimizedQuickShuffleData, extractFilesGeneric.getSuffleArray());
        excelOptimizedQuickShuffleData.close();

    }

    private void quickSortSuffle(Excel excel, Double[][] doubleArray) {

        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            QuickSortTestTime.performExperimentsFor(doubleArray[exponent], true, limit, excel);

        }
        for (int exponent = 0, limit = 2; exponent != 21; exponent++, limit *= 2) {
            QuickSortTestTime.performExperimentsFor(doubleArray[exponent], false, limit, excel);
        }

    }

    private void quickWithoutSuffle(Excel excel, Double[][] doubleArray) {

        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            QuickNoShuffleTestTime.performExperimentsFor(doubleArray[exponent], true, limit, excel);

        }
        for (int exponent = 0, limit = 2; exponent != 21; exponent++, limit *= 2) {
            QuickNoShuffleTestTime.performExperimentsFor(doubleArray[exponent], false, limit, excel);
        }
    }

    private void systemQuickSort(Excel excel, Double[][] doubleArray) {
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            SystemQuickTestTime.performExperimentsFor(doubleArray[exponent], true, limit, excel);

        }
        for (int exponent = 0, limit = 2; exponent != 21; exponent++, limit *= 2) {
            SystemQuickTestTime.performExperimentsFor(doubleArray[exponent], false, limit, excel);
        }
    }

    private void optimizedQuickSort(Excel excel, Double[][] doubleArray) {
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            OptimizedQuickTestTime.performExperimentsFor(doubleArray[exponent], true, limit, excel);

        }
        for (int exponent = 0, limit = 2; exponent != 21; exponent++, limit *= 2) {
            OptimizedQuickTestTime.performExperimentsFor(doubleArray[exponent], false, limit, excel);
        }
    }
}