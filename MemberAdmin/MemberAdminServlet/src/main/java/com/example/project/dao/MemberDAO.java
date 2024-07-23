package com.example.project.dao;


import com.example.project.util.DBUtil;
import com.example.project.vo.MemberVO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Member dao.
 */
public class MemberDAO {

    private static final Logger LOGGER = Logger.getLogger(MemberDAO.class.getName());

    /**
     * Instantiates a new Member dao.
     */
    public MemberDAO() {
    }

    /**
     * List members list.
     *
     * @return the list
     */
    public List<MemberVO> listMembers() {
        List<MemberVO> memberList = new ArrayList<>();
        String query = "SELECT * FROM t_member order by join_date desc";
        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                String id = rs.getString("id");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date joinDate = rs.getDate("join_date");
                MemberVO memberVO = new MemberVO(id, pwd, name, email, joinDate);
                memberList.add(memberVO);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error select members");
        }
        return memberList;
    }

    /**
     * Add member.
     *
     * @param memberVO the member vo
     */
    public void addMember(MemberVO memberVO) {
        String query = "INSERT INTO t_member(id, pwd, name, email) VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
        ) {
            pstmt.setString(1, memberVO.getId());
            pstmt.setString(2, memberVO.getPwd());
            pstmt.setString(3, memberVO.getName());
            pstmt.setString(4, memberVO.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error add Member");
        }
    }

    /**
     * Find member member vo.
     *
     * @param id the id
     * @return the member vo
     */
    public MemberVO findMember(String id) {
        MemberVO memberVO = null;
        String query = "SELECT * FROM t_member WHERE id = ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String memberId = rs.getString("id");
                    String pwd = rs.getString("pwd");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    Date joinDate = rs.getDate("join_date");
                    memberVO = new MemberVO(memberId, pwd, name, email, joinDate);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding member", e);
        }

        return memberVO;
    }

    /**
     * Update member.
     *
     * @param memberVO the member vo
     */
    public void updateMember(MemberVO memberVO) {
        String query = "UPDATE t_member SET pwd = ?, name = ?, email = ? WHERE id = ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, memberVO.getPwd());
            pstmt.setString(2, memberVO.getName());
            pstmt.setString(3, memberVO.getEmail());
            pstmt.setString(4, memberVO.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                LOGGER.log(Level.WARNING, "No member found with ID: " + memberVO.getId());
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating member with ID: " + memberVO.getId(), e);
        }
    }

    /**
     * Delete member.
     *
     * @param id the id
     */
    public void deleteMember(String id) {
        String query = "DELETE FROM t_member WHERE id = ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                LOGGER.log(Level.WARNING, "No member found with ID: " + id);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting member with ID: " + id, e);
        }
    }
}
