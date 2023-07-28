package by.tyzcorporation.library.service.db;

import by.tyzcorporation.library.model.entity.Book;
import by.tyzcorporation.library.service.db.repository.BookRepository;
import by.tyzcorporation.library.service.db.repository.PublicationRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class PoolController {

    public static void managePool() {
        ConnectionPool pool = new ConnectionPool(5);
        pool.initializePool();

        try {
            Connection connection = pool.getConnection();
//            BookRepository bookRepository = new BookRepository(connection);
//
//            PublicationRepository publicationController = new PublicationRepository(connection);
//            Book book = new Book("Book1", 1111, "Best1", "fantasy", false, 0);
//            int publicationId = publicationController.insertIntoDatabase(book, book.getIdPublication());
//
//            bookRepository.insertIntoDatabase(book, publicationId);
//            bookRepository.getAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
        } finally {
            pool.closeAllConnections();
        }
    }
}
