import java.io.*;
import java.util.*;

public class Main {

    // initiate variables
    static int dataSize = 100000;
    static int simulationTimes = 5;
    static String outputCSV = "rawResult.csv";
    static String[] dataSetNames = {"normalDistributionA.csv", "normalDistributionB.csv",
                                    "usTemperature.csv", "globalEnvironmentalTemperature.csv"};
    static float[][] dataSets = new float[dataSetNames.length][dataSize];
    static List<String> records;

    public static void main(String[] args) throws Exception {
        // fill data into dataSets
        for (int i = 0; i < dataSetNames.length; i++) {
            fillSet(i);
        }

        // create output CSV
        createCSV(outputCSV);

        // initiate objects
        InsertionSort insertionSort = new InsertionSort();
        SelectionSort selectionSort = new SelectionSort();
        BubbleSort bubbleSort = new BubbleSort();
        MergeSort mergeSort = new MergeSort();
        QuickSort quickSort = new QuickSort();

        // run 5 simulations for each sort; print results to csv
        for (int i = 1; i <= simulationTimes; i++) {
            writeToCSV("Simulation " + i);
            for (int j = 0; j < dataSetNames.length; j++) {
                StringBuilder output = new StringBuilder();
                output.append(dataSetNames[j] + "\n");
                
                records = insertionSort.sort(dataSets[j].clone());
                output.append("Insertion Sort - data processed " + i + "," + records.get(0) + "\n" + 
                              "Insertion Sort - sort degree " + i + "," + records.get(1) + "\n" + 
                              "Insertion Sort - runtime " + i + "," + records.get(2) + "\n" + 
                              "Insertion Sort - memory usage " + i + "," + records.get(3) + "\n" + "\n");

                records = selectionSort.sort(dataSets[j].clone());
                output.append("Selection Sort - data processed " + i + "," + records.get(0) + "\n" + 
                              "Selection Sort - sort degree " + i + "," + records.get(1) + "\n" + 
                              "Selection Sort - runtime " + i + "," + records.get(2) + "\n" + 
                              "Selection Sort - memory usage " + i + "," + records.get(3) + "\n" + "\n");

                records = bubbleSort.sort(dataSets[j].clone());
                output.append("Bubble Sort - data processed " + i + "," + records.get(0) + "\n" + 
                              "Bubble Sort - sort degree " + i + "," + records.get(1) + "\n" + 
                              "Bubble Sort - runtime " + i + "," + records.get(2) + "\n" + 
                              "Bubble Sort - memory usage " + i + "," + records.get(3) + "\n" + "\n");

                records = mergeSort.sort(dataSets[j].clone());
                output.append("Merge Sort - data processed " + i + "," + records.get(0) + "\n" + 
                              "Merge Sort - sort degree " + i + "," + records.get(1) + "\n" + 
                              "Merge Sort - runtime " + i + "," + records.get(2) + "\n" + 
                              "Merge Sort - memory usage " + i + "," + records.get(3) + "\n" + "\n");

                records = quickSort.sort(dataSets[j].clone());
                output.append("Quick Sort - data processed " + i + "," + records.get(0) + "\n" + 
                              "Quick Sort - sort degree " + i + "," + records.get(1) + "\n" + 
                              "Quick Sort - runtime " + i + "," + records.get(2) + "\n" + 
                              "Quick Sort - memory usage " + i + "," + records.get(3) + "\n" + "\n");
                
                writeToCSV(output.toString());
            }
        }
    }

    public static int fillSet(int a) {
        Scanner scanner;
        int cur = 0;
        try {
            scanner = new Scanner(new File(dataSetNames[a]));
            while (scanner.hasNext()) {
                dataSets[a][cur++] = Float.parseFloat(scanner.next());
            }
            scanner.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        return -1;
    }

    public static int createCSV(String s) {
        try {
            PrintWriter pw = new PrintWriter(new File(s));
            pw.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        return -1;
    }

    public static int writeToCSV(String s) {
        try {
            FileWriter fw = new FileWriter(outputCSV, true);
            fw.write(s);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
