package com;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter num of a row: ");
	    int num = scanner.nextInt();

        IRowSumCalculator summable = ManagerFactory.createEvaluator(num);
        Fraction fraction = summable.getSum();

        System.out.printf("Result of a row: %s / %s", fraction.getNominator(), fraction.getDenominator());
    }
}
