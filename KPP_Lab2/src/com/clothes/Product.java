package com.clothes;

import java.util.Date;

public abstract class Product {
    private String size;
    private String brand;
    private int price;
    private String title;

    public Product(String title, String size, int price, String brand) {
        this.title = title;
        this.size = size;
        this.price = price;
        this.brand = brand;
    }
    public String getSize() {
        return size;
    }
    public String getBrand() {
        return brand;
    }
    public int getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "Size=" + size +
                ", brand=" + brand +
                ", price=" + price +
                "}\n";
    }
}
