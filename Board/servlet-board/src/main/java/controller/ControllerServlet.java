package controller;

import dao.BoardDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Board;

public class ControllerServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ControllerServlet.class.getName());
    private static final long serialVersionUID = 1L;
    private BoardDao boardDao;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        try {
            boardDao = new BoardDao(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Cannot initialize BoardDao", e);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    insertBoard(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateBoard(req, resp);
                    break;
                case "/delete":
                    deleteBoard(req, resp);
                    break;
                case "/detail":
                    showBoardDetail(req, resp);
                    break;
                default:
                    listBoard(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listBoard(HttpServletRequest req, HttpServletResponse resp)
        throws SQLException, ServletException, IOException {
        List<Board> listBoard = boardDao.listAllBoards();
        req.setAttribute("listBoard", listBoard);
        RequestDispatcher dispatcher = req.getRequestDispatcher("board-list.jsp");
        dispatcher.forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("board-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        int num = Integer.parseInt(req.getParameter("num"));
        Board existngBoard = boardDao.getBoard(num);
        RequestDispatcher dispatcher = req.getRequestDispatcher("board-form.jsp");
        req.setAttribute("board", existngBoard);
        dispatcher.forward(req, resp);
    }

    private void insertBoard(HttpServletRequest req, HttpServletResponse resp)
        throws SQLException, ServletException, IOException {
        String title = req.getParameter("title");
        String writer = req.getParameter("writer");
        String content = req.getParameter("content");

        boardDao.addBoard(title, writer, content);
        resp.sendRedirect("list");
    }

    private void updateBoard(HttpServletRequest req, HttpServletResponse resp)
        throws SQLException, ServletException, IOException {
        int num = Integer.parseInt(req.getParameter("num"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        boardDao.updateBoard(num, title, content);
        resp.sendRedirect("list");
    }

    private void deleteBoard(HttpServletRequest req, HttpServletResponse resp)
        throws SQLException, ServletException, IOException {
        int num = Integer.parseInt(req.getParameter("num"));

        boardDao.deleteBoard(num);
        resp.sendRedirect("list");
    }

    private void showBoardDetail(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
        int num = Integer.parseInt(request.getParameter("num"));

        boolean viewCountUpdated = boardDao.increaseViewCount(num);

        Board board = boardDao.getBoard(num);

        // 게시물 데이터를 request에 설정
        request.setAttribute("board", board);

        RequestDispatcher dispatcher = request.getRequestDispatcher("board-detail.jsp");
        dispatcher.forward(request, response);
    }
}
