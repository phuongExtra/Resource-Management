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

/**
 *
 * @author nhatp
 */
public class LogoutAction extends ActionSupport {

    static Logger log = Logger.getLogger(LogoutAction.class);

    public LogoutAction() {
    }
    
    public String execute() {
        try {
            Map session = ActionContext.getContext().getSession();
            if (session != null) {
                session.clear();
            }
        } catch (Exception e) {
            log.error("Error at LogoutAction: " + e.getMessage());
        }
        return "success";
    }
    
}
