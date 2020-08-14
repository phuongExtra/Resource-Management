/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongln.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import phuongln.dtos.UserDTO;
import phuongln.utils.DBConnection;

/**
 *
 * @author nhatp
 */
public class UserDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public UserDTO checkLogin(String username, String password) throws Exception {
        int roleID = 0;
        int statusID = 0;
        UserDTO dto = new UserDTO();
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT roleID, statusID FROM tblUsers " + "WHERE username = ? and password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                roleID = rs.getInt("roleID");
                statusID = rs.getInt("statusID");
                dto.setRoleID(roleID);
                dto.setStatusID(statusID);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
