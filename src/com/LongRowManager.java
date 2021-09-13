package com;
import java.math.BigInteger;

public class LongRowManager implements IRowSumCalculator
{
    private final int num;
    private BigInteger nominator = BigInteger.ZERO;
    private BigInteger denominator = BigInteger.ZERO;

    public LongRowManager(int num)
    {
        this.num = num;
    }

    public Fraction getSum()
    {
        for (int i = 1; i <= num; ++i)
        {
            BigInteger curNominator = BigInteger.ONE;
            BigInteger curDenominator = BigInteger.valueOf(13).subtract(BigInteger.valueOf(i)).add(BigInteger.
                    valueOf(i).pow(2)).subtract(BigInteger.valueOf(17)).multiply(BigInteger.valueOf(i));

            if (i == 1)
            {
                nominator = curNominator;
                denominator = curDenominator;
            }
            else
            {
                BigInteger lcm = calculateLCM(denominator, curDenominator);
                BigInteger multiplier = lcm.divide(denominator);
                nominator = nominator.multiply(multiplier);
                denominator = denominator.multiply(multiplier);

                multiplier = lcm.divide(curDenominator);
                curNominator = curNominator.multiply(multiplier);

                nominator = nominator.add(curNominator);
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
        BigInteger divider = nominator.gcd(denominator);
        if (!divider.equals(BigInteger.ZERO))
        {
            nominator = nominator.divide(divider);
            denominator = denominator.divide(divider);
        }
    }

    private BigInteger calculateLCM(BigInteger firDenominator, BigInteger secDenominator)
    {
        return firDenominator.multiply(secDenominator).divide(firDenominator.gcd(secDenominator));
    }
}
