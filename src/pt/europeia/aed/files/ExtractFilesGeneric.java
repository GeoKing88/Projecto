package pt.europeia.aed.files;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class extract the data from the provided files. The goal is to use this data in future BstTestTime.
 * The files have order, random and Partially order data.
 * In this class, the data is converted in double arrays who store double array (Two-dimensional arrays).
 * Besides that, it create more two arrays, inverted order array and inverted partially order array. They are create base on the original data files.
 */

public class ExtractFilesGeneric {


    private Double[][] dataFromFilesPartiallySorted;
    private Double[][] dataFromFilesShuffle;
    private Double[][] dataFromFilesSorted;
    private Double[][] invertedPartiallySorted;
    private Double[][] invertedSorted;


    /**
     * The maximum data the client desire.
     */
    private int MAX;

    /**
     * The constructor of the class. They will do all the extraction and create the new arrays.
     *
     * @throws IOException The argument should be less or equal to 24 (16777216).
     */
    public ExtractFilesGeneric(int exponent) throws IOException {


        dataFromFilesPartiallySorted = new Double[exponent][];
        dataFromFilesShuffle = new Double[exponent][];
        dataFromFilesSorted = new Double[exponent][];
        invertedPartiallySorted = new Double[exponent][];
        invertedSorted = new Double[exponent][];

        if (exponent > 24) {
            throw new IllegalArgumentException("The exponent should be less or equal to 24");
        }
        MAX = 1 << exponent;

        extractData("Sorted", "sorted");
        extractData("Shuffle", "shuffled");
        extractData("Partially Sorted", "partially_sorted_");
    }

    /**
     * This class is responsible for extract the data from files and stored in doubles arrays with double arrays.
     * Beside that, it create inverted order double array and inverted partially sorted double array.
     *
     * @param directory Name of the directory where the file is locate.
     * @param fileName  Name of the file
     * @throws IOException
     */
    private void extractData(String directory, String fileName) throws IOException {

        FileInputStream fis;
        BufferedInputStream bis;
        DataInputStream dis;

        if (!directory.equals("Partially Sorted")) {
            String file = "/home/geoking88/Documents/Necessito de Guardar/Estrutura de Dados e Algoritmos/TrabalhadorEstudante/Ficheiros/" + directory + "/" + fileName + "_" + MAX + ".txt";

            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            Double[] data = createMaxArray(dis, MAX);

            switch (directory) {
                case "Sorted":
                    dataFromFilesSorted = createArray(data);
                    dataFromFilesSorted[19] = data;
                    invertedSorted = invertedArray(dataFromFilesSorted);
                case "Shuffle":
                    dataFromFilesShuffle = createArray(data);
                    dataFromFilesShuffle[19] = data;
            }
            fis.close();
            bis.close();
            dis.close();
        } else {

            for (int i = 2; i <= MAX; i *= 2) {

                String file = "/home/geoking88/Documents/Necessito de Guardar/Estrutura de Dados e Algoritmos/TrabalhadorEstudante/Ficheiros/Partially Sorted/" + fileName + i + ".txt";

                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                dis = new DataInputStream(bis);

                Double[] data = createMaxArray(dis, i);

                dataFromFilesPartiallySorted = createArray(data);
                dataFromFilesPartiallySorted[19] = data;
                invertedPartiallySorted = invertedArray(dataFromFilesPartiallySorted);
                fis.close();
                bis.close();
                dis.close();
            }
        }
    }

    /**
     * Create a double array with most data length.
     */
    private Double[] createMaxArray(DataInputStream dis, int limit) throws IOException {
        Double[] data = new Double[MAX];
        for (int i = 0; i < limit; i++) {
            String stringData = dis.readLine();
            data[i] = Double.parseDouble(stringData);
        }
        return data;
    }

    /**
     * Create the new Two-dimensional double array and put the data on it.
     */
    private Double[][] createArray(Double[] array) {

        int index = 18;
        Double[][] dataFromFiles = new Double[20][];
        for (int i = MAX / 2; i > 1; i /= 2) {
            Double[] newArray = new Double[i];
            System.arraycopy(array, 0, newArray, 0, newArray.length);
            dataFromFiles[index--] = newArray;
        }
        return dataFromFiles;
    }

    /**
     * Create the new Two-dimensional double array in inverted order and put the data on it.
     */

    private Double[][] invertedArray(Double[][] array) {
        for (int i = 0; i < array.length; i++) {
            Double[] data = new Double[array[i].length];
            int index = 0;
            for (int j = array[i].length - 1; j >= 0; j--) {
                data[index++] = array[i][j];
            }
            array[i] = data;
        }
        return array;
    }

    /**
     * Get the Sorted array.
     */
    public Double[][] getSortedArray() {
        return dataFromFilesSorted;
    }

    /**
     * Get the suffle Array
     */
    public Double[][] getSuffleArray() {
        return dataFromFilesShuffle;
    }

    /**
     * Get the Inverted Sorted array
     */
    public Double[][] getInvertedSorted() {
        return invertedSorted;
    }

    /**
     * get the partially Sorted array
     */

    public Double[][] getDataFromFilesPartiallySorted() {
        return dataFromFilesPartiallySorted;
    }

    /**
     * Get the inverted  partially Sorted array
     */
    public Double[][] getInvertedPartiallySorted() {
        return invertedPartiallySorted;
    }


}







