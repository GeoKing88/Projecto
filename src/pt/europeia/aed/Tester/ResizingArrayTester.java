package pt.europeia.aed.Tester;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.SortingTestTime.ResizingArrayTestTime;
import pt.europeia.aed.book.chapter1.section3.iterable.ResizingArrayQueue;
import pt.europeia.aed.files.Excel;

import java.io.IOException;

public class ResizingArrayTester {

    private static ResizingArrayQueue<String> resizingArrayQueue;


    public ResizingArrayTester() throws IOException, InvalidFormatException {
        Excel excelResizingArray = new Excel("ResizingArray", "Resizing Arrays");
        warmup(excelResizingArray);
        testEnqueue(excelResizingArray);
        testDequeue(excelResizingArray);
        excelResizingArray.close();
    }


    private static void warmup(Excel excel) {

        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2) {
            final int size = limit;
            ResizingArrayTestTime.performExperimentsFor(() -> enqueueResizingArray(size), true, size, excel);
        }
    }

    private static void testEnqueue(Excel excel) {
        for (int exponent = 0, limit = 1; exponent != 25; exponent++, limit *= 2) {
            final int sizeBest = limit;
            final int sizeWorst = limit + 1;
            ResizingArrayTestTime.performExperimentsFor(() -> enqueueResizingArray(sizeBest), false, sizeBest, excel);
            ResizingArrayTestTime.performExperimentsFor(() -> enqueueResizingArray(sizeWorst), false, sizeWorst, excel);
        }

    }

    private static void testDequeue(Excel excel) {
        for (int exponent = 0, limit = 1; exponent != 25; exponent++, limit *= 2) {
            enqueueResizingArray(limit);
            final int size = limit;
            ResizingArrayTestTime.performExperimentsFor(() -> dequeueResizingArray(), false, size, excel);
        }
    }

    private static void dequeueResizingArray() {
        ResizingArrayQueue<String> resizingArrayQueueClone = new ResizingArrayQueue<>(resizingArrayQueue);
        while (resizingArrayQueueClone.size() != 0) {
            resizingArrayQueueClone.dequeue();
        }
    }

    private static void enqueueResizingArray(final int limit) {
        resizingArrayQueue = new ResizingArrayQueue<>();
        for (int i = 0; i < limit; i++) {
            resizingArrayQueue.enqueue("Benfica");
        }


    }

   /* public static void main(String[] args) throws IOException, InvalidFormatException {
        ResizingArrayTester main = new ResizingArrayTester();
    }**/

}