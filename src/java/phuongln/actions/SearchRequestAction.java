/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongln.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import phuongln.daos.BookingDAO;
import phuongln.dtos.BookingDTO;
import phuongln.dtos.BookingDetailDTO;

/**
 *
 * @author nhatp
 */
public class SearchRequestAction extends ActionSupport {

    static Logger log = Logger.getLogger(SearchRequestAction.class);
    private String itemNameSearch, usernameSearch, fromDate, toDate, action;
    private int statusSearch, page;

    public SearchRequestAction() {
    }

    public String execute() throws Exception {
        String label = "search";
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (itemNameSearch == null) {
                itemNameSearch = "";
            }
            if (usernameSearch == null) {
                usernameSearch = "";
            }
            if (fromDate == null) {
                fromDate = "";
            }
            if (toDate == null) {
                toDate = "";
            }
            if (action != null) {
                if (action.equals("Next Page")) {
                    setPage(page + 1);
                } else if (action.equals("Previous Page")) {
                    setPage(page - 1);
                }
            }
            List<BookingDTO> requestList = new BookingDAO().getBookingRequests(itemNameSearch, usernameSearch, statusSearch, fromDate, toDate, page);
            for (int i = 0; i < requestList.size(); i++) {
                List<BookingDetailDTO> itemListByBookingID = new BookingDAO().getDetailListByBookingID(requestList.get(i).getBookingID());
                requestList.get(i).setItemList(itemListByBookingID);
                for (int j = 0; j < itemListByBookingID.size(); j++) {
                }
            }
            
            int totalPages = new BookingDAO().getTotalPages();
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("requestList", requestList);
        } catch (Exception e) {
            log.error("Error at SearchRequestAction: " + e.getMessage());
        }
        return label;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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
