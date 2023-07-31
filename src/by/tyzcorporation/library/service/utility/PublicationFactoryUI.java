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

import static by.tyzcorporation.library.service.utility.db.DBUtility.findLastId;

public class PublicationFactoryUI {
    public static Publication createPublication(String type) {
        ConnectionPool pool = new ConnectionPool(1);
        pool.initializePool();

        try (Connection connection = pool.getConnection()) {
            switch (type) {
                case "BOOK":
                    return createBook(connection);
                //   case "MAGAZINE" ->
                //{
                // System.out.print("Enter magazine title: ");
                // title = scanner.nextLine();
                //System.out.print("Enter magazine category: ");
                //  String category = scanner.nextLine();
                // System.out.print("Enter magazine genre: ");
                // genre = scanner.nextLine();
                // pageCount = getUserInputInt("Enter magazine page count: ");
                //    return new Magazine(1, title, pageCount, category, genre, false, 0);
                //  }
                //case "ALBUM" -> {
                //    System.out.print("Enter album title: ");
                //   title = scanner.nextLine();
                //   pageCount = getUserInputInt("Enter album page count: ");
                //     return new ConcreteAlbum(title, pageCount, 0);
                //    }
                default:
                    throw new IllegalArgumentException("Invalid publication type: " + type);
            }
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException("Error while creating publication", e);
        } finally {
            pool.closeAllConnections();
        }
    }

    private static Book createBook(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int id = findLastId(connection) + 1;
        System.out.println("id = " + id);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();
        int pageCount = getUserInputInt("Enter book page count: ");

        Book book = new Book(id, title, pageCount, author, genre, false, 0);
        BookRepository bookRepository = new BookRepository(connection);
        BookController bookController = new BookController(new BookService(bookRepository, new PublicationRepository(connection), connection));
        bookController.createBook(book);

        return book;
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
