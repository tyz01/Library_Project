package by.tyzcorporation.library.service.db.bisneslogic;

import by.tyzcorporation.library.model.entity.Book;
import by.tyzcorporation.library.service.db.repository.BookRepository;
import by.tyzcorporation.library.service.db.repository.PublicationRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    public void createBook(Book book, Connection connection) {
        try {
            PublicationRepository publicationRepository = new PublicationRepository(connection);
            int publicationId = publicationRepository.insertIntoDatabase(book);
            bookRepository.create(book, publicationId);
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating book", e);
        }
    }

    public void getAll() {
        try {
            List<Book> books = bookRepository.getAll();
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all books", e);
        }
    }

    public Book getBookById(Integer idBook) {
        try {
            return bookRepository.getEntityById(idBook);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeBook(int idPublication) {
        try {
            bookRepository.delete(idPublication);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
