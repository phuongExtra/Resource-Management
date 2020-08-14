/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuongln.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import phuongln.dtos.UserDTO;

/**
 *
 * @author nhatp
 */
public class DispatchFilter implements Filter {

    private static final boolean debug = true;
//    private static final String SEARCH_REQUEST = "SearchRequestAction";
//    private static final String SEARCH_REQUEST_REDIRECT = "SearchRequestAction.action";
//    private static final String SEARCH_ITEM = "SearchItemAction";
//    private static final String SEARCH_REQUEST_HISTORY = "SearchRequestHistoryAction";
//    private static final String SEARCH_REQUEST_HISTORY_REDIRECT = "SearchRequestHistoryAction.action";
//    private static final String BOOKING = "BookingAction";
//    private static final String LOGIN = "LoginAction";
//    private static final String LOGOUT = "LogoutAction";
//    private static final String UPDATE = "UpdateRequestAction";
//    private static final String CANCEL = "DeleteRequestAction";
//    private static final String ADD = "AddToCartAction";
//    private static final String CART_PAGE = "cart.jsp";
//    private static final String SEARCH_REQUEST_HISTORY_PAGE = "requestHistory.jsp";
//    private static final String SEARCH_REQUEST_PAGE = "manageRequest.jsp";
//    private static final String LOGIN_PAGE = "login.jsp";
//    private static final String SEARCH_ITEM_PAGE = "searchItem.jsp";
//
//    private final List<String> ADMIN_LIST;
//    private final List<String> USER_LIST;
//    private final List<String> GUEST_LIST;
    static Logger log = Logger.getLogger(DispatchFilter.class);
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public DispatchFilter() {
//        ADMIN_LIST = new ArrayList<>();
//        ADMIN_LIST.add(SEARCH_ITEM);
//        ADMIN_LIST.add(SEARCH_ITEM_PAGE);
//        ADMIN_LIST.add(SEARCH_REQUEST);
//        ADMIN_LIST.add(SEARCH_REQUEST_PAGE);
//        ADMIN_LIST.add(SEARCH_REQUEST_REDIRECT);
//        ADMIN_LIST.add(UPDATE);
//        ADMIN_LIST.add(LOGOUT);
//        ADMIN_LIST.add(LOGIN_PAGE);
//        ADMIN_LIST.add(LOGIN);
//
//        USER_LIST = new ArrayList<>();
//        USER_LIST.add(SEARCH_ITEM);
//        USER_LIST.add(SEARCH_ITEM_PAGE);
//        USER_LIST.add(SEARCH_REQUEST_HISTORY);
//        USER_LIST.add(SEARCH_REQUEST_HISTORY_PAGE);
//        USER_LIST.add(SEARCH_REQUEST_HISTORY_REDIRECT);
//        USER_LIST.add(CANCEL);
//        USER_LIST.add(ADD);
//        USER_LIST.add(CART_PAGE);
//        USER_LIST.add(BOOKING);
//        USER_LIST.add(LOGOUT);
//        USER_LIST.add(LOGIN_PAGE);
//        USER_LIST.add(LOGIN);
//
//        GUEST_LIST = new ArrayList<>();
//        GUEST_LIST.add(LOGIN_PAGE);
//        GUEST_LIST.add(LOGIN);
//        List<String> srcList = Utilities.getSrcList("css");
//        for (int i = 0; i < srcList.size(); i++) {
//            ADMIN_LIST.add(srcList.get(i));
//            USER_LIST.add(srcList.get(i));
//            GUEST_LIST.add(srcList.get(i));
//        }
//        srcList = Utilities.getSrcList("js");
//        for (int i = 0; i < srcList.size(); i++) {
//            ADMIN_LIST.add(srcList.get(i));
//            USER_LIST.add(srcList.get(i));
//            GUEST_LIST.add(srcList.get(i));
//        }
//        srcList = Utilities.getSrcList("jquery");
//        for (int i = 0; i < srcList.size(); i++) {
//            ADMIN_LIST.add(srcList.get(i));
//            USER_LIST.add(srcList.get(i));
//            GUEST_LIST.add(srcList.get(i));
//        }

    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DispatchFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DispatchFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        HttpSession session = req.getSession(false);
//        String uri = req.getRequestURI();
//        String resource = uri.substring(uri.lastIndexOf("/") + 1);
//        
//        if (session != null) {
//            if (session.getAttribute("user") != null) {
//                UserDTO user = (UserDTO) session.getAttribute("user");
//
//                int roleID = user.getRoleID();
//                if (roleID == 1 && ADMIN_LIST.contains(resource)) {
//                    chain.doFilter(request, response);
//
//                } else if ((roleID == 2 || roleID == 3) && USER_LIST.contains(resource)) {
//                    chain.doFilter(request, response);
//
//                } else {
//                    res.sendRedirect(LOGIN_PAGE);
//
//                }
//            } else {
//                if (GUEST_LIST.contains(resource)) {
//                    chain.doFilter(request, response);
//
//                } else {
//                    res.sendRedirect(LOGIN_PAGE);
//                }
//            }
//        } else {
//            if (GUEST_LIST.contains(resource)) {
//                chain.doFilter(request, response);
//
//            } else {
//                res.sendRedirect(LOGIN_PAGE);
//            }
//        }

String url = "login.jsp";
        boolean accessDenied = false;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UserDTO dto = (UserDTO) session.getAttribute("user");
        String uri = req.getRequestURI();
        String resource = uri.substring(uri.lastIndexOf("/") + 1);
        if (resource.contains(".action")) {
            resource = resource.substring(0, resource.lastIndexOf("."));
        }
        if (dto != null) {
            int role = dto.getRoleID();
            if (role == 1) {
                if (resource.contains("cart.jsp") || resource.contains("requestHistory.jsp") || resource.contains("AddToCartAction")
                        || resource.contains("BookingAction") || resource.contains("DeleteRequestAction")
                        || resource.contains("SearchRequestHistoryAction")) {
                    accessDenied = true;
                }
            } else if (role == 2 || role == 3) {
                if (resource.contains("manageRequest.jsp") || resource.contains("SearchRequestAction") || resource.contains("UpdateRequestAction")) {
                    accessDenied = true;
                }
            }
        } else if (resource.contains("cart.jsp") || resource.contains("requestHistory.jsp")
                || resource.contains("BookingAction") || resource.contains("AddToCartAction")
                || resource.contains("DeleteRequestAction") || resource.contains("SearchRequestHistoryAction")
                || resource.contains("manageRequest.jsp") || resource.contains("SearchRequestAction")
                || resource.contains("UpdateRequestAction") || resource.contains("LogoutAction") || resource.contains("searchItem.jsp")
                || resource.contains("SearchItemAction")) {
            accessDenied = true;
        }
        if (accessDenied) {
            res.sendRedirect(url);
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("DispatchFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("DispatchFilter()");
        }
        StringBuffer sb = new StringBuffer("DispatchFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
