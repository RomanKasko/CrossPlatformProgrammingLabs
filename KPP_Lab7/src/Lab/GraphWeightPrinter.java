package Lab;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GraphWeightPrinter {
    int n;
    Integer[][] matr;

    public GraphWeightPrinter(Integer[][] matrix){
        matr = matrix;
        n = FileManager.maxValue() + 1;
    }

    public void findWeightInMST(JTextArea jTextArea, PrimaMethod prima, int ThreadCount) throws InterruptedException {
        List<Double> weight = prima.weight;
        List<Integer> indexes = new ArrayList<>();
        Double sum = 0.0;

        if (ThreadCount > weight.size()) {
            Monitor mon = new Monitor(weight, 0, weight.size(), 1);
            mon.start();
            Thread.sleep(150);
            jTextArea.append(mon.threadInfo);
            sum = mon.sum;
        } else if (weight.size() % ThreadCount == 0) {
            for (int i = 0; i < weight.size(); i += ((weight.size() * 1.0) / ThreadCount)) {
                indexes.add(i);
            }

            for (int i = 0; i < indexes.size(); i++) {
                Monitor b = new Monitor(weight, i * weight.size()/ThreadCount, (i + 1) * weight.size()/ThreadCount, i + 1);
                b.start();
                Thread.sleep(150);
                jTextArea.append(b.threadInfo);
                sum+= b.sum;
            }
        } else {
            for (double i = 0; i < weight.size(); i += ((weight.size() * 1.0) / ThreadCount)) {
                indexes.add((int) i);
            }

            for (int i = 0; i < indexes.size() - 1; i++) {
                Monitor b = new Monitor(weight, i * weight.size()/ThreadCount, (i + 1) * weight.size()/ThreadCount, i + 1);
                b.start();
                Thread.sleep(150);
                jTextArea.append(b.threadInfo);
                sum+= b.sum;
            }

            Monitor b = new Monitor(weight, indexes.get(indexes.size() - 1), weight.size(), indexes.size());
            b.start();
            Thread.sleep(150);
            jTextArea.append(b.threadInfo);
            sum += b.sum;
        }
        System.out.println("MST(minimum spanning tree) weight: " + sum);
    }
}