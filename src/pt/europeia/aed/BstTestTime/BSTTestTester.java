package pt.europeia.aed.BstTestTime;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public class BSTTestTester {

    /*
    final int LIMIT = 20;

    public BSTTestTester() throws IOException, InvalidFormatException {
        ExtractFilesGeneric extractFilesGeneric = new ExtractFilesGeneric(LIMIT);

        Excel excelBSTPutPartiallySortedPut = new Excel("BSTPutPartiallySortedPut", "PartiallySorted");
        bstTester(extractFilesGeneric.getDataFromFilesPartiallySorted(), excelBSTPutPartiallySortedPut, BSTMethod.put);
        excelBSTPutPartiallySortedPut.close();

        Excel excelBSTPUTShufflePut = new Excel("BSTPutSufflePut", "Shuflle");
        bstTester(extractFilesGeneric.getSuffleArray(), excelBSTPUTShufflePut, BSTMethod.put);
        excelBSTPUTShufflePut.close();

        Excel excelBSTSortedPut = new Excel("BSTSortedPut", "Sorted");
        bstTester(extractFilesGeneric.getSortedArray(), excelBSTPUTShufflePut, BSTMethod.put);
        excelBSTSortedPut.close();

        Excel excelBSTInvParSortedPut = new Excel("BSTInvParSortedPut", "InvertedParSorted");
        bstTester(extractFilesGeneric.getInvertedPartiallySorted(), excelBSTInvParSortedPut, BSTMethod.put);
        excelBSTInvParSortedPut.close();

        Excel excelBSTInvertedSortedPut = new Excel("BSTInvertedSortedPut", "InvertedSorted");
        bstTester(extractFilesGeneric.getInvertedSorted(), excelBSTInvertedSortedPut, BSTMethod.put);
        excelBSTInvertedSortedPut.close();


        Excel excelBSTPartiallySortedSearch = new Excel("BSTPartiallySortedSearch", "PartiallySorted");
        bstTester(extractFilesGeneric.getDataFromFilesPartiallySorted(), excelBSTPartiallySortedSearch, BSTMethod.search);
        excelBSTPartiallySortedSearch.close();

        Excel excelBSTShuffleSearch = new Excel("BstShuffleSearch", "Shuffle");
        bstTester(extractFilesGeneric.getSuffleArray(), excelBSTShuffleSearch, BSTMethod.search);
        excelBSTShuffleSearch.close();

        Excel excelBstSortedSearch = new Excel("BstSortedSearch", "SortedPut");
        bstTester(extractFilesGeneric.getSortedArray(), excelBstSortedSearch, BSTMethod.search);
        excelBstSortedSearch.close();

        Excel excelBstInvPartiallySorted = new Excel("InvParSortedSearch", "InvParSorted");
        bstTester(extractFilesGeneric.getInvertedPartiallySorted(), excelBstInvPartiallySorted, BSTMethod.search);
        excelBstInvPartiallySorted.close();

        Excel excelBstInvertedSortedSearch = new Excel("InvertedSortedSearch", "InvertedSorted");
        bstTester(extractFilesGeneric.getInvertedSorted(), excelBstInvertedSortedSearch, BSTMethod.search);
        excelBstInvertedSortedSearch.close();

        Excel excelBstPartiallySortedSearchAll = new Excel("PartiallySortedSearchAll", "PartiallySorted");
        bstTester(extractFilesGeneric.getDataFromFilesPartiallySorted(), excelBstPartiallySortedSearchAll, BSTMethod.searchAll);
        excelBstPartiallySortedSearchAll.close();

        Excel excelBstShuffleSearchAll = new Excel("ShuffleSearchAll", "shuflle");
        bstTester(extractFilesGeneric.getSuffleArray(), excelBSTShuffleSearch, BSTMethod.searchAll);
        excelBstShuffleSearchAll.close();

        Excel excelBstSortedSearchAll = new Excel("SortedSearchAll", "Sorted");
        bstTester(extractFilesGeneric.getSortedArray(), excelBstSortedSearchAll, BSTMethod.searchAll);
        excelBstSortedSearchAll.close();

        Excel excelBstInvParSortedSearchAll = new Excel("InvertedParSortedSearchAll", "InvertedParSearch");
        bstTester(extractFilesGeneric.getInvertedPartiallySorted(), excelBstInvParSortedSearchAll, BSTMethod.searchAll);
        excelBstInvParSortedSearchAll.close();

        Excel excelBstInvertedSortedSearchAll = new Excel("InvertedSortedSearchAll", "InvertedSorted");
        bstTester(extractFilesGeneric.getInvertedSorted(), excelBstInvertedSortedSearchAll, BSTMethod.searchAll);
        excelBstInvertedSortedSearchAll.close();
    }

    private static void bstTester(Double[][] doubleArray, Excel excel, BSTMethod method) throws IOException {

        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2) {
            BSTTestTester.performExperimentsFor(doubleArray[exponent], true, limit, excel, method);
        }

        for (int exponent = 0, limit = 1; exponent != 20; exponent++, limit *= 2) {
            BSTTestTester.performExperimentsFor(doubleArray[exponent], false, limit, excel, method);
        }
    }
**/


    public static void main(String[] args) throws IOException, InvalidFormatException {
        BSTTestTester bstTester = new BSTTestTester();
    }

}
