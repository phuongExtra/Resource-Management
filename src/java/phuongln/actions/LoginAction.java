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
import phuongln.daos.UserDAO;
import phuongln.dtos.UserDTO;
import phuongln.utils.CartObject;

/**
 *
 * @author nhatp
 */
public class LoginAction extends ActionSupport {

    static Logger log = Logger.getLogger(LoginAction.class);
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";
    private static final String NEW = "new";
    private String username, password, reCAPTCHAResult;
    private static final String[] CATEGORY = {"electronic", "non-electronic"};
    
    public LoginAction() {
    }
    
    public String execute() throws Exception {
        String label = ERROR;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (reCAPTCHAResult.equals("True")) {
                UserDTO dto = new UserDAO().checkLogin(username, password);
                int statusID = dto.getStatusID();
                dto.setUsername(username);
                if (statusID == 1) {
                    label = NEW;
                    request.setAttribute("error", "You need to active your account to login!");
                    return label;
                } else if (statusID == 2) {
                    Map session = ActionContext.getContext().getSession();
                    session.put("user", dto);
                    session.put("category", CATEGORY);
                    int roleID = dto.getRoleID();
                    if (roleID == 1) {
                        label = SUCCESS;
                    } else if (roleID == 2 || roleID == 3) {
                        label = SUCCESS;
                        CartObject cart = new CartObject();
                        cart.setCustomerName(username);
                        session.put("cart", cart);
                    } else {
                        request.setAttribute("error", "Your status is invalid!");
                    }
                } else {
                    request.setAttribute("error", "Invalid username or password!");
                }
            } else {
                request.setAttribute("error", "You must check the reCAPTCHA!");
            }
        } catch (Exception e) {
            log.error("Error at LoginAction: " + e.getMessage());
        }
        return label;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getReCAPTCHAResult() {
        return reCAPTCHAResult;
    }
    
    public void setReCAPTCHAResult(String reCAPTCHAResult) {
        this.reCAPTCHAResult = reCAPTCHAResult;
    }
}
