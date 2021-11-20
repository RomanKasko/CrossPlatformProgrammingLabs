package Lab;

import java.util.*;

class PrimaMethod {
    private static int number;
    public List<Double> weight = new ArrayList<>();

    public PrimaMethod(int number){
        PrimaMethod.number = number;
    }

    Map<Integer, Map<Integer, Double>> processPrimaMST(Integer[][] graph) {
        int[] resMST = new int[number];
        int[] key = new int[number];
        Boolean[] mstSet = new Boolean[number];

        for (int i = 0; i < number; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        resMST[0] = -1;

        for (int count = 0; count < number - 1; count++) {
            int indexU = findNodeWithMinKey(key, mstSet);
            mstSet[indexU] = true;

            for (int indexV = 0; indexV < number; indexV++)
                if (graph[indexU][indexV] != 0 &&
                               !mstSet[indexV] &&
                    graph[indexU][indexV] < key[indexV]) {
                        resMST[indexV] = indexU;
                        key[indexV] = graph[indexU][indexV];
                }
        }

        printMST(resMST, graph);

        Map<Integer, Map<Integer, Double>> resMap = new HashMap<>();
        for (int i = 1; i < number; i++) {
            Map<Integer, Double> map1 = new TreeMap<>();

            map1.put(i, Double.valueOf(graph[i][resMST[i]]));
            weight.add(Double.valueOf(graph[i][resMST[i]]));

            if (resMap.containsKey(resMST[i])) {
                Map<Integer, Double> temp = new TreeMap<>();
                for (Map.Entry<Integer, Map<Integer, Double>> entry : resMap.entrySet()){
                    if (entry.getKey().equals(resMST[i])) {
                        temp = entry.getValue();
                    }
                }
                temp.putAll(map1);
            } else {
                resMap.put(resMST[i], map1);
            }
        }

        return resMap;
    }

    int findNodeWithMinKey(int[] key, Boolean[] mst) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int i = 0; i < number; i++)
            if (!mst[i] && key[i] < min) {
                minIndex = i;
                min = key[i];
            }
        return minIndex;
    }

    void printMST(int[] parent, Integer[][] graph) {
        System.out.println("Node \t\tWeight");
        for (int i = 1; i < number; i++)
            System.out.println(parent[i] + " <----> " + i + "\t" + graph[i][parent[i]]);
    }
}