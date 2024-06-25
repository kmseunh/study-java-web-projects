package util;

import java.sql.Connection;
import java.sql.SQLException;
import junit.framework.TestCase;

public class DBUtilTest extends TestCase {

    private DBUtil dbUtil;

    protected void setUp() throws Exception {
        super.setUp();
        // 테스트 전 초기화 작업
        String jdbcURL = "jdbc:mariadb://localhost:3306/study";
        String jdbcUsername = "root";
        String jdbcPassword = "1111";

        dbUtil = new DBUtil(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        // 테스트 후 정리 작업
        dbUtil = null;
    }

    public void testConnection() {
        try {
            Connection conn = dbUtil.getConnection();
            assertNotNull("Connection should not be null", conn);
            conn.close(); // 연결 닫기
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }
}
