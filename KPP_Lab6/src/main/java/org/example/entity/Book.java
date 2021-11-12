package org.example.entity;

import lombok.*;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Book implements Serializable {
    private final String author;
    private final String name;
    private final int publishYear;

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public Book(String author, String name, int year) {
        this.author = author;
        this.name = name;
        this.publishYear = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", publishYear=" + publishYear +
                '}';
    }
}
