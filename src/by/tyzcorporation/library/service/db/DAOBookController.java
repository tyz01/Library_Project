package by.tyzcorporation.library.service.db;

import by.tyzcorporation.library.model.entity.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DAOBookController extends AbstractController<Book, Integer> {
    private final ConnectionPool connectionPool;
    private final Connection connection;
    private final BookController bookController;
    private final LibraryController libraryController;
    private final PublicationController publicationController;

    public DAOBookController(ConnectionPool connectionPool) throws InterruptedException {
        this.connectionPool = connectionPool;
        this.connection = connectionPool.getConnection();
        this.bookController = new BookController(connection);
        this.libraryController = new LibraryController(connection);
        this.publicationController = new PublicationController(connection);
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book update(Book entity) {
        return null;
    }

    @Override
    public Book getEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Book entity) {
        return false;
    }

    @Override
    public int insertIntoDatabase(Book book) throws SQLException {
        int idPublication;

        try {
            connection.setAutoCommit(false);

            idPublication = publicationController.insertPublication(book);
            libraryController.insertIntoLibrary(idPublication);
            bookController.insertBook(book, idPublication);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            returnConnectionInPool();
        }

        return idPublication;
    }

    public void returnConnectionInPool() {
        connectionPool.releaseConnection(connection);
    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connectionPool.releaseConnection(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
