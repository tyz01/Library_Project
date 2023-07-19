package by.tyzcorporation.library.service.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractController<E, K> implements Controller<E, K> {
    private ConnectionPool connectionPool;
    private Connection connection;

    public AbstractController(ConnectionPool connectionPool) throws InterruptedException {
        this.connectionPool = connectionPool;
        this.connection = connectionPool.getConnection();
    }

    public AbstractController() {
    }

    public void returnConnectionInPool() {
        connectionPool.releaseConnection(connection);
    }

    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}