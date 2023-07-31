package by.tyzcorporation.library.service.db.repository;

import by.tyzcorporation.library.model.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository extends AbstractRepository<Book, Integer> {
    private final Connection connection;

    public BookRepository(Connection connection) {
        this.connection = connection;
    }

    //    public List<Book> getAll() throws SQLException {
//        List<Book> books = new ArrayList<>();
//        String getAllBooksQuery = "SELECT * FROM Book";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(getAllBooksQuery);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            while (resultSet.next()) {
//                int bookId = resultSet.getInt("idBook");
//                String title = resultSet.getString("title");
//                int pageCount = resultSet.getInt("pageCount");
//                String author = resultSet.getString("author");
//                String genre = resultSet.getString("genre");
//                boolean borrow = resultSet.getBoolean("borrow");
//                int countBorrowPublication = resultSet.getInt("countBorrowPublication");
//
//                Book book = new Book(bookId, title, pageCount, author, genre, borrow, countBorrowPublication);
//                books.add(book);
//            }
//        } catch (SQLException e) {
//            throw new SQLException("Error while getting all books", e);
//        }
//
//        return books;
//    }
    @Override
    public List<Book> getAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        String getAllBooksQuery = "SELECT idBook, title, pageCount, author, genre, borrow, countBorrowPublication FROM Book";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getAllBooksQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int bookId = resultSet.getInt("idBook");
                String title = resultSet.getString("title");
                int pageCount = resultSet.getInt("pageCount");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                boolean borrow = resultSet.getBoolean("borrow");
                int countBorrowPublication = resultSet.getInt("countBorrowPublication");

                Book book = new Book(bookId, title, pageCount, author, genre, borrow, countBorrowPublication);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new SQLException("Error while getting all books", e);
        }

        return books;
    }

    @Override
    public Book update(Book book) throws SQLException {
        String updateBookQuery = "UPDATE Book " +
                "SET title = ?, pageCount = ?, author = ?, genre = ?, borrow = ?, countBorrowPublication = ? " +
                "WHERE idBook = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateBookQuery)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getPageCount());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getGenre());
            preparedStatement.setBoolean(5, book.isBorrow());
            preparedStatement.setInt(6, book.getCountBorrowPublication());
            preparedStatement.setInt(7, book.getIdPublication());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return book;
            }
        } catch (SQLException e) {
            throw e;
        }

        return null;
    }

    @Override
    public Book getEntityById(Integer id) throws SQLException {
        String getBookByIdQuery = "SELECT * FROM Book WHERE idBook = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getBookByIdQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int bookId = resultSet.getInt("idBook");
                String title = resultSet.getString("title");
                int pageCount = resultSet.getInt("pageCount");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                boolean borrow = resultSet.getBoolean("borrow");
                int countBorrowPublication = resultSet.getInt("countBorrowPublication");

                return new Book(bookId, title, pageCount, author, genre, borrow, countBorrowPublication);
            }
        } catch (SQLException e) {
            throw e;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        String deleteBookQuery = "DELETE FROM Book WHERE idBook = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookQuery)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public int create(Book book, Integer publicationId) throws SQLException {
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



