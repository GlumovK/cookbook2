package servlet;


import model.User;
import repository.Storage;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Storage storage;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            storage = Storage.getInstance();
            userService = new UserServiceImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String errorMsg = null;
        if(email == null || email.equals("")){
            errorMsg ="User Email can't be null or empty";
        }
        if(password == null || password.equals("")){
            errorMsg = "Password can't be null or empty";
        }
        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(request, response);

        } else{

            User user = userService.getByEmailAndPassword(email, password);
           if (user.getName() != null) {
               System.out.println("User found with details= " + user);
               HttpSession session = request.getSession();
               session.setAttribute("User", user);
               response.sendRedirect("home.jsp");
           } else {
               RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
               PrintWriter out= response.getWriter();
               out.println("<font color=red>No user found with given email id, please register first.</font>");
               rd.include(request, response);
           }
        }
    }

}