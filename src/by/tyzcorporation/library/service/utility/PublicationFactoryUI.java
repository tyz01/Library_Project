package by.tyzcorporation.library.service.utility;

import by.tyzcorporation.library.model.entity.*;
import by.tyzcorporation.library.service.db.ConnectionPool;
import by.tyzcorporation.library.service.db.bisneslogic.BookService;
import by.tyzcorporation.library.service.db.controller.BookController;
import by.tyzcorporation.library.service.db.repository.BookRepository;
import by.tyzcorporation.library.service.db.repository.PublicationRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PublicationFactoryUI {

    public static Publication createPublication(String type) {

        ConnectionPool pool = new ConnectionPool(5);
        pool.initializePool();
        Connection connection = null;
        try {
            connection = pool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        BookRepository bookRepository = new BookRepository(connection);
        PublicationRepository publicationRepository = new PublicationRepository(connection);
        BookService bookService = new BookService(bookRepository, publicationRepository);

        BookController bookController = new BookController(bookService);


        Scanner scanner = new Scanner(System.in);
        Integer id = null;
        String title;
        String author;
        String genre;
        int pageCount;
        switch (type) {
            case "BOOK" -> {
                System.out.print("Enter book title: ");
                title = scanner.nextLine();
                System.out.print("Enter book author: ");
                author = scanner.nextLine();
                System.out.print("Enter book genre: ");
                genre = scanner.nextLine();
                pageCount = getUserInputInt("Enter book page count: ");
                Book book = new Book(id, title, pageCount, author, genre, false, 0);

                bookController.createBook(book);
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                pool.closeAllConnections();

                return book;
            }
            case "MAGAZINE" -> {
                System.out.print("Enter magazine title: ");
                title = scanner.nextLine();
                System.out.print("Enter magazine category: ");
                String category = scanner.nextLine();
                System.out.print("Enter magazine genre: ");
                genre = scanner.nextLine();
                pageCount = getUserInputInt("Enter magazine page count: ");
                return new Magazine(1, title, pageCount, category, genre, false, 0);
            }
            case "ALBUM" -> {
                System.out.print("Enter album title: ");
                title = scanner.nextLine();
                pageCount = getUserInputInt("Enter album page count: ");
                return new ConcreteAlbum(title, pageCount, 0);
            }
            default -> throw new IllegalArgumentException("Invalid publication type: " + type);
        }
    }

    public static int getUserInputInt(String message) {
        Scanner scanner = new Scanner(System.in);
        int value;
        while (true) {
            try {
                System.out.print(message);
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
    }
}
