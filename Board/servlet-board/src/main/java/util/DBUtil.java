package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Db util.
 */
public class DBUtil {

    private static final Logger LOGGER = Logger.getLogger(DBUtil.class.getName());

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;

    /**
     * Instantiates a new Db util.
     *
     * @param jdbcURL      the jdbc url
     * @param jdbcUsername the jdbc username
     * @param jdbcPassword the jdbc password
     */
    public DBUtil(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     * @throws SQLException the sql exception
     */
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE, "Error connecting to the database", e);
            throw new SQLException("Unable to connect to database", e);
        }
    }
}
