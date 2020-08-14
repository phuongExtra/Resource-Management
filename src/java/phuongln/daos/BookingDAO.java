/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongln.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import phuongln.dtos.BookingDTO;
import phuongln.dtos.BookingDetailDTO;
import phuongln.utils.CartObject;
import phuongln.utils.DBConnection;

/**
 *
 * @author nhatp
 */
public class BookingDAO {

    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

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

    public List<BookingDTO> getBookedList(String fromDate, String toDate) throws Exception {
        List<BookingDTO> result = null;
        BookingDTO dto = null;
        String bookingID;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT d.bookingID "
                    + "FROM tblBookingRequest r, tblBookingDetails d "
                    + "WHERE r.bookingID = d.bookingID "
                    + "AND (statusID = 4 OR statusID = 1) "
                    + "AND d.bookingID NOT IN "
                    + "(SELECT d.bookingID "
                    + "FROM tblBookingRequest r, tblBookingDetails d "
                    + "WHERE r.bookingID = d.bookingID "
                    + "AND (statusID = 4 OR statusID = 1) "
                    + "AND (d.bookingDate >'2020-07-30' OR d.returnDate < '2020-07-01'))";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                bookingID = rs.getString("bookingID");
                dto = new BookingDTO();
                dto.setBookingID(bookingID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getBookingIDCount() throws Exception {
        int count = 0;
        try {
            if (con == null) {
                con = DBConnection.getConnection();
            }
            String sql = "SELECT COUNT(bookingID) as total FROM tblBookingRequest";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } finally {

        }
        return count;
    }

    public int getDetailIDCount() throws Exception {
        int count = 0;
        try {
            if (con == null) {
                con = DBConnection.getConnection();
            }
            String sql = "SELECT COUNT(detailID) as total FROM tblBookingDetails";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } finally {

        }
        return count;
    }

    public boolean insertBookingRequest(CartObject cart, String username) throws Exception {
        boolean check = false;
        int bookingcount = getBookingIDCount() + 1;
        int detailCount = getDetailIDCount();
        Date date = Calendar.getInstance().getTime();
        String dateSring = FORMAT.format(date);
        try {
            if (con == null) {
                con = DBConnection.getConnection();
            }
            con.setAutoCommit(false);
            String sql = "INSERT INTO tblBookingRequest(bookingID, bookingDate, username, statusID) values(?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, "B" + bookingcount);
            ps.setString(2, dateSring);
            ps.setString(3, username);
            ps.setInt(4, 1);
            check = ps.executeUpdate() > 0;
            if (check) {
                sql = "INSERT INTO tblBookingDetails(detailID, bookingID, itemID, itemName, quantity, bookingDate, returnDate) values(?,?,?,?,?,?,?)";
                int count = 0;
                bookingcount = getBookingIDCount();
                for (String id : cart.getCart().keySet()) {
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "D" + (detailCount + count + 1));
                    ps.setString(2, "B" + bookingcount);
                    ps.setString(3, id);
                    ps.setString(4, cart.getCart().get(id).getItemName());
                    ps.setInt(5, cart.getCart().get(id).getQuantity());
                    ps.setString(6, cart.getCart().get(id).getBookingDate());
                    ps.setString(7, cart.getCart().get(id).getReturnDate());
                    ps.executeUpdate();
                    count++;
                }
            }
            con.commit();
            check = true;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<BookingDTO> getBookingRequests(String itemNameSearch, String usernameSearch, int statusIDSearch, String fromDate, String toDate, int page) throws Exception {
        List<BookingDTO> result = null;
        BookingDTO dto = null;
        String bookingID, username, bookingDate;
        String statusSQL = "";
        int statusID;
        try {
            if (statusIDSearch != 0) {
                statusSQL = "AND statusID = " + statusIDSearch;
            } else {
                statusSQL = "AND (statusID = 1 OR statusID = 4 OR statusID = 5) ";
            }
            String priSql = "SELECT DISTINCT TOP(5) r.bookingID, r.username, r.statusID, r.bookingDate "
                    + "FROM tblBookingRequest r, tblBookingDetails d WHERE r.bookingID = d.bookingID "
                    + "AND d.itemName LIKE ? "
                    + "AND r.username LIKE ? " + statusSQL
                    + "AND d.bookingID NOT IN (";
            String secSql = "SELECT DISTINCT TOP(?) d.bookingID "
                    + "FROM tblBookingDetails d, tblBookingRequest r "
                    + "WHERE r.bookingID = d.bookingID "
                    + "AND d.itemName LIKE ? "
                    + "AND r.username LIKE ? " + statusSQL
                    + "AND (d.bookingDate > '" + toDate + "' OR d.returnDate < '" + fromDate + "') ORDER BY bookingID)  ORDER BY r.bookingDate";

            con = DBConnection.getConnection();

            ps = con.prepareStatement(priSql + secSql);
            ps.setString(1, "%" + itemNameSearch + "%");
            ps.setString(2, "%" + usernameSearch + "%");
            ps.setInt(3, (page - 1) * 5);
            ps.setString(4, "%" + itemNameSearch + "%");
            ps.setString(5, "%" + usernameSearch + "%");
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                bookingID = rs.getString("bookingID");
                username = rs.getString("username");
                bookingDate = rs.getString("bookingDate");
                statusID = rs.getInt("statusID");
                dto = new BookingDTO();
                dto.setBookingID(bookingID);
                dto.setUsername(username);
                dto.setStatusID(statusID);
                dto.setBookingDate(bookingDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getTotalPages() throws Exception {
        int total = 0;
        int count = 0;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT COUNT(bookingID) as total FROM tblBookingRequest";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
            total = count / 5;
            if (count % 5 > 0) {
                total += 1;
            }
        } finally {
            closeConnection();
        }
        return total;
    }

    public boolean updateRequest(String bookingID, String action) throws Exception {
        boolean check = false;
        try {
            con = DBConnection.getConnection();
            String sql = "UPDATE tblBookingRequest ";
            if (action.equals("Accept")) {
                sql += "SET statusID = 4 WHERE bookingID = ?";
            } else if (action.equals("Decline")) {
                sql += "SET statusID = 5 WHERE bookingID = ?";
            } else if (action.equals("Cancel")) {
                sql += "SET statusID = 3 WHERE bookingID = ?";
            }
            ps = con.prepareStatement(sql);
            ps.setString(1, bookingID);
            check = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<BookingDetailDTO> getDetailListByBookingID(String bookingID) throws Exception {
        List<BookingDetailDTO> result = null;
        BookingDetailDTO dto = null;
        String itemID, itemName, bookingDate, returnDate;
        int quantity;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT itemID, itemName, bookingDate, returnDate, quantity FROM tblBookingDetails WHERE bookingID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, bookingID);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                itemID = rs.getString("itemID");
                itemName = rs.getString("itemName");
                quantity = rs.getInt("quantity");
                bookingDate = rs.getString("bookingDate");
                returnDate = rs.getString("returnDate");
                dto = new BookingDetailDTO();
                dto.setItemID(itemID);
                dto.setItemName(itemName);
                dto.setQuantity(quantity);
                dto.setBookingDate(bookingDate);
                dto.setReturnDate(returnDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<BookingDTO> getRequestHistory(String itemNameSearch, String fromDate, String toDate, int page, String username) throws Exception {
        List<BookingDTO> result = null;
        BookingDTO dto = null;
        String bookingID, bookingDate;
        int statusID;
        try {

            String priSql = "SELECT DISTINCT TOP(5) r.bookingID, r.statusID, r.bookingDate "
                    + "FROM tblBookingRequest r, tblBookingDetails d "
                    + "WHERE r.bookingID = d.bookingID "
                    + "AND d.itemName LIKE ? AND r.username = ? "
                    + "AND d.bookingID NOT IN (";
            String secSql = "SELECT DISTINCT TOP(?) d.bookingID "
                    + "FROM tblBookingDetails d, tblBookingRequest r "
                    + "WHERE r.bookingID = d.bookingID "
                    + "AND d.itemName LIKE ? AND username = ? "
                    + "AND (d.bookingDate > '" + toDate + "' OR d.returnDate < '" + fromDate + "') ORDER BY d.bookingID)  ORDER BY r.bookingDate";

            con = DBConnection.getConnection();

            ps = con.prepareStatement(priSql + secSql);
            ps.setString(1, "%" + itemNameSearch + "%");
            ps.setString(2, username);
            ps.setInt(3, (page - 1) * 5);
            ps.setString(4, "%" + itemNameSearch + "%");
            ps.setString(5, username);
            rs = ps.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                bookingID = rs.getString("bookingID");
                bookingDate = rs.getString("bookingDate");
                statusID = rs.getInt("statusID");
                dto = new BookingDTO();
                dto.setBookingID(bookingID);
                dto.setStatusID(statusID);
                dto.setBookingDate(bookingDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
