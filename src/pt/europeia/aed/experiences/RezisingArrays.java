package pt.europeia.aed.experiences;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import pt.europeia.aed.book.chapter1.section3.iterable.ResizingArrayQueue;
import pt.europeia.aed.files.Excel;

import java.io.IOException;

public class RezisingArrays {

    private static ResizingArrayQueue<String> resizingArrayQueue;
    private static Excel excel;

    public RezisingArrays() throws IOException, InvalidFormatException {
        excel = new Excel("ResizingArray", "Resizing Arrays");
        warmup();
        testEnqueue();
        testDequeue();
        excel.close();
    }

    private static void warmup() {

        for (int exponent = 0, limit = 1; exponent != 8; exponent++, limit *= 2) {
            final int size = limit;
            TesterTimes.performExperimentsFor(() -> enqueueResizingArray(size), true, size, excel);
        }
    }

    private static void testEnqueue() {
        for (int exponent = 0, limit = 1; exponent != 25; exponent++, limit *= 2) {
            final int sizeBest = limit;
            final int sizeWorst = limit + 1;
            TesterTimes.performExperimentsFor(() -> enqueueResizingArray(sizeBest), false, sizeBest, excel);
            TesterTimes.performExperimentsFor(() -> enqueueResizingArray(sizeWorst), false, sizeWorst, excel);
        }

    }

    private static void testDequeue() {
        for (int exponent = 0, limit = 1; exponent != 25; exponent++, limit *= 2) {
            enqueueResizingArray(limit);
            final int size = limit;
            TesterTimes.performExperimentsFor(() -> dequeueResizingArray(), false, size, excel);
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

    public static void main(String args[]) throws IOException, InvalidFormatException {
        RezisingArrays rezisingArrays = new RezisingArrays();
    }
}
