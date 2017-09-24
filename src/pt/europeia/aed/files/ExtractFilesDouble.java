package pt.europeia.aed.files;

import java.io.*;

/**
 * This class extract the data from the provided files. The goal is to use this data in future BstTestTime.
 * The files have order, random and Partially order data.
 * In this class, the data is converted in double arrays who store double array (Two-dimensional arrays).
 * Besides that, it create more two arrays, inverted order array and inverted partially order array. They are create base on the original data files.
 */

public class ExtractFilesDouble {


    private double[][] dataFromFilesPartiallySorted = new double[24][];
    private double[][] dataFromFilesShuffle = new double[24][];
    private double[][] dataFromFilesSorted = new double[24][];
    private double[][] invertedPartiallySorted = new double[24][];
    private double[][] invertedSorted = new double[24][];

    /**
     * The last file have 16777216 numbers, the equivilant of 2^30.
     */
    private final int MAX = 16777216;

    /**
     * The constructor of the class. They will do all the extraction and create the new arrays.
     *
     * @throws IOException
     */
    public ExtractFilesDouble() throws IOException {

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
            String file = "/home/geoking88/Documents/Necessito de Guardar/Estrutura de Dados e Algoritmos/TrabalhadorEstudante/Ficheiros/" + directory + "/" + fileName + "_16777216.txt";

            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            double[] data = createMaxArray(dis, MAX);

            switch (directory) {
                case "Sorted":
                    dataFromFilesSorted = createArray(data);
                    dataFromFilesSorted[23] = data;
                    invertedSorted = invertedArray(dataFromFilesSorted);
                case "Shuffle":
                    dataFromFilesShuffle = createArray(data);
                    dataFromFilesShuffle[23] = data;
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

                double[] data = createMaxArray(dis, i);

                dataFromFilesPartiallySorted = createArray(data);
                dataFromFilesPartiallySorted[23] = data;
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
    private double[] createMaxArray(DataInputStream dis, int limit) throws IOException {
        double[] data = new double[MAX];
        for (int i = 0; i < limit; i++) {
            String stringData = dis.readLine();
            data[i] = Double.parseDouble(stringData);
        }
        return data;
    }

    /**
     * Create the new Two-dimensional double array and put the data on it.
     */
    private double[][] createArray(double[] array) {

        int index = 22;
        double[][] dataFromFiles = new double[24][];
        for (int i = MAX / 2; i > 1; i /= 2) {
            double[] newArray = new double[i];
            System.arraycopy(array, 0, newArray, 0, newArray.length);
            dataFromFiles[index--] = newArray;
        }
        return dataFromFiles;
    }

    /**
     * Create the new Two-dimensional double array in inverted order and put the data on it.
     */

    private double[][] invertedArray(double[][] array) {
        for (int i = 0; i < array.length; i++) {
            double[] data = new double[array[i].length];
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
    public double[][] getSortedArray() {
        return dataFromFilesSorted;
    }

    /**
     * Get the suffle Array
     */
    public double[][] getSuffleArray() {
        return dataFromFilesShuffle;
    }

    /**
     * Get the Inverted Sorted array
     */
    public double[][] getInvertedSorted() {
        return invertedSorted;
    }

    /**
     * get the partially Sorted array
     */

    public double[][] getDataFromFilesPartiallySorted() {
        return dataFromFilesPartiallySorted;
    }

    /**
     * Get the inverted  partially Sorted array
     */
    public double[][] getInvertedPartiallySorted() {
        return invertedPartiallySorted;
    }


}







