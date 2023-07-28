package by.tyzcorporation.library.service.db.repository;

import by.tyzcorporation.library.model.entity.Magazine;
import by.tyzcorporation.library.service.db.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MagazineRepository extends AbstractRepository<Magazine, Integer> {
    private final Connection connection;

    public MagazineRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Magazine> getAll() throws SQLException {
        List<Magazine> books = new ArrayList<>();

        String getAllBooksQuery = "SELECT * FROM Magazine";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getAllBooksQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int magazineId = resultSet.getInt("idMagazine");
                String title = resultSet.getString("title");
                int pageCount = resultSet.getInt("pageCount");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                boolean borrow = resultSet.getBoolean("borrow");
                int countBorrowPublication = resultSet.getInt("countBorrowPublication");

                Magazine magazine = new Magazine(magazineId, title, pageCount, author, genre, borrow, countBorrowPublication);
                System.out.println(magazine);
                books.add(magazine);
            }
        } catch (SQLException e) {
            throw e;
        }

        return books;
    }

    @Override
    public Magazine update(Magazine entity) {
        return null;
    }

    @Override
    public Magazine getEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Magazine entity) {
        return false;
    }

    @Override
    public int insertIntoDatabase(Magazine magazine, Integer publicationId) throws SQLException {
        String insertBookQuery = "INSERT INTO Magazine (title, pageCount, author, genre, borrow, countBorrowPublication, publicationId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertBookQuery)) {
            preparedStatement.setString(1, magazine.getTitle());
            preparedStatement.setInt(2, magazine.getPageCount());
            preparedStatement.setString(3, magazine.getCategory());
            preparedStatement.setString(4, magazine.getGenre());
            preparedStatement.setBoolean(5, magazine.isBorrow());
            preparedStatement.setInt(6, magazine.getCountBorrowPublication());
            preparedStatement.setInt(7, publicationId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        return publicationId;

    }

}