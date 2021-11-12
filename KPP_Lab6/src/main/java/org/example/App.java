package org.example;

import org.example.entity.SeasonTicket;
import org.example.entity.Book;
import org.example.entity.BookBorrow;
import org.example.manager.LibraryManager;
import org.example.manager.FilesManager;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App 
{
    private static final FilesManager filesManager = new FilesManager();
    private static final LibraryManager libraryManager = new LibraryManager();
    private static final Scanner scanner = new Scanner(System.in);
    private static SeasonTicket currentSeasonTicket;

    public static void main( String[] args ) throws ParseException {
        System.out.println("We are glad to see you in our library. Please ");
        enterIntoLibrary();
        showMenu();
        System.out.println("Have a nice day! Please come again");
        filesManager.saveLibraryInfo(libraryManager.getLibraryInfo());
    }
    
    private static void enterIntoLibrary() {
        boolean isEnd = false;
        do {

            System.out.println("Write your email: ");
            String email = scanner.nextLine();
            SeasonTicket client = libraryManager.getClient(email);
            if (client != null) {
                isEnd = true;
                currentSeasonTicket = client;
            } else {
                System.out.println("Your are not our client");
            }

        } while(!isEnd);
    }

    private static void showMenu() throws ParseException {
        System.out.println("Choose mode: ");
        boolean end = false;
        do {
            System.out.println("\tEnter as Client (1)");
            System.out.println("\tEnter as Administrator (2)");
            System.out.println("\tExit (smth else)");

            String mode = scanner.nextLine();
            switch (mode) {
                case "1":
                    showClientMenu();
                    break;
                case "2":
                    showAdministratorMenu();
                    break;
                default:
                    end = true;
            }
        } while(!end);
    }

    private static void showClientMenu() throws ParseException {
        System.out.println("Choose mode: ");
        boolean end = false;

        do {
            System.out.println("\tTake a book (1)");
            System.out.println("\tReturn the book (2)");
            System.out.println("\tExit (smth else)");

            String result = scanner.nextLine();
            switch (result) {
                case "1":
                    lendBookToClientProcess();
                    break;
                case "2":
                    clientReturnBookProcess();
                    break;
                default:
                    end = true;
            }
        } while(!end);
    }

    private static void lendBookToClientProcess() {
        System.out.println("List of available books: ");
        List<Book> books = libraryManager.getAvailableBooks();

        if (books.isEmpty()) {
            System.out.println("There`re no any available books");
            return;
        }

        for (int i = 0; i < books.size(); ++i) {
            Book book = books.get(i);
            System.out.printf("%d. %s  %s  %d %n", i + 1, book.getName(), book.getAuthor(), book.getPublishYear());
        }

        int startIndex = -1;
        boolean end = false;
        do {
            System.out.println("Choose book (1 - ...) or exit(0) : ");

            try {
                startIndex = Integer.parseInt(scanner.nextLine());
            } catch (Exception ex) {
                System.out.println("Invalid data");
            }

            if (startIndex == 0) {
                return;
            } else if (startIndex > 0) {
                end = true;
            }
        } while(!end);

        System.out.println("Enter date when you return the book: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);

        if (!libraryManager.addBookBorrow(currentSeasonTicket, new BookBorrow(books.get(startIndex - 1), date))) {
            System.out.println("You reached the max number of borrowed books.");
        }
    }

    private static void clientReturnBookProcess() {
        System.out.println("There some book the you should return: ");

        List<BookBorrow> orderList = libraryManager.getBookBorrowingList(currentSeasonTicket);

        if (orderList.isEmpty()) {
            System.out.println("You don`t have any borrowed books");
            return;
        }

        for (int i = 0; i < orderList.size(); ++i) {
            BookBorrow order = orderList.get(i);
            System.out.printf("%d. %s  %s %n", i + 1, order.getBook(), order.getReturningDate().toString());
        }

        int startIndex = -1;
        boolean end = false;
        do {
            System.out.println("Choose book (1 - ...) or exit(0) : ");
            try {
                startIndex = Integer.parseInt(scanner.nextLine());
            } catch (Exception ex) {
                System.out.println("Invalid data");
            }

            if (startIndex == 0) {
                return;
            } else if (startIndex > 0) {
                end = true;
            }
        } while(!end);

        libraryManager.removeBookBorrow(currentSeasonTicket, orderList.get(startIndex - 1));
        System.out.println("You returned this book at " + LocalDate.now());
    }

    private static void printManagerMenu() {
        System.out.println("Choose the mode: ");
        System.out.println("\tClients mailing list (1)");
        System.out.println("\tClients debt list (2)");
        System.out.println("\tMail to clients (3)");
        System.out.println("\tSort books by publish year(4)");
        System.out.println("\tAuthor popularity (5)");
        System.out.println("\tMax count of books from clients list (6)");
        System.out.println("\tExit (smth else)");
    }

    private static void showAdministratorMenu() {
        boolean end = false;
        do {
            printManagerMenu();
            String result = scanner.nextLine();
            switch (result) {
                case "1":
                    System.out.println(libraryManager.getUserListThatShouldBeMailed());
                    break;
                case "2":
                    var entries = libraryManager.getDebtList().entrySet();
                    for (var entry: entries) {
                        System.out.println(entry.getKey().getClientName() + " " + entry.getValue());
                    }
                    break;
                case "3":
                    libraryManager.createMailingLists();
                    break;
                case "4":
                    System.out.println(libraryManager.sortBooksByPublishYear());
                    break;
                case "5":
                    System.out.println("Enter author name: ");
                    String author = scanner.nextLine();
                    System.out.println(libraryManager.getBorrowedBooksCounterWithSuchAuthor(author));
                    break;
                case "6":
                    long num = libraryManager.findMaxBookCountFromClients();
                    System.out.println("Max nums of books in one Client is " + num);
                    break;
                default:
                    end = true;
            }
        } while(!end);
    }
}
