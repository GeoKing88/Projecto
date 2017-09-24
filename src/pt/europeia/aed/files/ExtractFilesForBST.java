package pt.europeia.aed.files;

import java.io.IOException;

public class ExtractFilesForBST extends ExtractFilesGeneric {
    /**
     * The constructor of the class. They will do all the extraction and create the new arrays.
     *
     * @param exponent
     * @throws IOException The argument should be less or equal to 24 (16777216).
     */
    private Double[][] keyPartiallySorted;
    private Double[][] keyShuffle;
    private Double[][] keySorted;
    private Double[][] keyInvPartially;
    private Double[][] invertedSorted;


    public ExtractFilesForBST(int exponent) throws IOException {
        super(exponent);

    }


}
