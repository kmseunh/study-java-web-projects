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

public class MemberDAO {

    private static final Logger LOGGER = Logger.getLogger(MemberDAO.class.getName());

    public MemberDAO() {
    }

    public List<MemberVO> listMembers() {
        List<MemberVO> memberList = new ArrayList<MemberVO>();
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
}
