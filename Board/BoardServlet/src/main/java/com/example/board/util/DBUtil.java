package com.example.board.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtil {

    private static final Logger LOGGER = Logger.getLogger(DBUtil.class.getName());
    private static HikariDataSource dataSource;

    static {
        try {
            initializeDataSource();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "데이터 소스 초기화 중 오류 발생", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void initializeDataSource() throws ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/study");
        config.setUsername("kmseunh");
        config.setPassword("1111");

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("DataSource is not initialized");
        }
        return dataSource.getConnection();
    }
}
