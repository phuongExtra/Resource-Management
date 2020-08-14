/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongln.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author nhatp
 */
public class BookingDTO implements Serializable {

    private String username, bookingDate, bookingID;
    private int statusID;
    private List<BookingDetailDTO> itemList;

    public BookingDTO() {
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public List<BookingDetailDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<BookingDetailDTO> itemList) {
        this.itemList = itemList;
    }

}
