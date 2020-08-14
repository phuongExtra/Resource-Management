/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongln.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import phuongln.dtos.ResourceDTO;
import phuongln.utils.DBConnection;

/**
 *
 * @author nhatp
 */
public class ResourceDAO {

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

    public List<ResourceDTO> getResourceListByRoleID(String nameSearch, String categorySearch, int roleID, int page) throws Exception {
        List<ResourceDTO> result = null;
        ResourceDTO dto = null;
        String itemID, itemName, category, color;
        int quantity;
        try {
            String priSql = "SELECT TOP(5) itemID, itemName, category, color, quantity FROM tblItems WHERE itemName LIKE ? AND category = ? ";
            String secSql = "SELECT TOP(?) itemID FROM tblItems WHERE itemName LIKE ? AND category = ? ";

            String condition = "";
            String order = "ORDER BY itemName ";
            if (roleID == 1 || roleID == 2) {
                condition = "AND roleID != 1 ";
            } else {
                condition = "AND roleID = 3 ";
            }
            con = DBConnection.getConnection();

            ps = con.prepareStatement(priSql + condition + "AND itemID NOT IN (" + secSql + condition + order + ") " + order);
            ps.setString(1, "%" + nameSearch + "%");
            ps.setString(2, categorySearch);
            ps.setInt(3, (page - 1) * 5);
            ps.setString(4, "%" + nameSearch + "%");
            ps.setString(5, categorySearch);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                itemID = rs.getString("itemID");
                itemName = rs.getString("itemName");
                category = rs.getString("category");
                color = rs.getString("color");
                quantity = rs.getInt("quantity");
                dto = new ResourceDTO();
                dto.setItemID(itemID);
                dto.setItemName(itemName);
                dto.setCategory(category);
                dto.setColor(color);
                dto.setQuantity(quantity);
                dto.setAvailable(quantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getTotalPages(int roleID) throws Exception {
        int total = 0;
        int result = 0;
        String condition = "";
        try {
            if (roleID == 1 || roleID == 2) {
                condition = "roleID != 1";
            } else {
                condition = "roleID = 3";
            }
            con = DBConnection.getConnection();
            String sql = "SELECT COUNT(itemID) as total FROM tblItems WHERE ";
            ps = con.prepareStatement(sql + condition);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt("total");
            }
            total = result / 5;
            if (result % 5 > 0) {
                total += 1;
            }
        } finally {
            closeConnection();
        }
        return total;
    }

    public ResourceDTO getItemByItemID(String itemID) throws Exception {
        ResourceDTO dto = null;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT itemName, color, category, quantity FROM tblItems WHERE statusID = 2 AND itemID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, itemID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String itemName = rs.getString("itemName");
                String color = rs.getString("color");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");
                dto = new ResourceDTO();
                dto.setItemName(itemName);
                dto.setColor(color);
                dto.setCategory(category);
                dto.setQuantity(quantity);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<ResourceDTO> getAllItems() throws Exception {
        List<ResourceDTO> result = null;
        ResourceDTO dto = null;
        String itemID, itemName;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT itemID, itemName FROM tblItems";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                itemID = rs.getString("itemID");
                itemName = rs.getString("itemName");
                dto = new ResourceDTO();
                dto.setItemID(itemID);
                dto.setItemName(itemName);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

}
