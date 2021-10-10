package com.clothes;

public class Outerwear extends Clothes {

    private int sleeveLength;
    public Outerwear(String title,String size,int price, int cotton,int elastane, String brand, int sleeveLength) {
        super(title,size, price, cotton, elastane, brand);
        this.sleeveLength = sleeveLength;
    }

    public int getSleeveLength() {
        return sleeveLength;
    }

    @Override
    public String toString() {
        return "Outerwear{" +
                super.toString() + "," +
                "sleeveLength=" + sleeveLength +
                "}\n";
    }
}
