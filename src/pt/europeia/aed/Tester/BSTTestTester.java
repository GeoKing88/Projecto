package pt.europeia.aed.Tester;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.BstTestTime.BstTestTimePut;
import pt.europeia.aed.files.Excel;
import pt.europeia.aed.files.ExtractFiles;


import java.io.IOException;

public class BSTTestTester {


    final int LIMIT = 20;

    public BSTTestTester() throws IOException, InvalidFormatException {
        ExtractFiles extractFilesGeneric2 = new ExtractFiles(LIMIT);


        Excel excelPutPartiallySorted = new Excel("PutPartiallySorted", "PartiallySorted");
        bstTesterPut(extractFilesGeneric2.getPartiallySorted(), excelPutPartiallySorted);
        excelPutPartiallySorted.close();

        Excel excelPutShuffle = new Excel("PutShuffle", "Shuffle");
        bstTesterPut(extractFilesGeneric2.getShuffle(), excelPutShuffle);
        excelPutShuffle.close();

        Excel excelPutSorted = new Excel("PutSorted", "Sorted");
        bstTesterPut(extractFilesGeneric2.getShuffle(), excelPutSorted);
        excelPutSorted.close();

        Excel excelPutInvPartiallySorted = new Excel("PutInvParSorted", "InvParSorted");
        bstTesterPut(extractFilesGeneric2.getInvParSorted(), excelPutInvPartiallySorted);
        excelPutInvPartiallySorted.close();

        Excel excelPutInvertedSorted = new Excel("PutInvSorted", "InvSorted");
        bstTesterPut(extractFilesGeneric2.getInvertedSorted(), excelPutInvertedSorted);
        excelPutInvertedSorted.close();

        Excel excelSearchPartiallySorted = new Excel("SearchPartiallySorted", "PartiallySorted");
        bstTesterPut(extractFilesGeneric2.getPartiallySorted(), excelSearchPartiallySorted);
        excelSearchPartiallySorted.close();

        Excel excelSearchShuffle = new Excel("SearchShuffle", "Shuffle");
        bstTesterPut(extractFilesGeneric2.getShuffle(), excelSearchShuffle);
        excelSearchShuffle.close();

        Excel excelSearchSorted = new Excel("SearchSorted", "Sorted");
        bstTesterPut(extractFilesGeneric2.getSorted(), excelSearchSorted);
        excelSearchSorted.close();

        Excel excelSearchInvParSorted = new Excel("SearchInvParSorted", "InvParSorted");
        bstTesterPut(extractFilesGeneric2.getInvertedSorted(), excelPutInvertedSorted);
        excelSearchInvParSorted.close();

        Excel excelSearchInvertedSorted = new Excel("SearchInvSorted", "InvertedSorted");
        bstTesterPut(extractFilesGeneric2.getInvertedSorted(), excelSearchInvertedSorted);
        excelSearchInvertedSorted.close();

        Excel excelDeletePartiallySorted = new Excel("DeletePartiallySorted", "PartiallySorted");
        bstTesterPut(extractFilesGeneric2.getPartiallySorted(), excelDeletePartiallySorted);
        excelDeletePartiallySorted.close();

        Excel excelDeleteShuffle = new Excel("DeleteShuffle", "Shuffle");
        bstTesterPut(extractFilesGeneric2.getShuffle(), excelDeleteShuffle);
        excelDeleteShuffle.close();

        Excel excelDeleteSorted = new Excel("DeleteSorted", "Sorted");
        bstTesterPut(extractFilesGeneric2.getSorted(), excelDeleteSorted);
        excelDeleteSorted.close();

        Excel excelDeleteInvParSorted = new Excel("DeleteInvParSorted", "InvParSorted");
        bstTesterPut(extractFilesGeneric2.getInvParSorted(), excelDeleteInvParSorted);
        excelDeleteInvParSorted.close();

        Excel excelDeleteInvSorted = new Excel("DeleteInvSorted", "InvSorted");
        bstTesterPut(extractFilesGeneric2.getInvertedSorted(), excelDeleteInvSorted);
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
