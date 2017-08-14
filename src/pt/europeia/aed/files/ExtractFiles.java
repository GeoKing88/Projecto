package pt.europeia.aed.files;

import java.io.*;
import java.util.ArrayList;

public class ExtractFiles {

    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;
    private DataInputStream dis;

    private ArrayList<Double[]> dataFromFilesPartiallySorted = new ArrayList<>();
    private ArrayList<Double[]> dataFromFilesShuffle = new ArrayList<>();
    private ArrayList<Double[]> dataFromFilesSorted = new ArrayList<>();
    private ArrayList<Double[]> invertedPartiallySorted = new ArrayList<>();
    private ArrayList<Double[]> invertedSorted = new ArrayList<>();
    private final int MAX = 16777216;


    public ExtractFiles() throws IOException {
        extractData("Partially Sorted", "partially_sorted");
        extractData("Sorted", "sorted");
        extractData("Shuffle", "shuffled");
    }

    private void extractData(String directory, String fileName) throws IOException {
        try {
            for (int i = 2; i <= MAX; i *= 2) {
                file = new File("/home/geoking88/Documents/Necessito de Guardar/Estrutura de Dados e Algoritmos/" +
                        "TrabalhadorEstudante/Ficheiros/" + directory + "/" + fileName + "_" + i + ".txt");
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                dis = new DataInputStream(bis);

                Double[] data = new Double[i];
                for (int j = 0; j < i; j++) {
                    String stringData = dis.readLine();
                    data[j] = convertData(stringData);
                }
                fis.close();
                bis.close();
                dis.close();
                switch (directory) {
                    case "Partially Sorted":
                        dataFromFilesPartiallySorted.add(data);
                    case "Sorted":
                        dataFromFilesSorted.add(data);
                    case "Suffle":
                        dataFromFilesShuffle.add(data);
                }

            }
        } catch (
                FileNotFoundException e)

        {
            e.printStackTrace();
        }
    }

    private Double convertData(String stringData) {
        return Double.parseDouble(stringData);
    }

    public ArrayList<Double[]> partiallySorted() {
        return dataFromFilesPartiallySorted;
    }

    public ArrayList<Double[]> sorted() {
        return dataFromFilesSorted;
    }

    public ArrayList<Double[]> shuffle() {
        return dataFromFilesShuffle;
    }
    public ArrayList<Double[]> invertedPartiallySorted(){




        }
    }

}



