package web;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("AuthenticationFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        System.out.println("Requested Resource::" + uri);

        HttpSession session = req.getSession(false);

        if (session == null && !(uri.endsWith("html") || uri.endsWith("Login") || uri.endsWith("Register"))) {
            System.out.println("Unauthorized access request");
            res.sendRedirect("login.html");
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }

    }

    public void destroy() {
    }

}