package by.tyzcorporation.library.service.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibraryController {
    private final Connection connection;

    public LibraryController(Connection connection) throws InterruptedException {
        this.connection = connection;
    }
    public void insertIntoLibrary(int publicationId) throws SQLException {
        String insertIntoLibrary = "INSERT INTO Library (publicationId) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertIntoLibrary)) {
            preparedStatement.setInt(1, publicationId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}
