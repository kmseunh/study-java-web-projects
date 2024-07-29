package com.example.bookstore.dao;

import com.example.bookstore.model.Book;
import com.example.bookstore.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookStoreDAO {

    private static final Logger LOGGER = Logger.getLogger(BookStoreDAO.class.getName());


    public boolean insertBook(Book book) {
        String query = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)
        ) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setFloat(3, book.getPrice());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in insertBook", e);
            return false;
        }
    }

    public List<Book> listAllBooks() {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM book";

        try (Connection con = DBUtil.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                float price = rs.getFloat("price");

                bookList.add(new Book(id, title, author, price));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in listAllBooks", e);
        }
        return bookList;
    }

    public boolean updateBook(Book book) {
        String query = "UPDATE book SET title = ?, author = ?, price = ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
        ) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setFloat(3, book.getPrice());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in updateBook", e);
            return false;
        }
    }

    public boolean deleteBook(int id) {
        String query = "DELETE FROM book WHERE book_id = ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
        ) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in deleteBook", e);
            return false;
        }
    }

    public Book getBook(int id) {
        String query = "SELECT * FROM book WHERE book_id = ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)
        ) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    float price = rs.getFloat("price");

                    return new Book(title, author, price);
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error in getBook", e);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in getBook", e);
        }
        return null;
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> bookList = new ArrayList<>();
        String query = "SELECT * FROM book WHERE title LIKE ? OR author LIKE ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)
        ) {
            String searchKeyword = "%" + keyword.trim() + "%";
            pstmt.setString(1, searchKeyword);
            pstmt.setString(2, searchKeyword);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("book_id");
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    float price = rs.getFloat("price");

                    bookList.add(new Book(id, title, author, price));
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error in searchBooks", e);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error in searchBooks", e);
        }
        return bookList;
    }
}
