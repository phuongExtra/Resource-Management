/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongln.actions;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import phuongln.daos.BookingDAO;

/**
 *
 * @author nhatp
 */
public class UpdateRequestAction extends ActionSupport {

    static Logger log = Logger.getLogger(UpdateRequestAction.class);
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";
    private String bookingID, action, itemNameSearch, usernameSearch, fromDate, toDate;
    private int statusSearch, page;

    public UpdateRequestAction() {
    }

    public String execute() throws Exception {
        String label = ERROR;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            boolean valid = new BookingDAO().updateRequest(bookingID, action);
            if (valid) {
                label = SUCCESS;
                if (action.equals("Acccept")) {
                    request.setAttribute("success", "Accept successfully!");
                } else if (action.equals("Decline")) {
                    request.setAttribute("success", "Decline successfully!");
                }
            }

        } catch (Exception e) {
            log.error("Error at UpdateRequestAction: " + e.getMessage());
        }
        return label;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getItemNameSearch() {
        return itemNameSearch;
    }

    public void setItemNameSearch(String itemNameSearch) {
        this.itemNameSearch = itemNameSearch;
    }

    public String getUsernameSearch() {
        return usernameSearch;
    }

    public void setUsernameSearch(String usernameSearch) {
        this.usernameSearch = usernameSearch;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getStatusSearch() {
        return statusSearch;
    }

    public void setStatusSearch(int statusSearch) {
        this.statusSearch = statusSearch;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
