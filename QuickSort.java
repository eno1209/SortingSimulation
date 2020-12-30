//import java.io.*;
import java.util.*;

public class QuickSort {
    // initiate variables
    int length, count, check;
    int gap = 100;
    long startTime;
    double noninversion, inversion;
    Random rand;
    StringBuilder dataProcessed, sortDegree;
    StringBuilder runTime, memoUsg;
    
    // Constructor
    public QuickSort() {

    }

    /**
     * Insertion Sort method.
     * @param dataSet a unsorted data set
     * @return a list of Strings containing data processed, sort degree, runtime and memory usage.
     */
    public List<String> sort(float[] dataSet) {
        // initiate variables
        length = dataSet.length; count = 0; check = 0;
        rand = new Random();
        dataProcessed = new StringBuilder(); sortDegree = new StringBuilder();
        runTime = new StringBuilder(); memoUsg = new StringBuilder();
        
        // get the initial run time
        startTime = System.nanoTime();

        // sort and record
        sortHelper(dataSet, 0, length - 1);

        return new ArrayList<>(Arrays.asList(dataProcessed.toString(), sortDegree.toString(),
                                runTime.toString(), memoUsg.toString()));
    }

    public void sortHelper(float[] dataSet, int lo, int hi) {
        if (lo < hi) {
            int pivot = partition(dataSet, lo, hi);

            sortHelper(dataSet, lo, pivot - 1);
            sortHelper(dataSet, pivot + 1, hi);

            // check runtime and memory usage
            check++; count++;
            if (check % (length / gap) == 0) {
                runTime.append((System.nanoTime() - startTime) + ",");
                System.gc();
                memoUsg.append((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + ",");
    
                dataProcessed.append(count + ",");
    
                noninversion = 0; inversion = 0;
                for (int k = 0; k < length / 10; k++) {
                    int p = rand.nextInt(length);
                    int q = rand.nextInt(length);
                    if (p < q) {
                        if (dataSet[p] > dataSet[q]) inversion++;
                        else noninversion++;
                    }
                    if (p > q) {
                        if (dataSet[p] < dataSet[q]) inversion++;
                        else noninversion++;
                    }
                }
                sortDegree.append((noninversion / inversion) + ",");
            }
        }
    }

    public int partition(float[] dataSet, int lo, int hi) {
        float pivot = dataSet[hi];
        int i = lo - 1;

        for (int j = lo; j < hi; j++) {
            if (dataSet[j] < pivot) {
                i++;

                float temp = dataSet[i];
                dataSet[i] = dataSet[j];
                dataSet[j] = temp;
            }
        }

        float temp = dataSet[i + 1];
        dataSet[i + 1] = dataSet[hi];
        dataSet[hi] = temp;

        return i + 1;
    }

}