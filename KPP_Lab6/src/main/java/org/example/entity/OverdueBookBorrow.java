package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public class OverdueBookBorrow {
    private final Book book;
    private final long debtDay;

    @Override
    public String toString() {
        return "OverdueBookBorrow{" +
                "book=" + book +
                ", debtDay=" + debtDay +
                '}';
    }

    public Book getBook() {
        return book;
    }

    public long getDebtDay() {
        return debtDay;
    }

    public OverdueBookBorrow(Book book, long debtDay) {
        this.book = book;
        this.debtDay = debtDay;
    }
}
