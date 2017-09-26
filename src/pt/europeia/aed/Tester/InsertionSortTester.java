package pt.europeia.aed.Tester;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.SortingTestTime.InsertionSortBinarySearchTestTime;
import pt.europeia.aed.SortingTestTime.InsertionSortTestTime;
import pt.europeia.aed.SortingTestTime.InsertionWithMovesTestTime;
import pt.europeia.aed.book.chapter2.section1.InsertionWithMoves;
import pt.europeia.aed.files.Excel;
import pt.europeia.aed.files.ExtractFilesGeneric;

import java.io.IOException;

public class InsertionSortTester {

    final int LIMIT = 20;

    public InsertionSortTester() throws IOException, InvalidFormatException {

        ExtractFilesGeneric extractFilesGeneric = new ExtractFilesGeneric(LIMIT);


        Excel excelInsertionSortPartiallySorted = new Excel("InsertionSortParSorted", "ParSorted");
        insertionSort(excelInsertionSortPartiallySorted, extractFilesGeneric.getDataFromFilesPartiallySorted());
        excelInsertionSortPartiallySorted.close();

        Excel excelInsertionSortShuffle = new Excel("InsertionSortSuffle", "Shuffle");
        insertionSort(excelInsertionSortShuffle, extractFilesGeneric.getSuffleArray());
        excelInsertionSortShuffle.close();

        Excel excelInsertionSortSorted = new Excel("InsertionSortSorted", "Sorted");
        insertionSort(excelInsertionSortSorted, extractFilesGeneric.getSortedArray());
        excelInsertionSortSorted.close();

        Excel excelInsertionSortInvParSorted = new Excel("InsertionSortInvParSorted", "InvParSorted");
        insertionSort(excelInsertionSortInvParSorted, extractFilesGeneric.getInvertedPartiallySorted());
        excelInsertionSortInvParSorted.close();

        Excel excelInsertionSortInverted = new Excel("InsertionSortInverted", "Inverted");
        insertionSort(excelInsertionSortInverted, extractFilesGeneric.getInvertedSorted());
        excelInsertionSortInverted.close();

        Excel excelInsertionSortWithMovesParSorted = new Excel("InsertionWithMovesParSor", "PartiallySorted");
        insertionWithMoves(excelInsertionSortWithMovesParSorted, extractFilesGeneric.getDataFromFilesPartiallySorted());
        excelInsertionSortWithMovesParSorted.close();

        Excel excelInsertionSortWithMovesShuffle = new Excel("InsertionWithMovesShuffle", "Shuffle");
        insertionWithMoves(excelInsertionSortWithMovesShuffle, extractFilesGeneric.getSuffleArray());
        excelInsertionSortWithMovesShuffle.close();

        Excel excelInsertionSortWithMovesSorted = new Excel("InsertionWithMovesSorted", "Sorted");
        insertionWithMoves(excelInsertionSortWithMovesSorted, extractFilesGeneric.getSortedArray());
        excelInsertionSortWithMovesSorted.close();

        Excel excelInsertionSortWithMovesInvPartSorted = new Excel("InsertionWithMovesIParSort", "PartInverted");
        insertionWithMoves(excelInsertionSortWithMovesInvPartSorted, extractFilesGeneric.getInvertedPartiallySorted());
        excelInsertionSortWithMovesInvPartSorted.close();

        Excel excelInsertionSortWithMovesInverted = new Excel("InsertionSortWithMovesInvSort", "InvertedSorted");
        insertionWithMoves(excelInsertionSortWithMovesInverted, extractFilesGeneric.getInvertedSorted());
        excelInsertionSortWithMovesInverted.close();

        Excel excelInsertionBSParSorted = new Excel("InsertionSortBSParSorted", "PartiallySorted");
        insertionWithMovesAndBinarySearch(excelInsertionBSParSorted, extractFilesGeneric.getDataFromFilesPartiallySorted());
        excelInsertionBSParSorted.close();

        Excel excelInsertionBSShuffle = new Excel("InsertionSortBSShuffle", "Shuffle");
        insertionWithMovesAndBinarySearch(excelInsertionBSShuffle, extractFilesGeneric.getSuffleArray());
        excelInsertionBSShuffle.close();

        Excel excelInsertionBSSorted = new Excel("InsertionSortBSSorted", "Sorted");
        insertionWithMovesAndBinarySearch(excelInsertionBSSorted, extractFilesGeneric.getSortedArray());
        excelInsertionBSSorted.close();

        Excel excelInsertionBSInvParSorted = new Excel("InsertionSortIParSort", "InvertedPartiallySorted");
        insertionWithMovesAndBinarySearch(excelInsertionBSInvParSorted, extractFilesGeneric.getInvertedPartiallySorted());
        excelInsertionBSInvParSorted.close();

        Excel excelInsertionBSInvertedSorted = new Excel("InsertionSortInverted", "InvertedSorted");
        insertionWithMovesAndBinarySearch(excelInsertionBSInvertedSorted, extractFilesGeneric.getInvertedSorted());
        excelInsertionBSInvertedSorted.close();


    }

    private void insertionSort(Excel excel, Double[][] doubleArray) {
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(doubleArray[exponent], true, limit, excel);

        }
        for (int exponent = 0, limit = 2; exponent != 21; exponent++, limit *= 2) {
            InsertionSortTestTime.performExperimentsFor(doubleArray[exponent], false, limit, excel);
        }
    }

    private void insertionWithMoves(Excel excel, Double[][] doubleArray) {
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            InsertionWithMovesTestTime.performExperimentsFor(doubleArray[exponent], true, limit, excel);

        }
        for (int exponent = 0, limit = 2; exponent != 21; exponent++, limit *= 2) {
            InsertionWithMovesTestTime.performExperimentsFor(doubleArray[exponent], false, limit, excel);
        }
    }

    private void insertionWithMovesAndBinarySearch(Excel excel, Double[][] doubleArray) {
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            InsertionSortBinarySearchTestTime.performExperimentsFor(doubleArray[exponent], true, limit, excel);

        }
        for (int exponent = 0, limit = 2; exponent != 21; exponent++, limit *= 2) {
            InsertionSortBinarySearchTestTime.performExperimentsFor(doubleArray[exponent], false, limit, excel);
        }
    }
/*
    public static void main(String[] args) {

    }
*/

}
