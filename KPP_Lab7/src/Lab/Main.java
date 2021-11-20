package Lab;

import javax.swing.*;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Enter number of threads: ");
        int threadCounter = new Scanner(System.in).nextInt();

        long startTimer = System.currentTimeMillis();
        GraphWeightPrinter resMatrix = new GraphWeightPrinter(FileManager.getMatrix());

        PrimaMethod primaMethod = new PrimaMethod(resMatrix.n);
        Map<Integer, Map<Integer,Double>> resMap = primaMethod.processPrimaMST(resMatrix.matr);
        //System.out.println(resMap);

        JTextArea jTextArea = new JTextArea("  ");
        showUI(jTextArea);

        resMatrix.findWeightInMST(jTextArea,primaMethod,threadCounter);

        long finishTimer = System.currentTimeMillis();
        long elapsedTime = finishTimer - startTimer;
        System.out.println("Elapsed time: "+ elapsedTime + " millis");
    }

    public static void showUI(JTextArea jTextArea){
        JFrame frame = new JFrame();
        frame.setTitle("Prima Method");
        frame.setBounds(100, 100, 100, 100);

        jTextArea.setBounds(40, 40, 100, 100);
        jTextArea.setSize(500, 500);

        frame.add(jTextArea);

        jTextArea.setText("Threads processing...\n");
        jTextArea.getScrollableTracksViewportHeight();

        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
