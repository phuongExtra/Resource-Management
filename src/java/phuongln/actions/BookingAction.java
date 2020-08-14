/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongln.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import phuongln.daos.BookingDAO;
import phuongln.utils.CartObject;

/**
 *
 * @author nhatp
 */
public class BookingAction extends ActionSupport {

    static Logger log = Logger.getLogger(BookingAction.class);
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";
    
    public BookingAction() {
    }
    
    public String execute() throws Exception {
        String label = ERROR;
        try {
            Map session = ActionContext.getContext().getSession();
            HttpServletRequest request = ServletActionContext.getRequest();
            CartObject cart = (CartObject) session.get("cart");
            BookingDAO dao = new BookingDAO();
            if(dao.insertBookingRequest(cart, cart.getCustomerName())){
                cart.getCart().clear();
            }

            if (cart.size() == 0) {
                request.setAttribute("success", "Send request successflly!");
                label = SUCCESS;
            } else {
                request.setAttribute("error", "Send request failed!");
            }
            session.put("cartSize", cart.size());
            
        } catch (Exception e) {
            log.error("Error at BookingAction: " + e.getMessage());
        }
        return label;
    }
    
}
