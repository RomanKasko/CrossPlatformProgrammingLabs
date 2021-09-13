package com;

public class ShortRowManager implements IRowSumCalculator
{
    private final int num;
    private long nominator = 0;
    private long denominator = 0;

    public ShortRowManager(int num) {
        this.num = num;
    }

    public Fraction getSum()
    {
        for (int i = 1; i <= num; ++i)
        {
            long curNominator = 1;
            long curDenominator = 13 - i + i * i - 17 * i;

            if (i == 1)
            {
                nominator = curNominator;
                denominator = curDenominator;
            }
            else
            {
                long lcm = calculateLCM(denominator, curDenominator);
                long multiplier = lcm / denominator;
                nominator *= multiplier;
                denominator *= multiplier;

                multiplier = lcm / curDenominator;
                curNominator *= multiplier;

                nominator += curNominator;
            }
            reduceFraction();
        }
        Fraction fraction = new Fraction();
        fraction.setNominator(nominator);
        fraction.setDenominator(denominator);
        return fraction;
    }

    private void reduceFraction()
    {
        long divider = calculateGDC(nominator, denominator);
        if (divider != 0)
        {
            nominator /= divider;
            denominator /= divider;
        }
    }

    private long calculateGDC(long nominator, long denominator)
    {
        return denominator == 0 ? nominator : calculateGDC(denominator, nominator % denominator);
    }

    private long calculateLCM(long firstDenominator, long secondDenominator)
    {
        return (firstDenominator * secondDenominator) / calculateGDC(firstDenominator, secondDenominator);
    }
}
