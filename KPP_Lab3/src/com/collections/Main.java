package com.collections;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final TaskManager manager = new TaskManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String mode = scanner.nextLine();
            switch (mode) {
                case "1":
                    System.out.println("Num of clubs to print from each city: ");
                    int n = scanner.nextInt();
                    manager.task1(n);
                    break;
                case "2":
                    manager.task2();
                    break;
                case "3":
                    manager.task3();
                    break;
                default:
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1 Task(1)");
        System.out.println("2 Task(2)");
        System.out.println("3 Task(3)");
        System.out.println("Quit(smth else)");
    }
}
