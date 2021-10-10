package com;

import com.clothes.*;
import com.enums.SortingOrder;
import com.manager.ClothesStoreManager;

import java.util.*;

public class Main {
    private static ClothesStoreManager manager;

    public static void main(String[] args) {
        List<Clothes> clothesList = new ArrayList<>();
        List<Clothes> clothesAdidasList = new ArrayList<>();
        List<Clothes> clothesPriceList = new ArrayList<>();

        fillData(clothesList);
        manager = new ClothesStoreManager(clothesList);

        System.out.println("Sorting by price:");
        manager.sortByPrice(SortingOrder.DESCENDING);
        System.out.println(manager);
        System.out.println("-------------------------------------");

        System.out.println("Sorting by brand:");
        manager.sortByBrand(SortingOrder.ASCENDING);
        System.out.println(manager);
        System.out.println("-------------------------------------");

        System.out.println("Sorting by size:");
        manager.sortBySize(SortingOrder.ASCENDING);
        System.out.println(manager);
        System.out.println("-------------------------------------");

        System.out.println("Sorting by % of cotton:");
        manager.sortByCotton(SortingOrder.DESCENDING);
        System.out.println(manager);
        System.out.println("-------------------------------------");

        System.out.println("List of Adidas clothes:");
        clothesAdidasList = manager.findListByBrand("Adidas");
        System.out.println(clothesAdidasList);
        System.out.println("-------------------------------------");

        System.out.println("List of clothes with price 600:");
        clothesPriceList = manager.findListByPrice(600);
        System.out.println(clothesPriceList);
        System.out.println("-------------------------------------");
    }

    private static void fillData(List<Clothes> list) {
        list.add(new Outerwear("Hoody","M",1500,50,50,"Adidas",80));
        list.add(new Outerwear("Hoody","S",1000,70,30,"Adidas",50));
        list.add(new Outerwear("Hoody","L",1600,45,55,"Adidas",90));
        list.add(new Outerwear("Hoody","M",1300,60,40,"Nike",85));
        list.add(new Outerwear("Hoody","S",1000,70,30,"Nike",55));
        list.add(new Outerwear("Hoody","L",1500,45,55,"Nike",95));
        list.add(new Outerwear("T-Shirt","S",500,45,55,"Adidas",20));
        list.add(new Outerwear("T-Shirt","M",650,45,55,"Adidas",30));
        list.add(new Outerwear("T-Shirt","S",600,75,25,"Nike",25));
        list.add(new Outerwear("T-Shirt","M",700,90,10,"Reebok",35));
        list.add(new Outerwear("T-Shirt","M",750,50,50,"Reebok",30));
        list.add(new Sweatpants("Shorts","S",550,70,30,"Adidas",40));
        list.add(new Sweatpants("Shorts","M",400,50,50,"Nike",45));
        list.add(new Sweatpants("Shorts","L",600,50,50,"Adidas",50));
        list.add(new Sweatpants("Sport pants","M",750,65,35,"Reebok",80));
        list.add(new Sweatpants("Sport pants","M",700,35,65,"Adidas",90));
        list.add(new Sweatpants("Sport pants","M",550,70,30,"Puma",80));
        list.add(new Sweatpants("Sport pants","M",600,50,50,"Nike",85));
    }
}