package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Board;
import util.DBUtil;


/**
 * The type Board dao.
 */
public class BoardDao {

    private static final Logger LOGGER = Logger.getLogger(BoardDao.class.getName());

    private DBUtil dbUtil;


    /**
     * Instantiates a new Board dao.
     *
     * @param jdbcURL      the jdbc url
     * @param jdbcUsername the jdbc username
     * @param jdbcPassword the jdbc password
     * @throws SQLException the sql exception
     */
    public BoardDao(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        this.dbUtil = new DBUtil(jdbcURL, jdbcUsername, jdbcPassword);
    }


    /**
     * Add board boolean.
     *
     * @param title   the title
     * @param writer  the writer
     * @param content the content
     * @return the boolean
     */
    public boolean addBoard(String title, String writer, String content) {
        if (title == null || writer == null) {
            throw new IllegalArgumentException("Title and writer must not be null");
        }

        String sql = "INSERT INTO board (title, writer, content) VALUES (?, ?, ?)";

        try (
            Connection conn = dbUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, title);
            pstmt.setString(2, writer);
            pstmt.setString(3, content);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting board data", e);
            return false;
        }
    }


    /**
     * List all boards list.
     *
     * @return the list
     */
    public List<Board> listAllBoards() {
        List<Board> listBoard = new ArrayList<>();
        String sql = "SELECT * FROM board";

        try (Connection conn = dbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                int num = rs.getInt("num");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String content = rs.getString("content");
                Timestamp regdate = rs.getTimestamp("regdate");
                Timestamp lastModified = rs.getTimestamp("last_modified");
                int cnt = rs.getInt("cnt");

                listBoard.add(new Board(num, title, writer, content, regdate, lastModified, cnt));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error selecting boardList data", e);
        }
        return listBoard;
    }


    /**
     * Gets board.
     *
     * @param num the num
     * @return the board
     */
    public Board getBoard(int num) {
        String sql = "SELECT * from board where num = ?";

        try (Connection conn = dbUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, num);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String writer = rs.getString("writer");
                    String content = rs.getString("content");
                    Timestamp regdate = rs.getTimestamp("regdate");
                    Timestamp lastModified = rs.getTimestamp("last_modified");
                    int cnt = rs.getInt("cnt");

                    return new Board(num, title, writer, content, regdate, lastModified, cnt);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error selecting board data", e);
        }
        return null;
    }


    /**
     * Update board boolean.
     *
     * @param num     the num
     * @param title   the title
     * @param content the content
     * @return the boolean
     */
    public boolean updateBoard(int num, String title, String content) {
        String sql = "UPDATE board SET title = ?, content = ? where num = ?";

        try (Connection conn = dbUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setInt(3, num);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error update board data", e);
            return false;
        }
    }


    /**
     * Delete board boolean.
     *
     * @param num the num
     * @return the boolean
     */
    public boolean deleteBoard(int num) {
        String sql = "DELETE FROM board where num = ?";

        try (Connection conn = dbUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, num);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error delete board data", e);
            return false;
        }
    }

    /**
     * Increase view count boolean.
     *
     * @param num the num
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public boolean increaseViewCount(int num) throws SQLException {
        String sql = "UPDATE board SET cnt = cnt + 1 WHERE num = ?";

        try (Connection conn = dbUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, num);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error increase view count", e);
            return false;
        }
    }
}
