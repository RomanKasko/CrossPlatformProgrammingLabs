package com;

public class ManagerFactory
{
    private final static int MAX = 15;
    public static IRowSumCalculator createEvaluator(int num)
    {
        if (num >= MAX)
        {
            return new LongRowManager(num);
        }
        else
        {
            return new ShortRowManager(num);
        }
    }
}
