package by.tyzcorporation.library.service.db.connector;

import by.tyzcorporation.library.service.logger.MyLogger;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PosgresConnector {
    private static final Logger logger = MyLogger.getMyLogger();
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5433/library";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection connect() {
        logger.info("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "PostgreSQL JDBC Driver is not found: ", e);
        }

        logger.info("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Connection Failed: ", e);
        }
        if (connection != null) {
            logger.info("You successfully connected to database now");
        } else {
            logger.log(Level.WARNING, "Failed to make connection to database");
        }
        return connection;
    }
}
