package com.collections;

import java.io.*;
import java.util.*;

public class TaskManager {
    private static List<FootballClub> firstFileData;
    private static List<FootballClub> secondFileData;
    private static final File firFile = new File(".\\resources\\data.txt");
    private static final File secFile = new File(".\\resources\\data2.txt");

    public void task1(int n) {
        if (firstFileData == null) {
            firstFileData = readData(firFile);
        }
        Map<String, List<FootballClub>> map = new HashMap<>();
        var cities = new ArrayList<>(firstFileData);

        for (FootballClub item: cities)
        {
            List<FootballClub> tempList;
            if (map.containsKey(item.getCity()))
            {
                tempList = map.get(item.getCity());
                tempList.add(item);
                map.put(item.getCity(), tempList);
            }
            else
            {
                tempList = new ArrayList<>();
                tempList.add(item);
                map.put(item.getCity(), tempList);
            }
        }

        for (Map.Entry<String, List<FootballClub>> entry : map.entrySet()) {
            System.out.println("City: " + entry.getKey());
            for (int i = 0; i < n && i < entry.getValue().size(); ++i) {
                System.out.println("\tClub: " + entry.getValue().get(i));
            }
        }
    }

    public void task2() {
        if (firstFileData == null) {
            firstFileData = readData(firFile);
        }

        var clubsList = new ArrayList<>(firstFileData);
        Map<String, List<String>> map = new HashMap<>();
        List<String> resCitiesList = new LinkedList<>();

        for (FootballClub item : clubsList) {
            List<String> list = null;
            if (map.containsKey(item.getTitle())) {
                list = map.get(item.getTitle());
                list.add(item.getCity());
                map.put(item.getTitle(), list);
            } else {
                list = new ArrayList<String>();
                list.add(item.getCity());
                map.put(item.getTitle(), list);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println("Club name: "
                    + entry.getKey()
                    + (entry.getValue().size() > 1 ?
                    " have " + entry.getValue().size() + " cities."
                    : " has " + entry.getValue().size() + " city."));
        }
    }

    public void task3() {
        if (firstFileData == null) {
            firstFileData = readData(firFile);
        }
        if (secondFileData == null) {
            secondFileData = readData(secFile);
        }

        List<FootballClub> clubsFirList = new ArrayList<>(firstFileData);
        List<FootballClub> clubsSecList = new ArrayList<>(secondFileData);
        List<FootballClub> resultedList = new ArrayList<FootballClub>();

        for (FootballClub item: clubsFirList)
        {
            if (clubsSecList.contains(item))
            {
                resultedList.add(item);
            }
        }
        System.out.println("Shared elements from two lists");
        for (FootballClub item: resultedList)
        {
            System.out.println("\t" + item.toString());
        }
    }

    private static List<FootballClub> readData(File file) {
        int lineNumber = 0;
        List<FootballClub> list = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                ++lineNumber;
                String[] data = line.split(",");
                list.add(new FootballClub(data[0],Integer.parseInt(data[1]),data[2]));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.out.printf("Sorry, but you have invalid data in %d line. Fix it and try again", lineNumber);
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Sorry, but you get unexpected error while reading file");
            System.exit(1);
        }
        return list;
    }
}
