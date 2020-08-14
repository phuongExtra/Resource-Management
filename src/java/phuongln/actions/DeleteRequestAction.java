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
public class DeleteRequestAction extends ActionSupport {

    static Logger log = Logger.getLogger(DeleteRequestAction.class);
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";
    private String bookingID, action, itemNameSearch, usernameSsearch, fromDate, toDate;
    private int statusSearch, page;
    
    public DeleteRequestAction() {
    }
    
    public String execute() throws Exception {
        String label = ERROR;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            boolean valid = new BookingDAO().updateRequest(bookingID, action);
            if (valid) {
                label = SUCCESS;
                
                request.setAttribute("success", "Delete successfully!");
                
            }
            
        } catch (Exception e) {
            log.error("Error at DeleteRequestAction: " + e.getMessage());
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
    
    public String getUsernameSsearch() {
        return usernameSsearch;
    }
    
    public void setUsernameSsearch(String usernameSsearch) {
        this.usernameSsearch = usernameSsearch;
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
