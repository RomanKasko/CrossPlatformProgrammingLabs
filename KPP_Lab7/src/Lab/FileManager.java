package Lab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FileManager {
    public static Integer[][] getMatrix() {
        int max = maxValue();
        List<Integer> firNodes= readFirstNodes();
        List<Integer> secNodes= readSecondNodes();
        List<Integer> weight= readNodesWeight();

        Integer[][] resMatr = new Integer[max+1][max+1];
        for (int i = 0; i <= max ; i++) {
            for (int j = 0; j <= max; j++) {
                resMatr[i][j]=0;
            }
        }

        for (int i = 0; i < weight.size(); i++) {
            resMatr[firNodes.get(i)][secNodes.get(i)] = weight.get(i);
            resMatr[secNodes.get(i)][firNodes.get(i)] = weight.get(i);
        }

        System.out.println("Matrix from list of adjacency's:");
        for (int i = 0; i <= max ; i++){
            for (int j = 0; j <= max; j++){
                System.out.print(resMatr[i][j] + " ");
            }
            System.out.println("\n");
        }
    return resMatr;
    }

    public static Integer maxValue(){
    List<Integer> list = readFirstNodes();
    list.addAll(readSecondNodes());
    return list.stream().mapToInt(x->x).max().orElseThrow(NoSuchElementException::new);
   }

    public static List<Integer> readFirstNodes(){
        List<Integer> node= new ArrayList<>();
        BufferedReader reader;

        try {
            File file = new File("src/graphs");
            reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(" ");
                String temp = parts[0].trim();

                if (!temp.equals("")) {
                    node.add(Integer.valueOf(temp));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    public static List<Integer> readSecondNodes(){
        List<Integer> Edge= new ArrayList<>();
        BufferedReader br;
        try {
            File file = new File("src/graphs");
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String temp = (parts[1].trim());
                if (!temp.equals("")) Edge.add(Integer.valueOf(temp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Edge;
    }

    public static List<Integer> readNodesWeight(){
        List<Integer> weight= new ArrayList<>();
        BufferedReader br;
        try {
            File file = new File("src/graphs");
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String temp = parts[2].trim();
                if (!temp.equals("")) weight.add(Integer.valueOf(temp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weight;
    }
}
