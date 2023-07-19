package by.tyzcorporation.library.service.db;

import by.tyzcorporation.library.service.db.connector.PosgresConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private final int maxConnections;
    private final BlockingQueue<Connection> connections;

    public ConnectionPool(int maxConnections) {
        this.maxConnections = maxConnections;
        this.connections = new ArrayBlockingQueue<>(maxConnections);
    }

    public Connection getConnection() throws InterruptedException {
        return connections.take();
    }

    public void releaseConnection(Connection connection) {
        connections.add(connection);
    }

    public void initializePool() {
        for (int i = 0; i < maxConnections; i++) {
            Connection connection = createNewConnection();
            connections.add(connection);
        }
    }

    public void closeAllConnections() {
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connections.clear();
    }

    private Connection createNewConnection() {
        return PosgresConnector.connect();
    }
}

