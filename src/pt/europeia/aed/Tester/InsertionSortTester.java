package pt.europeia.aed.Tester;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.SortingTestTime.InsertionSortBinarySearchTestTime;
import pt.europeia.aed.SortingTestTime.InsertionSortTestTime;
import pt.europeia.aed.SortingTestTime.InsertionWithMovesTestTime;
import pt.europeia.aed.files.Excel;
import pt.europeia.aed.files.ExtractFiles;

import java.io.IOException;

public class InsertionSortTester {

    final int LIMIT = 20;

    public InsertionSortTester() throws IOException, InvalidFormatException {

        ExtractFiles extractFilesGeneric2 = new ExtractFiles(LIMIT);


        Excel excelInsertionSortPartiallySorted = new Excel("InsertionSortParSorted", "ParSorted");
        insertionSort(excelInsertionSortPartiallySorted, extractFilesGeneric2.getPartiallySorted());
        excelInsertionSortPartiallySorted.close();

        Excel excelInsertionSortShuffle = new Excel("InsertionSortSuffle", "Shuffle");
        insertionSort(excelInsertionSortPartiallySorted, extractFilesGeneric2.getShuffle());
        excelInsertionSortShuffle.close();

        Excel excelInsertionSortSorted = new Excel("InsertionSortSorted", "Sorted");
        insertionSort(excelInsertionSortSorted, extractFilesGeneric2.getInvertedSorted());
        excelInsertionSortSorted.close();

        Excel excelInsertionSortInvParSorted = new Excel("InsertionSortInvParSorted", "InvParSorted");
        insertionSort(excelInsertionSortInvParSorted, extractFilesGeneric2.getInvParSorted());
        excelInsertionSortInvParSorted.close();

        Excel excelInsertionSortInverted = new Excel("InsertionSortInverted", "Inverted");
        insertionSort(excelInsertionSortInverted, extractFilesGeneric2.getInvertedSorted());
        excelInsertionSortInverted.close();

        Excel excelInsertionSortWithMovesParSorted = new Excel("InsertionWithMovesParSor", "PartiallySorted");
        insertionWithMoves(excelInsertionSortWithMovesParSorted, extractFilesGeneric2.getPartiallySorted());
        excelInsertionSortWithMovesParSorted.close();

        Excel excelInsertionSortWithMovesShuffle = new Excel("InsertionWithMovesShuffle", "Shuffle");
        insertionWithMoves(excelInsertionSortWithMovesShuffle, extractFilesGeneric2.getShuffle());
        excelInsertionSortWithMovesShuffle.close();

        Excel excelInsertionSortWithMovesSorted = new Excel("InsertionWithMovesSorted", "Sorted");
        insertionWithMoves(excelInsertionSortWithMovesSorted, extractFilesGeneric2.getSorted());
        excelInsertionSortWithMovesSorted.close();

        Excel excelInsertionSortWithMovesInvPartSorted = new Excel("InsertionWithMovesIParSort", "PartInverted");
        insertionWithMoves(excelInsertionSortWithMovesInvPartSorted, extractFilesGeneric2.getInvParSorted());
        excelInsertionSortWithMovesInvPartSorted.close();

        Excel excelInsertionSortWithMovesInverted = new Excel("InsertionSortWithMovesInvSort", "InvertedSorted");
        insertionWithMoves(excelInsertionSortWithMovesInverted, extractFilesGeneric2.getInvertedSorted());
        excelInsertionSortWithMovesInverted.close();

        Excel excelInsertionBSParSorted = new Excel("InsertionSortBSParSorted", "PartiallySorted");
        insertionWithMovesAndBinarySearch(excelInsertionBSParSorted, extractFilesGeneric2.getPartiallySorted());
        excelInsertionBSParSorted.close();

        Excel excelInsertionBSShuffle = new Excel("InsertionSortBSShuffle", "Shuffle");
        insertionWithMovesAndBinarySearch(excelInsertionBSShuffle, extractFilesGeneric2.getShuffle());
        excelInsertionBSShuffle.close();

        Excel excelInsertionBSSorted = new Excel("InsertionSortBSSorted", "Sorted");
        insertionWithMovesAndBinarySearch(excelInsertionBSSorted, extractFilesGeneric2.getSorted());
        excelInsertionBSSorted.close();

        Excel excelInsertionBSInvParSorted = new Excel("InsertionSortIParSort", "InvertedPartiallySorted");
        insertionWithMovesAndBinarySearch(excelInsertionBSInvParSorted, extractFilesGeneric2.getInvParSorted());
        excelInsertionBSInvParSorted.close();

        Excel excelInsertionBSInvertedSorted = new Excel("InsertionSortInverted", "InvertedSorted");
        insertionWithMovesAndBinarySearch(excelInsertionBSInvertedSorted, extractFilesGeneric2.getInvertedSorted());
        excelInsertionBSInvertedSorted.close();


    }

    private void insertionSort(Excel excel, Double[][] doubleArray) throws IOException, InvalidFormatException {
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(doubleArray[exponent], limit, true, excel);

        }
        for (int exponent = 0, limit = 2; exponent != 21; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(doubleArray[exponent], limit, false, excel);
        }
    }

    private void insertionWithMoves(Excel excel, Double[][] doubleArray) {
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            InsertionWithMovesTestTime.performExperimentsFor(doubleArray[exponent], limit, true, excel);

        }
        for (int exponent = 0, limit = 2; exponent != 21; exponent++, limit *= 2) {
            InsertionWithMovesTestTime.performExperimentsFor(doubleArray[exponent], limit, false, excel);
        }
    }

    private void insertionWithMovesAndBinarySearch(Excel excel, Double[][] doubleArray) {
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            InsertionSortBinarySearchTestTime.performExperimentsFor(doubleArray[exponent], limit, true, excel);

        }
        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            InsertionSortBinarySearchTestTime.performExperimentsFor(doubleArray[exponent], limit, false, excel);

        }
    }

    public static void main(String[] args) {


    }
}


