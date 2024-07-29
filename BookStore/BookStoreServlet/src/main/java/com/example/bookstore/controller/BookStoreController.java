package com.example.bookstore.controller;

import com.example.bookstore.dao.BookStoreDAO;
import com.example.bookstore.model.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/book/*")
public class BookStoreController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BookStoreController.class.getName());
    private BookStoreDAO bookStoreDAO;

    @Override
    public void init() {
        bookStoreDAO = new BookStoreDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        doHandle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        doHandle(req, resp);
    }

    private void doHandle(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getPathInfo();
        LOGGER.log(Level.SEVERE, "Received action: {0}", action);

        try {
            switch (action) {
                case "/insertBook.do":
                    insertBook(req, resp);
                    break;
                case "/deleteBook.do":
                    deleteBook(req, resp);
                    break;
                case "/showBookForm.do":
                    showBookForm(req, resp);
                    break;
                case "/showUpdateBook.do":
                    showUpdateBook(req, resp);
                    break;
                case "/updateBook.do":
                    updateBook(req, resp);
                    break;
                case "/search":
                    searchBook(req, resp);
                    break;
                case "/listBook.do":
                default:
                    listBook(req, resp);
                    break;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception in handling action: " + action, e);
        }
    }

    private void listBook(HttpServletRequest req, HttpServletResponse resp)
        throws SQLException, IOException {
        try {
            List<Book> bookList = bookStoreDAO.listAllBooks();
            req.setAttribute("bookList", bookList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/book/listBook.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in listBook", e);
        }
    }

    private void insertBook(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            float price = Float.parseFloat(req.getParameter("price"));

            Book book = new Book(title, author, price);
            bookStoreDAO.insertBook(book);
            resp.sendRedirect(req.getContextPath() + "/book/listBook.do");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in insertBook", e);
        }
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            bookStoreDAO.deleteBook(id);
            resp.sendRedirect(req.getContextPath() + "/book/listBook.do");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in deleteBook", e);
        }
    }

    private void showBookForm(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            req.setAttribute("formTitle", "책 등록");
            req.setAttribute("formAction", "insertBook");
            req.setAttribute("submitButtonText", "등록");
            req.setAttribute("bookInfo", new Book("", "", 0.0f));

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/book/bookForm.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in showBookForm", e);
        }
    }

    private void showUpdateBook(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));

            Book bookInfo = bookStoreDAO.getBook(id);
            req.setAttribute("formTitle", "책 수정");
            req.setAttribute("formAction", "updateBook");
            req.setAttribute("submitButtonText", "수정");
            req.setAttribute("bookInfo", bookInfo);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/book/bookForm.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in showUpdateBook", e);
        }
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            float price = Float.parseFloat(req.getParameter("price"));

            Book book = new Book(id, title, author, price);
            bookStoreDAO.updateBook(book);
            resp.sendRedirect(req.getContextPath() + "/book/listBook.do");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in updateBook", e);
        }
    }

    private void searchBook(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            String keyword = req.getParameter("keyword");
            List<Book> bookList = bookStoreDAO.searchBooks(keyword);

            req.setAttribute("bookList", bookList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/book/listBook.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in searchBook", e);
        }
    }
}
