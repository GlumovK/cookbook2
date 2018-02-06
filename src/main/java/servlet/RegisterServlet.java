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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class RegisterServlet extends HttpServlet {

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
        String name = request.getParameter("name");

        String errorMsg = null;
        if (email == null || email.equals("")) {
            errorMsg = "Email ID can't be null or empty.";
        }
        if (password == null || password.equals("")) {
            errorMsg = "Password can't be null or empty.";
        }
        if (name == null || name.equals("")) {
            errorMsg = "Name can't be null or empty.";
        }
        User userLog = userService.getByEmailAndPassword(email, password);
        if (userLog.getName() != null) {
            errorMsg = "User already exist";
        }

        if (errorMsg != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>" + errorMsg + "</font>");
            rd.include(request, response);
        } else {
            User user = new User(name, email, password);
            userService.create(user);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=green>Registration successful, please login below.</font>");
            rd.include(request, response);
        }

    }
}