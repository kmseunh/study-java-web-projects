import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.example.board.util.DBUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

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
            LOGGER.log(Level.SEVERE, "데이터베이스 연결 실패: " + e.getMessage());
            fail("데이터베이스 연결 실패: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.warning("데이터베이스 연결 실패: " + e.getMessage());
                }
            }
        }
    }
}
