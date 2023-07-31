package by.tyzcorporation.library.service.db.repository;

import by.tyzcorporation.library.service.db.connector.ConnectionPool;
import by.tyzcorporation.library.service.db.abstarction.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractRepository<E, K> implements Controller<E, K> {
    private ConnectionPool connectionPool;
    private Connection connection;

    public AbstractRepository(ConnectionPool connectionPool) throws InterruptedException {
        this.connectionPool = connectionPool;
        this.connection = connectionPool.getConnection();
    }

    public AbstractRepository() {
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