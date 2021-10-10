package com.clothes;

public class Sweatpants extends Clothes {
    private int legLength;

    public Sweatpants(String title,String size,int price, int cotton, int elastane, String brand, int legLength) {
        super(title,size,price,cotton,elastane,brand);
        this.legLength = legLength;
    }

    public int getLegLength() {
        return legLength;
    }

    @Override
    public String toString() {
        return "Sweatpants{" +
                super.toString() + "," +
                " legLength =" + legLength +
                "}\n";
    }
}
