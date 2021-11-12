package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Library implements Serializable {
    private final List<SeasonTicket> seasonTicketList;
    private final List<Book> totalBookList;
    private final List<Book> availableBookList;

    public List<SeasonTicket> getSeasonTicketList() {
        return seasonTicketList;
    }

    public List<Book> getTotalBookList() {
        return totalBookList;
    }

    public List<Book> getAvailableBookList() {
        return availableBookList;
    }

    public Library(List<SeasonTicket> seasonTicketList, List<Book> availableBookList) {
        this.availableBookList = availableBookList;
        this.seasonTicketList = seasonTicketList;
        this.totalBookList = new LinkedList<>(availableBookList);
    }

    @Override
    public String toString() {
        return "Library{"
                + "season Tickets = " + seasonTicketList + '\n'
                + "available Books = " + availableBookList + '\n'
                + "total Books = " + totalBookList
                + '}' + '\n';
    }
}
