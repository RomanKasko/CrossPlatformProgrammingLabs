package org.example.manager;

import org.example.entity.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryManager {
    private static final int MAX_BOOKS_PER_CLIENT = 5;
    private static final int MIN_BOOKS_FOR_MAILING = 2;
    private final Library library;

    public LibraryManager() {
        FilesManager manager = new FilesManager();
        this.library = new Library(manager.readClientsInfo(), manager.readBooksInfo());
        library.getTotalBookList().
                addAll(library.getSeasonTicketList().
                        stream().
                        flatMap(seasonTicket -> seasonTicket.getBorrowList().stream()).
                        map(BookBorrow::getBook).
                        collect(Collectors.toList()));

    }

    public Library getLibraryInfo() {
        return library;
    }

    public List<String> getUserListThatShouldBeMailed() {
        return library.getSeasonTicketList().stream().
                filter(seasonTicket -> seasonTicket.getBorrowList().size() > MIN_BOOKS_FOR_MAILING).
                map(SeasonTicket::getClientEmail).
                collect(Collectors.toList());
    }

    public long getBorrowedBooksCounterWithSuchAuthor(String author) {
        return library.getSeasonTicketList().stream().
                filter(seasonTicket -> seasonTicket.getBorrowList().stream().
                anyMatch(order -> order.getBook().getAuthor().equals(author))).
                count();
    }

    public SeasonTicket getClient(String email) {
        var list = library.getSeasonTicketList().stream().
                filter(seasonTicket -> seasonTicket.getClientEmail().equals(email)).
                collect(Collectors.toList());

        return list.isEmpty() ? null : list.get(0);
    }

    public List<Book> getAvailableBooks() {
        return library.getAvailableBookList();
    }

    public List<BookBorrow> getBookBorrowingList(SeasonTicket currentSeasonTicket) {
        return library.getSeasonTicketList().stream().
                filter(seasonTicket -> seasonTicket.equals(currentSeasonTicket)).
                collect(Collectors.toList()).
                get(0).getBorrowList();
    }

    public long findMaxBookCountFromClients() {
        return library.getSeasonTicketList().stream().
                map(seasonTicket -> seasonTicket.getBorrowList().size()).
                max(Integer::compareTo).orElse(0);
    }

    public List<Book> sortBooksByPublishYear() {
        return library.getTotalBookList().stream().
                sorted(Comparator.comparingInt(Book::getPublishYear)).
                collect(Collectors.toList());
    }

    public void createMailingLists() {
        List<SeasonTicket> firMailList = library.getSeasonTicketList().stream().
                filter(seasonTicket -> seasonTicket.getBorrowList().size() < MIN_BOOKS_FOR_MAILING).
                collect(Collectors.toList());

        List<SeasonTicket> secMailList = library.getSeasonTicketList().stream().
                filter(seasonTicket -> seasonTicket.getBorrowList().size() >= MIN_BOOKS_FOR_MAILING).
                collect(Collectors.toList());

        for (SeasonTicket ticket : firMailList) {
            ticket.notify("Hi "+ ticket.getClientName() + "! There some news about books in our library!");
        }

        for (SeasonTicket ticket : secMailList) {
            ticket.notify("Hello "+ ticket.getClientName() + "! Don't forget to return borrowed books in time");
        }
    }

    public Map<SeasonTicket, List<OverdueBookBorrow>> getDebtList() {
        return library.getSeasonTicketList().stream().
                filter(seasonTicket -> seasonTicket.getBorrowList().stream().
                anyMatch(order -> order.getReturningDate().isBefore(LocalDate.now()))).
                collect(Collectors.toMap(seasonTicket -> seasonTicket, seasonTicket -> seasonTicket.getBorrowList().stream().
                filter(order -> order.getReturningDate().isBefore(LocalDate.now())).
                map(order -> new OverdueBookBorrow(order.getBook(), ChronoUnit.DAYS.between(LocalDate.now(), order.getReturningDate()))).
                collect(Collectors.toList())));
    }

    public boolean addBookBorrow(SeasonTicket ticket, BookBorrow order) {
        if (ticket.getBorrowList().size() == MAX_BOOKS_PER_CLIENT) {
            return false;
        }

        ticket.addBook(order);
        library.getTotalBookList().remove(order.getBook());
        return true;
    }

    public void removeBookBorrow(SeasonTicket currentSeasonTicket, BookBorrow order) {
        currentSeasonTicket.removeBook(order);
    }
}
