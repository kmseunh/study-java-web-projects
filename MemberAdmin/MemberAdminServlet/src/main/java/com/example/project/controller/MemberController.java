package com.example.project.controller;

import com.example.project.dao.MemberDAO;
import com.example.project.vo.MemberVO;
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

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MemberController.class.getName());
    private static final long serialVersionUID = 1L;
    private MemberDAO memberDAO;

    @Override
    public void init() throws ServletException {
        memberDAO = new MemberDAO();
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
        throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String action = req.getPathInfo();
        LOGGER.log(Level.INFO, "Received action: {0}", action);

        try {
            switch (action) {
                case "/listMembers.do":
                    listMembers(req, resp);
                    break;
                case "/memberForm.do":
                    showMemberForm(req, resp);
                    break;
                case "/addMember.do":
                    addMember(req, resp);
                    break;
                case "/showModMember.do":
                    showModMemberForm(req, resp);
                    break;
                case "/modMember.do":
                    modMember(req, resp);
                    break;
                case "/delMember.do":
                    delMember(req, resp);
                    break;
                default:
                    listMembers(req, resp);
                    break;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Exception in handling action: " + action, e);
            handleError(req, resp, e);
        }
    }

    private void listMembers(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        try {
            List<MemberVO> memberList = memberDAO.listMembers();
            req.setAttribute("memberList", memberList);

            RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/member/listMembers.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error listing members", e);
            handleError(req, resp, e);
        }
    }

    private void addMember(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        try {
            String id = req.getParameter("id");
            String pwd = req.getParameter("pwd");
            String name = req.getParameter("name");
            String email = req.getParameter("email");

            MemberVO memberVO = new MemberVO(id, pwd, name, email);
            memberDAO.addMember(memberVO);
            resp.sendRedirect(req.getContextPath() + "/member/listMembers.do?msg=added");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding member", e);
            handleError(req, resp, e);
        }
    }

    private void showMemberForm(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        try {
            req.setAttribute("formTitle", "회원 가입");
            req.setAttribute("formAction", "addMember");
            req.setAttribute("submitButtonText", "가입");
            req.setAttribute("memberInfo",
                new MemberVO("", "", "", "")); // Empty member info for new members
            req.setAttribute("readonly", ""); // Not read-only for adding

            RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/member/memberForm.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error showing member form", e);
            handleError(req, resp, e);
        }
    }

    private void showModMemberForm(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        try {
            String id = req.getParameter("id");

            MemberVO memberInfo = memberDAO.findMember(id);
            req.setAttribute("formTitle", "회원 정보 수정");
            req.setAttribute("formAction", "modMember");
            req.setAttribute("submitButtonText", "수정");
            req.setAttribute("memberInfo", memberInfo);
            req.setAttribute("readonly", "readonly")
            ; // Read-only for modifying, if needed
            RequestDispatcher dispatcher = req.getRequestDispatcher(
                "/WEB-INF/member/memberForm.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error showing modify member form", e);
            handleError(req, resp, e);
        }
    }

    private void modMember(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        try {
            String id = req.getParameter("id");
            String pwd = req.getParameter("pwd");
            String name = req.getParameter("name");
            String email = req.getParameter("email");

            MemberVO memberVO = new MemberVO(id, pwd, name, email);
            memberDAO.updateMember(memberVO);
            resp.sendRedirect(req.getContextPath() + "/member/listMembers.do?msg=updated");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error modifying member", e);
            handleError(req, resp, e);
        }
    }

    private void delMember(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        try {
            String id = req.getParameter("id");

            memberDAO.deleteMember(id);
            resp.sendRedirect(req.getContextPath() + "/member/listMembers.do?msg=deleted");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting member", e);
            handleError(req, resp, e);
        }
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, Exception e)
        throws ServletException, IOException {
        req.setAttribute("errorMessage", e.getMessage());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
        dispatcher.forward(req, resp);
    }
}