package org.example.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

public class BookBorrow implements Serializable {
    private final Book book;
    private final LocalDate returningDate;

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "BookBorrow{" +
                "book=" + book +
                ", returningDate=" + returningDate +
                '}';
    }

    public LocalDate getReturningDate() {
        return returningDate;
    }

    public BookBorrow(Book book, LocalDate date) {
        this.book = book;
        this.returningDate = date;
    }
}
