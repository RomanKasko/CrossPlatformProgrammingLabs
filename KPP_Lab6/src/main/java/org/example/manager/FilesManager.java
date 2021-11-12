package org.example.manager;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.SeasonTicket;
import org.example.entity.Book;
import org.example.entity.Library;
import org.example.entity.BookBorrow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;


public class FilesManager {
    private final File books = new File("src\\main\\resources\\Books.txt");
    private final File clients = new File("src\\main\\resources\\Clients.txt");

    List<Book> readBooksInfo() {
        int lineCounter = 0;
        List<Book> list = new LinkedList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(books))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                ++lineCounter;
                String[] data = line.split(",");
                list.add(new Book(data[0], data[1], Integer.parseInt(data[2])));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.out.printf("Invalid data: %d line", lineCounter);
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("File reading error");
            System.exit(1);
        }
        return list;
    }

    List<SeasonTicket> readClientsInfo() {
        int lineCounter = 0;
        List<SeasonTicket> list = new LinkedList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(clients))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                ++lineCounter;
                String[] data = line.split(",");
                var orderList = parseOrder(data[data.length - 1]);
                list.add(new SeasonTicket(data[0], data[1], data[2], data[3], orderList));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.out.printf("Invalid data: %d line", lineCounter);
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("File reading error");
            System.exit(1);
        }

        return list;
    }

    private List<BookBorrow> parseOrder(String borrow) {
        borrow = borrow.trim().substring(1, borrow.length() - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<BookBorrow> list = new LinkedList<>();

        for (var order: borrow.split("#")) {
            String[] elem = order.split(";");
            list.add(new BookBorrow(new Book(elem[1], elem[0], Integer.parseInt(elem[2])),
                    LocalDate.parse(elem[3], formatter)));
        }

        return list;
    }

    public void saveLibraryInfo(Library library) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();
            objectMapper.writeValue(new File("src\\main\\resources\\Library.txt"), library);
        } catch (IOException ex) {
            System.out.println("Write info error");
        }
    }
}
