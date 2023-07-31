package by.tyzcorporation.library.service.db.repository;

import by.tyzcorporation.library.model.entity.Magazine;
import by.tyzcorporation.library.model.exception.technical.IncorrectSQLException;
import by.tyzcorporation.library.service.annotation.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MagazineRepository extends AbstractRepository<Magazine, Integer> {
    private final Connection connection;

    public MagazineRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Magazine> getAll() throws SQLException {
        List<Magazine> magazines = new ArrayList<>();

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
                magazines.add(magazine);
            }
        } catch (SQLException e) {
            throw e;
        }

        return magazines;
    }

    @Override
    public Magazine update(Magazine magazine) {
        String updateBookQuery = "UPDATE Magazine " +
                "SET title = ?, pageCount = ?, genre = ?, borrow = ?, countBorrowPublication = ? " +
                "WHERE idMagazine = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateBookQuery)) {
            preparedStatement.setString(1, magazine.getTitle());
            preparedStatement.setInt(2, magazine.getPageCount());
            preparedStatement.setString(3, magazine.getGenre());
            preparedStatement.setBoolean(4, magazine.isBorrow());
            preparedStatement.setInt(5, magazine.getCountBorrowPublication());
            preparedStatement.setInt(6, magazine.getIdPublication());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return magazine;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        throw new IncorrectSQLException("update magazine failed");
    }

    @Override
    public Magazine getEntityById(Integer id) {
        String getBookByIdQuery = "SELECT * FROM Magazine WHERE idMagazine = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getBookByIdQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int magazineId = resultSet.getInt("idMagazine");
                String title = resultSet.getString("title");
                int pageCount = resultSet.getInt("pageCount");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                boolean borrow = resultSet.getBoolean("borrow");
                int countBorrowPublication = resultSet.getInt("countBorrowPublication");

                return new Magazine(magazineId, title, pageCount, author, genre, borrow, countBorrowPublication);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        throw new IncorrectSQLException("get magazine failed");
    }


    @Override
    public boolean delete(Integer id) {
        String deleteBookQuery = "DELETE FROM Magazine WHERE idMagazine = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteBookQuery)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        throw new IncorrectSQLException("delete magazine failed");
    }

    @Override
    public int create(Magazine magazine, Integer publicationId) throws SQLException {
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