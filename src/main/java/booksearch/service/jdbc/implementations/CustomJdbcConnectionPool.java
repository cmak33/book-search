package booksearch.service.jdbc.implementations;

import booksearch.model.jdbc.JdbcConnectionPoolInfo;
import booksearch.service.jdbc.interfaces.JdbcConnectionPool;
import booksearch.service.sql.implementations.CustomEntitySqlExecutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomJdbcConnectionPool implements JdbcConnectionPool {

    private final List<Connection> availableConnections;

    private CustomJdbcConnectionPool() {
        availableConnections = new ArrayList<>();
        //JdbcConnectionPoolInfo
        initializeConnections(info);
    }

    public static CustomJdbcConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomJdbcConnectionPool INSTANCE = new CustomJdbcConnectionPool();
    }


    private void initializeConnections(JdbcConnectionPoolInfo info) throws SQLException {
        for (int count = 0; count < info.initialConnections(); count++) {
            DriverManager.getConnection(info.url(), info.user(), info.password());
        }
    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        if (availableConnections.isEmpty()) {
            throw new SQLException("No available connections");
        } else {
            return availableConnections.remove(availableConnections.size() - 1);
        }
    }

    @Override
    public synchronized void releaseConnection(Connection connection) {
        availableConnections.add(connection);
    }
}
