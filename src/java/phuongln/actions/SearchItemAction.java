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
import phuongln.daos.ResourceDAO;
import phuongln.dtos.BookingDTO;
import phuongln.dtos.BookingDetailDTO;
import phuongln.dtos.ResourceDTO;

/**
 *
 * @author nhatp
 */
public class SearchItemAction extends ActionSupport {

    static Logger log = Logger.getLogger(SearchItemAction.class);
    private String nameSearch, categorySearch, fromDate, toDate, action;
    private int roleID, page;

    public SearchItemAction() {
    }

    public String execute() {
        String label = "search";
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            if (nameSearch == null) {
                nameSearch = "";
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
            List<ResourceDTO> resourceList = new ResourceDAO().getResourceListByRoleID(nameSearch, categorySearch, roleID, page);
            List<BookingDTO> bookedList = new BookingDAO().getBookedList(fromDate, toDate);

            for (int i = 0; i < bookedList.size(); i++) {
                List<BookingDetailDTO> detailList = new BookingDAO().getDetailListByBookingID(bookedList.get(i).getBookingID());
                bookedList.get(i).setItemList(detailList);
            }

            if (!bookedList.isEmpty()) {
                for (int i = 0; i < bookedList.size(); i++) {
                    for (int j = 0; j < bookedList.get(i).getItemList().size(); j++) {
                        for (int k = 0; k < resourceList.size(); k++) {
                            if (resourceList.get(k).getItemID().equals(bookedList.get(i).getItemList().get(j).getItemID())) {
                                resourceList.get(k).setAvailable(resourceList.get(k).getQuantity() - bookedList.get(i).getItemList().get(j).getQuantity());
                            }
                        }
                    }
                }
            }
            int totalPages = new ResourceDAO().getTotalPages(roleID);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("resourceList", resourceList);
        } catch (Exception e) {
            log.error("Error at SearchItemAction: " + e.getMessage());
        }
        return label;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getCategorySearch() {
        return categorySearch;
    }

    public void setCategorySearch(String categorySearch) {
        this.categorySearch = categorySearch;
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

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
