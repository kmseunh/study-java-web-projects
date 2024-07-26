package com.example.board.dao;

import com.example.board.model.Board;
import com.example.board.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardDAO {

    private static final Logger LOGGER = Logger.getLogger(BoardDAO.class.getName());

    public boolean addBoard(Board board) {
        String query = "INSERT INTO board (title, writer, content) VALUES (?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getWriter());
            pstmt.setString(3, board.getContent());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in addBoard", e);
            return false;
        }
    }

    public List<Board> boardList() {
        List<Board> boardList = new ArrayList<>();
        String query = "SELECT * FROM board";

        try (Connection con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int num = rs.getInt("num");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String content = rs.getString("content");
                int cnt = rs.getInt("cnt");
                Timestamp regDate = rs.getTimestamp("regdate");
                Timestamp lastModified = rs.getTimestamp("last_modified");

                boardList.add(new Board(num, title, writer, content, cnt, regDate, lastModified));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in boardList", e);
        }
        return boardList;
    }

    public Board getBoardOne(int num) {
        String query = "SELECT * FROM board WHERE num = ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, num);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String writer = rs.getString("writer");
                    String content = rs.getString("content");
                    int cnt = rs.getInt("cnt");
                    Timestamp regDate = rs.getTimestamp("regdate");
                    Timestamp lastModified = rs.getTimestamp("last_modified");

                    return new Board(num, title, writer, content, cnt, regDate, lastModified);
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error in getBoardOne", e);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getBoardOne", e);
        }
        return null;
    }

    public boolean modBoard(Board board) {
        String query = "UPDATE board SET title = ?, writer = ?, content = ? WHERE num = ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getWriter());
            pstmt.setString(3, board.getContent());
            pstmt.setInt(4, board.getNum());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in modBoard", e);
            return false;
        }
    }

    public boolean delBoard(int num) {
        String query = "DELETE FROM board WHERE num = ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, num);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in delBoard", e);
            return false;
        }
    }

    public boolean increaseViewCount(int num) {
        String query = "UPDATE board SET cnt = cnt + 1 WHERE num = ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, num);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in increaseViewCount", e);
            return false;
        }
    }
}