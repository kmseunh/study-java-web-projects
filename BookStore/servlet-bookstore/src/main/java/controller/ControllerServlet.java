package controller;

import dao.BookDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;

public class ControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertBook(request, response);
                    break;
                case "/delete":
                    deleteBook(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateBook(request, response);
                    break;
                case "/search":
                    searchBook(request, response);
                    break;
                case "/list":
                default:
                    listBook(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        List<Book> listBook = bookDAO.listAllBooks();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookList.jsp");
        System.out.println("데이터 가져옴");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookDAO.getBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);

    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        Book newBook = new Book(title, author, price);
        bookDAO.insertBook(newBook);
        response.sendRedirect("list");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        float price = Float.parseFloat(request.getParameter("price"));

        Book book = new Book(id, title, author, price);
        bookDAO.updateBook(book);
        response.sendRedirect("list");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Book book = new Book(id);
        bookDAO.deleteBook(book);
        response.sendRedirect("list");

    }

    private void searchBook(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
        String keyword = request.getParameter("keyword");
        List<Book> listBook = bookDAO.searchBooks(keyword);

        // 검색 결과를 request 객체에 저장합니다.
        request.setAttribute("listBook", listBook);

        // 검색 결과 페이지로 forward 합니다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookList.jsp");
        dispatcher.forward(request, response);
    }

}