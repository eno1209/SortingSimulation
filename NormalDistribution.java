import java.io.*;
import java.util.*;

public class NormalDistribution {

    // initiate variables
    static int dataSize = 100000;

    public static void main(String[] args) {
        genND("normalDistributionA.csv", dataSize);
        genND("normalDistributionB.csv", dataSize);
    }

    public static int genND(String s, int length) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            sb.append(rand.nextGaussian() + "\n");
        }

        try {
            PrintWriter pw = new PrintWriter(new File(s));
            pw.write(sb.toString());
            pw.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        return -1;
    }
}