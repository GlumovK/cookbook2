package servlet;

import repository.Storage;
import web.IngredientController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "IngredientServlet", urlPatterns = {"/ingredients"})
public class IngredientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Storage storage;
    private IngredientController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            storage = Storage.getInstance();
            controller = new IngredientController();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            String name = request.getParameter("ingredientName");
            controller.create(name);
            response.sendRedirect("recipies");

        } else if ("addIngredientToRecipe".equals(action)) {
            int ingredientId = Integer.parseInt(request.getParameter("ingredientId"));
            int recipeId = Integer.parseInt(request.getParameter("recipeId"));
            controller.addIngredientToRecipe(ingredientId, recipeId);
            response.sendRedirect("recipies");
        }
    }
}