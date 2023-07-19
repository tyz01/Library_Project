package by.tyzcorporation.library.service.db;

import by.tyzcorporation.library.model.entity.Book;

import java.sql.*;
import java.util.List;

public class BookController  {
    private final Connection connection;

    public BookController(Connection connection) {
        this.connection = connection;
    }

    public List<Book> getAll() {
        return null;
    }
    public Book update(Book entity) {
        return null;
    }

    public Book getEntityById(Integer id) {
        return null;
    }
    public boolean delete(Integer id) {
        return false;
    }
    public boolean create(Book entity) {
        return false;
    }

    public int insertIntoDatabase(Book entity) {
        return 0;
    }

    public int insertBook(Book book, int publicationId) throws SQLException {
        String insertBookQuery = "INSERT INTO Book (title, pageCount, author, genre, borrow, countBorrowPublication, publicationId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertBookQuery)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getPageCount());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setBoolean(5, book.isBorrow());
            preparedStatement.setInt(6, book.getCountBorrowPublication());
            preparedStatement.setInt(7, publicationId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        return publicationId;
    }

}



