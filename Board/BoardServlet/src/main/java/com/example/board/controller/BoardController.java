package com.example.board.controller;

import com.example.board.dao.BoardDAO;
import com.example.board.model.Board;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(BoardController.class.getName());
    private static final long serialVersionUID = 1L;
    private BoardDAO boardDAO;

    @Override
    public void init() throws ServletException {
        boardDAO = new BoardDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        doHandle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        doHandle(req, resp);
    }

    private void doHandle(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getPathInfo();
        LOGGER.log(Level.INFO, "Received action: {0}", action);

        try {
            switch (action) {
                case "/listBoard.do":
                    listBoard(req, resp);
                    break;
                case "/addBoard.do":
                    addBoard(req, resp);
                    break;
                case "/showBoardForm.do":
                    showBoardForm(req, resp);
                    break;
                case "/showModBoardForm.do":
                    showModBoardForm(req, resp);
                    break;
                case "/modBoard.do":
                    modBoard(req, resp);
                    break;
                case "/delBoard.do":
                    delBoard(req, resp);
                    break;
                case "/detailBoard.do":
                    showBoardDetail(req, resp);
                    break;
                default:
                    listBoard(req, resp);
                    break;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception in handling action: " + action, e);
        }
    }

    private void listBoard(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            List<Board> boardList = boardDAO.boardList();
            req.setAttribute("boardList", boardList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/board/listBoard.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in listBoard", e);
        }
    }

    private void addBoard(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            String title = req.getParameter("title");
            String writer = req.getParameter("writer");
            String content = req.getParameter("content");

            Board board = new Board(title, writer, content);
            boardDAO.addBoard(board);
            resp.sendRedirect(req.getContextPath() + "/board/listBoard.do");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in addBoard", e);
        }
    }

    private void showBoardForm(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            req.setAttribute("formTitle", "게시글 등록");
            req.setAttribute("formAction", "addBoard");
            req.setAttribute("submitButtonText", "등록");
            req.setAttribute("boardInfo", new Board("", "", ""));

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/board/boardForm.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in showBoardForm", e);
        }
    }

    private void showModBoardForm(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            int num = Integer.parseInt(req.getParameter("num"));

            Board boardInfo = boardDAO.getBoardOne(num);
            req.setAttribute("formTitle", "게시글 수정");
            req.setAttribute("formAction", "modBoard");
            req.setAttribute("submitButtonText", "수정");
            req.setAttribute("boardInfo", boardInfo);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/board/boardForm.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in showModBoardForm", e);
        }
    }

    private void modBoard(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            int num = Integer.parseInt(req.getParameter("num"));
            String title = req.getParameter("title");
            String writer = req.getParameter("writer");
            String content = req.getParameter("content");

            Board board = new Board(num, title, writer, content);
            boardDAO.modBoard(board);
            resp.sendRedirect(req.getContextPath() + "/board/listBoard.do");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in modBoard", e);
        }
    }

    private void delBoard(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            int num = Integer.parseInt(req.getParameter("num"));

            boardDAO.delBoard(num);
            resp.sendRedirect(req.getContextPath() + "/board/listBoard.do");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in delBoard", e);
        }
    }

    private void showBoardDetail(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        try {
            int num = Integer.parseInt(req.getParameter("num"));

            boardDAO.increaseViewCount(num);
            Board board = boardDAO.getBoardOne(num);

            req.setAttribute("board", board);
            RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/board/boardDetail.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error in showBoardDetail", e);
        }
    }
}