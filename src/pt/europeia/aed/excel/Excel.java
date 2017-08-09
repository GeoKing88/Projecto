package pt.europeia.aed.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Excel {

    private File file;
    private Workbook workbook = new XSSFWorkbook();
    private FileOutputStream out;
    private Map<Integer, Double[]> data = new TreeMap<>();
    private XSSFSheet spreadsheet;
    private int numberRow = data.size();

    /**
     * @param nameFile       - The name of the file that will contain the data.
     * @param nameFirstSheet - The name where will be the first sheet of the excel File
     *                       <p>
     *                       <p>
     *                       This method is the constructor of the class Excel. It receive two parameters: The nameFile and nameFirstSheet. Both are Strings.
     *                       This method will create a new Excel File and will try to open it.
     */
    public Excel(String nameFile, String nameFirstSheet) throws IOException, InvalidFormatException {
        file = new File("/home/geoking88/Documents/Necessito de Guardar/Estrutura de Dados e Algoritmos/TrabalhadorEstudante/Resultados/" + nameFile + ".xlsx");

        if (file.isFile() && file.exists()) {
            FileInputStream in = new FileInputStream(file);
            workbook = new XSSFWorkbook(in);
            spreadsheet = (XSSFSheet) workbook.getSheet(nameFirstSheet);
            System.out.print("\nThe file is open");
        } else {
            out = new FileOutputStream(file);
            spreadsheet = (XSSFSheet) workbook.createSheet(nameFirstSheet);
            System.out.print("\nFile was successfully created");
        }
    }


    public void writeDataTimes(int value, double median, double minimum, double repetition) {
        numberRow++;
        Double doubleOfData[] = {(double) value, median, minimum, repetition};
        data.put(numberRow, doubleOfData);
        Set<Integer> keyid = data.keySet();

        for (Integer key : keyid) {
            ArrayList<Double> doubleArray = new ArrayList<Double>(Arrays.asList(data.get(key)));
            XSSFRow row = spreadsheet.createRow(numberRow);
            int cellid = 0;
            for (Double string : doubleArray) {
                Cell cell = row.createCell(cellid);
                cell.setCellValue(string);
                cellid++;

            }
        }
    }

    /**
     * This method are for the close of the excel file. This must be used when all the data are written in the file at the end.
     * The user must use this method to not lose all the data.
     * s
     */

    public void close() throws IOException {
        out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        System.out.print("\nThe File closed sucessefully");

    }


}
