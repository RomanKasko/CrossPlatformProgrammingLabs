package com;
import java.math.BigInteger;

public class Fraction
{
    private String nominator;
    private String denominator;

    public String getNominator() {
        return nominator;
    }
    public String getDenominator() {
        return denominator;
    }

    public void setNominator(long nominator) {
        this.nominator = String.valueOf(nominator);
    }
    public void setNominator(BigInteger nominator) {
        this.nominator = nominator.toString();
    }
    public void setDenominator(long denominator) {
        this.denominator = String.valueOf(denominator);
    }
    public void setDenominator(BigInteger denominator) {
        this.denominator = denominator.toString();
    }
}
