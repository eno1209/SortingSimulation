//import java.io.*;
import java.util.*;

public class MergeSort {
    // initiate variables
    int length, count, check;
    int gap = 100;
    long startTime;
    double noninversion, inversion;
    Random rand;
    StringBuilder dataProcessed, sortDegree;
    StringBuilder runTime, memoUsg;
    
    // Constructor
    public MergeSort() {

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

    public void sortHelper(float[] dataSet, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;

            sortHelper(dataSet, l, mid);
            sortHelper(dataSet, mid + 1, r);

            merge(dataSet, l, mid, r);

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

    public void merge(float[] dataSet, int l, int mid, int r) {
        // initiate left and right arrays
        float[] leftSet = new float[mid - l + 1];
        float[] rightSet = new float[r - mid];
        for (int i = 0; i < leftSet.length; i++) leftSet[i] = dataSet[l + i];
        for (int j = 0; j < rightSet.length; j++) rightSet[j] = dataSet[mid + 1 + j];

        // merge the two arrays
        int i = 0, j = 0, k = l;
        while (i < leftSet.length && j < rightSet.length) {
            if (leftSet[i] <= rightSet[j]) dataSet[k++] = leftSet[i++];
            else dataSet[k++] = rightSet[j++];
        }

        // copy the remaining elements in left and right arrays
        while (i < leftSet.length) dataSet[k++] = leftSet[i++];
        while (j < rightSet.length) dataSet[k++] = rightSet[j++];
    }

}