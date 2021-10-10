package com.clothes;

public class Shoes extends Product {
    private String lacesColor;

    public Shoes(String title,String size, int price, String brand,String lacesColor) {
        super(title,size,price,brand);
        this.lacesColor = lacesColor;
    }

    public String getLacesColor() {
        return lacesColor;
    }

    @Override
    public String toString() {
        return "Shoes{" +
                super.toString() + "," +
                " lacesColor=" + lacesColor +
                "}\n";
    }
}
