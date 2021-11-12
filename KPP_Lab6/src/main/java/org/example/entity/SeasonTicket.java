package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

public class SeasonTicket implements Serializable {
    private String clientName;
    private String clientSurname;
    private String clientLastName;
    private String clientEmail;
    private List<BookBorrow> borrowList;

    public SeasonTicket(String clientName, String clientSurname, String clientLastName, String clientEmail, List<BookBorrow> borrowList) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientLastName = clientLastName;
        this.clientEmail = clientEmail;
        this.borrowList = borrowList;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void setBorrowList(List<BookBorrow> borrowList) {
        this.borrowList = borrowList;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public List<BookBorrow> getBorrowList() {
        return borrowList;
    }

    public void addBook(BookBorrow order) {
        borrowList.add(order);
    }
    public void removeBook(BookBorrow order) {
        borrowList.remove(order);
    }
    public void notify(String msg) {
        System.out.println(msg+'\n');
    }

    @Override
    public String toString() {
        return "Season Ticket{"
                + "name: " + clientName
                + "surname: " + clientSurname
                + "last name: " + clientLastName
                + "email: " + clientEmail
                + "borrow List:" + borrowList + "}";
    }
}
