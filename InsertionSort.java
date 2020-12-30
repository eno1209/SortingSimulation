//import java.io.*;
import java.util.*;

public class InsertionSort {
    // initiate variables
    int length, check;
    int gap = 100;
    long startTime;
    double noninversion, inversion;
    Random rand;
    StringBuilder dataProcessed, sortDegree;
    StringBuilder runTime, memoUsg;

    // Constructor
    public InsertionSort() {

    }

    // test
    /*public static void main(String[] args) {
        int[] dataSet = {5,4,3,2,1};
        sort(dataSet);
        for (int i = 0; i < dataSet.length; i++) {
            System.out.print(dataSet[i] + " ");
        }
    }*/

    /**
     * Insertion Sort method.
     * @param dataSet a unsorted data set
     * @return a list of Strings containing data processed, sort degree, runtime and memory usage.
     */
    public List<String> sort(float[] dataSet) {
        // initiate variables
        length = dataSet.length; check = 0;
        rand = new Random();
        dataProcessed = new StringBuilder(); sortDegree = new StringBuilder();
        runTime = new StringBuilder(); memoUsg = new StringBuilder();
        
        // get the initial run time
        startTime = System.nanoTime();

        // sort and record
        for (int i = 1; i < length; i++) {
            // Main Sort
            int j = i - 1;
            float pivot = dataSet[i];
            while (j >= 0 && dataSet[j] > pivot) {
                dataSet[j + 1] = dataSet[j];
                j--;
            }
            dataSet[j + 1] = pivot;

            // check runtime and memory usage
            check++;
            if (check % (length / gap) == 0) {
                runTime.append((System.nanoTime() - startTime) + ",");
                System.gc();
                memoUsg.append((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + ",");
    
                dataProcessed.append(i + ",");
    
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

        return new ArrayList<>(Arrays.asList(dataProcessed.toString(), sortDegree.toString(),
                                runTime.toString(), memoUsg.toString()));
    }

}