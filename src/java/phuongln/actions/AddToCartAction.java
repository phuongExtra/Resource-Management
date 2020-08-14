/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongln.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.log4j.Logger;
import phuongln.daos.ResourceDAO;
import phuongln.dtos.BookingDetailDTO;
import phuongln.dtos.ResourceDTO;
import phuongln.utils.CartObject;

/**
 *
 * @author nhatp
 */
public class AddToCartAction extends ActionSupport {
static Logger log = Logger.getLogger(AddToCartAction.class);
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";
    private String itemID, fromDate, toDate;
    private int amount;

    public AddToCartAction() {
    }

    public String execute() {
        String label = ERROR;
        try {
            Map session = ActionContext.getContext().getSession();
            CartObject cart = (CartObject) session.get("cart");
            BookingDetailDTO dto = new BookingDetailDTO();
            ResourceDTO item = new ResourceDAO().getItemByItemID(itemID);
            dto.setItemID(itemID);
            dto.setItemName(item.getItemName());
            dto.setQuantity(amount);
            dto.setBookingDate(fromDate);
            dto.setReturnDate(toDate);
            cart.addToCart(dto);
            session.put("cartSize", cart.size());
            label = SUCCESS;
        } catch (Exception e) {
            log.error("Error at AddToCartAction: " + e.getMessage());
        }
        return label;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
