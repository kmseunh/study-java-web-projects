import com.example.project.util.DBUtil;
import java.util.logging.Logger;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBUtilTest {

    private static final Logger LOGGER = Logger.getLogger(DBUtilTest.class.getName());

    @Test
    public void testDatabaseConnection() {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            assertNotNull("Database connection should not be null", connection);

            assertTrue("Database connection should be valid", connection.isValid(5));

        } catch (SQLException e) {
            LOGGER.severe("Failed to connect to the database: " + e.getMessage());
            fail("Failed to connect to the database: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.warning("Failed to close the database connection: " + e.getMessage());
                }
            }
        }
    }
}
