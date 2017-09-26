package pt.europeia.aed.Tester;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.BstTestTime.BstTestTimePut;
import pt.europeia.aed.files.Excel;
import pt.europeia.aed.files.ExtractFilesGeneric;

import java.io.IOException;

public class BSTTestTester {


    final int LIMIT = 20;

    public BSTTestTester() throws IOException, InvalidFormatException {
        ExtractFilesGeneric extractFilesGeneric = new ExtractFilesGeneric(LIMIT);


        Excel excelPutPartiallySorted = new Excel("PutPartiallySorted", "PartiallySorted");
        bstTesterPut(extractFilesGeneric.getDataFromFilesPartiallySorted(), excelPutPartiallySorted);
        excelPutPartiallySorted.close();

        Excel excelPutShuffle = new Excel("PutShuffle", "Shuffle");
        bstTesterPut(extractFilesGeneric.getSuffleArray(), excelPutShuffle);
        excelPutShuffle.close();

        Excel excelPutSorted = new Excel("PutSorted", "Sorted");
        bstTesterPut(extractFilesGeneric.getSortedArray(), excelPutSorted);
        excelPutSorted.close();

        Excel excelPutInvPartiallySorted = new Excel("PutInvParSorted", "InvParSorted");
        bstTesterPut(extractFilesGeneric.getInvertedPartiallySorted(), excelPutInvPartiallySorted);
        excelPutInvPartiallySorted.close();

        Excel excelPutInvertedSorted = new Excel("PutInvSorted", "InvSorted");
        bstTesterPut(extractFilesGeneric.getInvertedSorted(), excelPutInvertedSorted);
        excelPutInvertedSorted.close();

        Excel excelSearchPartiallySorted = new Excel("SearchPartiallySorted", "PartiallySorted");
        bstTesterPut(extractFilesGeneric.getDataFromFilesPartiallySorted(), excelSearchPartiallySorted);
        excelSearchPartiallySorted.close();

        Excel excelSearchShuffle = new Excel("SearchShuffle", "Shuffle");
        bstTesterPut(extractFilesGeneric.getSuffleArray(), excelSearchShuffle);
        excelSearchShuffle.close();

        Excel excelSearchSorted = new Excel("SearchSorted", "Sorted");
        bstTesterPut(extractFilesGeneric.getSortedArray(), excelSearchSorted);
        excelSearchSorted.close();

        Excel excelSearchInvParSorted = new Excel("SearchInvParSorted", "InvParSorted");
        bstTesterPut(extractFilesGeneric.getInvertedSorted(), excelPutInvertedSorted);
        excelSearchInvParSorted.close();

        Excel excelSearchInvertedSorted = new Excel("SearchInvSorted", "InvertedSorted");
        bstTesterPut(extractFilesGeneric.getInvertedSorted(), excelSearchInvertedSorted);
        excelSearchInvertedSorted.close();

        Excel excelDeletePartiallySorted = new Excel("DeletePartiallySorted", "PartiallySorted");
        bstTesterPut(extractFilesGeneric.getDataFromFilesPartiallySorted(), excelDeletePartiallySorted);
        excelDeletePartiallySorted.close();

        Excel excelDeleteShuffle = new Excel("DeleteShuffle", "Shuffle");
        bstTesterPut(extractFilesGeneric.getSuffleArray(), excelDeleteShuffle);
        excelDeleteShuffle.close();

        Excel excelDeleteSorted = new Excel("DeleteSorted", "Sorted");
        bstTesterPut(extractFilesGeneric.getSortedArray(), excelDeleteSorted);
        excelDeleteSorted.close();

        Excel excelDeleteInvParSorted = new Excel("DeleteInvParSorted", "InvParSorted");
        bstTesterPut(extractFilesGeneric.getInvertedPartiallySorted(), excelDeleteInvParSorted);
        excelDeleteInvParSorted.close();

        Excel excelDeleteInvSorted = new Excel("DeleteInvSorted", "InvSorted");
        bstTesterPut(extractFilesGeneric.getInvertedSorted(), excelDeleteInvSorted);
        excelDeleteInvSorted.close();

    }

    private void bstTesterPut(Double[][] arraysForPut, Excel excel) {
        for (int exponent = 0, limit = 2; exponent != 8; exponent++, limit *= 2) {
            BstTestTimePut.performanceExperimentsForPut(arraysForPut[exponent], true, limit, excel);
        }
        for (int exponent = 0, limit = 2; exponent != 20; exponent++, limit *= 2) {
            BstTestTimePut.performanceExperimentsForPut(arraysForPut[exponent], false, limit, excel);
        }
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        BSTTestTester bstTester = new BSTTestTester();
    }

}
