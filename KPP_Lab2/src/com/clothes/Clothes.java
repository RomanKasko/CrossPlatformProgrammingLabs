package com.clothes;

public class Clothes extends Product {

    private int cotton;
    private int elastane;

    public Clothes(String title,String size,int price, int cotton,int elastane, String brand)
    {   super(title,size, price, brand);
        this.cotton = cotton;
        this.elastane = elastane;
    }

    public int getCotton() {
        return cotton;
    }
    public int getElastane() {
        return elastane;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                super.toString() +
                " % of cotton=" + cotton +
                " % of elastane=" + elastane +
                "}\n";
    }
}
