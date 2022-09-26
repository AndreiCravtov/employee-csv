package com.sparta.dao.sql;

import java.io.FileReader;
import java.io.IOException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Maintains a maximum pool size of {@code POOL_CAPACITY} connections
 */
class ConnectionPool implements AutoCloseable {
    private static ConnectionPool instance;

    private static final int POOL_CAPACITY = 150;

    private final BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(POOL_CAPACITY);

    private ConnectionPool() {
        String url;
        String username;
        String password;

        // get properties
        Properties props = new Properties();
        try {
            props.load(new FileReader("src/main/resources/dbconnect.properties"));
            url = props.getProperty("mysql.url");
            username = props.getProperty("mysql.username");
            password = props.getProperty("mysql.password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // initialize connections
        try {
            for (int i=0; i<POOL_CAPACITY; i++)
                pool.offer(DriverManager.getConnection(url, username, password));
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    static ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    Connection borrowConnection() {
        Connection conn;
        while ((conn = pool.poll()) == null); // pause execution until connection obtained
        return conn;
    }

    void returnConnection(Connection connection) {
        pool.offer(connection);
    }

    @Override
    public void close() throws SQLException {
        for (Connection conn: pool)
            conn.close();
    }
}
