package com.manager;

import com.enums.SortingOrder;
import com.clothes.Clothes;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ClothesStoreManager {

    private List<Clothes> list;
    public List<Clothes> getList()
    {
        return list;
    }
    public ClothesStoreManager(List<Clothes> list)
    {
        this.list = list;
    }

    public static class StaticComparator implements Comparator<Clothes> {
        @Override
        public int compare(Clothes cl1, Clothes cl2) {
            return cl1.getBrand().compareTo(cl2.getBrand());
        }
    }

    private class InnerComparator implements Comparator<Clothes> {
        @Override
        public int compare(Clothes cl1, Clothes cl2) {
            return cl1.getPrice() - cl2.getPrice();
        }
    }

    public List<Clothes> findListByBrand(String brand) {
        return list.stream().filter(d -> Objects.equals(d.getBrand(), brand)).toList();
    }

    public List<Clothes> findListByPrice(int price) {
        return list.stream().filter(d -> d.getPrice() == price).toList();
    }

    public void sortByBrand(SortingOrder order) {
        list.sort(new StaticComparator());
        reverseListIfNeeded(order);
    }

    public void sortByPrice(SortingOrder order) {
        list.sort(new InnerComparator());
        reverseListIfNeeded(order);
    }

    private void reverseListIfNeeded(SortingOrder order) {
        if (order == SortingOrder.DESCENDING) {
            Collections.reverse(list);
        }
    }

    public void sortBySize(SortingOrder order) {
        list.sort(new Comparator<Clothes>() {
            @Override
            public int compare(Clothes t1, Clothes t2) {
                return t1.getSize().compareTo(t2.getSize());
            }
        });
        reverseListIfNeeded(order);
    }

    public void sortByCotton(SortingOrder order) {
        list.sort((t1, t2) -> t1.getCotton() - t2.getCotton());
        reverseListIfNeeded(order);
    }

    @Override
    public String toString() {
        return "ClothesStoreManager{" +
                "list=" + list +
                '}';
    }
}
