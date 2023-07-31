package by.tyzcorporation.library.service.utility;

import by.tyzcorporation.library.model.entity.*;
import by.tyzcorporation.library.service.db.ConnectionPool;
import by.tyzcorporation.library.service.db.bisneslogic.AlbumService;
import by.tyzcorporation.library.service.db.bisneslogic.BookService;
import by.tyzcorporation.library.service.db.bisneslogic.MagazineService;
import by.tyzcorporation.library.service.db.controller.AlbumController;
import by.tyzcorporation.library.service.db.controller.BookController;
import by.tyzcorporation.library.service.db.controller.MagazineController;
import by.tyzcorporation.library.service.db.repository.AlbumRepository;
import by.tyzcorporation.library.service.db.repository.BookRepository;
import by.tyzcorporation.library.service.db.repository.MagazineRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static by.tyzcorporation.library.service.utility.db.DBUtility.findLastId;

public class PublicationFactoryUI {
    private static int id;
    private static String title;
    private static String genre;
    private static Integer pageCount;
    private static String author;
    private static final Scanner scanner = new Scanner(System.in);

    public static Publication createPublication(String type) {
        ConnectionPool pool = new ConnectionPool(1);
        pool.initializePool();
        try (Connection connection = pool.getConnection()) {
            return switch (type) {
                case "BOOK" -> createBook(connection);
                case "MAGAZINE" -> createMagazine(connection);
                case "ALBUM" -> createAlbum(connection);
                default -> throw new IllegalArgumentException("Invalid publication type: " + type);
            };
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException("Error while creating publication", e);
        } finally {
            pool.closeAllConnections();
        }
    }

    private static Book createBook(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        id = findLastId(connection) + 1;
        System.out.println("id = " + id);
        System.out.print("Enter book title: ");
        title = scanner.nextLine();
        System.out.print("Enter book author: ");
        author = scanner.nextLine();
        System.out.print("Enter book genre: ");
        genre = scanner.nextLine();
        pageCount = getUserInputInt("Enter book page count: ");

        Book book = new Book(id, title, pageCount, author, genre, false, 0);
        BookRepository bookRepository = new BookRepository(connection);
        BookController bookController = new BookController(new BookService(bookRepository));
        bookController.createBook(book, connection);

        return book;
    }

    private static Magazine createMagazine(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        int id = findLastId(connection) + 1;
        System.out.print("Enter magazine title: ");
        title = scanner.nextLine();
        System.out.print("Enter magazine category: ");
        String category = scanner.nextLine();
        System.out.print("Enter magazine genre: ");
        genre = scanner.nextLine();
        pageCount = getUserInputInt("Enter magazine page count: ");

        Magazine magazine = new Magazine(id, title, pageCount, category, genre, false, 0);
        MagazineRepository magazineRepository = new MagazineRepository(connection);
        MagazineController magazineController = new MagazineController(new MagazineService(magazineRepository));
        magazineController.createBook(magazine, connection);

        return magazine;
    }

    private static ConcreteAlbum createAlbum(Connection connection) {
        id = findLastId(connection) + 1;
        System.out.print("Enter album title: ");
        title = scanner.nextLine();
        pageCount = getUserInputInt("Enter album page count: ");

        Album album = new ConcreteAlbum(id, title, pageCount);
        AlbumRepository albumRepository = new AlbumRepository(connection);
        AlbumController albumController = new AlbumController(new AlbumService(albumRepository));
        albumController.createAlbum(album, 0);

        return (ConcreteAlbum) album;
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
