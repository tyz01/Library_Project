package by.tyzcorporation.library.service.utility.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtility {
    public static int findLastId(Connection connection) {
        String maxId = "SELECT MAX(idPublication) AS maxIdPublication FROM Publication";
        int maxIdPublication = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(maxId);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                maxIdPublication = resultSet.getInt("maxIdPublication");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maxIdPublication;
    }
}
