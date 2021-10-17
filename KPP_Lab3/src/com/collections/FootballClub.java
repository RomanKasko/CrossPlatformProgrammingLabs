package com.collections;

import java.util.Objects;

public class FootballClub {

    private final String title;
    private final String city;
    private final int yearOfFoundation;

    public FootballClub(String title,int yearOfFoundation,String city) {
        this.title = title;
        this.city = city;
        this.yearOfFoundation = yearOfFoundation;
    }

    public String getTitle() {
        return title;
    }
    public String getCity() {
        return city;
    }
    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballClub club = (FootballClub) o;
        return yearOfFoundation == club.yearOfFoundation && Objects.equals(title, club.title) && Objects.equals(city, club.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, city, yearOfFoundation);
    }

    @Override
    public String toString() {
        return "Football Club {" +
                "title='" + title + '\'' +
                ", city=" + city +
                ", yearOfFoundation=" + yearOfFoundation +
                "}\n";
    }
}
