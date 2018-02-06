package servlet;


import model.Recipe;
import model.User;
import repository.Storage;
import web.RecipeController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(name = "RecipeServlet", urlPatterns = {"/recipies"})
public class RecipeServlet extends HttpServlet {

    private Storage storage;
    private RecipeController recipeController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            storage = Storage.getInstance();
            recipeController = new RecipeController();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            final Recipe recipe = new Recipe();
            request.setAttribute("recipe", recipe);
            request.getRequestDispatcher("/recipeForm.jsp").forward(request, response);
        } else if ("addVote".equals(action)) {
            int id = getId(request);
            recipeController.addVote(id);
            response.sendRedirect("recipies");
        } else if ("subtractVote".equals(action)) {
            int id = getId(request);
            recipeController.subtractVote(id);
            response.sendRedirect("recipies");
        } else {
            request.setAttribute("recipies", recipeController.getAll());
            request.getRequestDispatcher("/recipies.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("getByName".equals(action)) {
            String name = request.getParameter("name");
            request.setAttribute("recipies", recipeController.getByName(name));
            request.getRequestDispatcher("/recipies.jsp").forward(request, response);
        } else if ("getByUser".equals(action)) {
            String userName = request.getParameter("user");
            request.setAttribute("recipies", recipeController.getByUser(userName));
            request.getRequestDispatcher("/recipies.jsp").forward(request, response);
        } else if ("getByIngredient".equals(action)) {
            String ingredient = request.getParameter("ingredient");
            request.setAttribute("recipies", recipeController.getByIngredient(ingredient));
            request.getRequestDispatcher("/recipies.jsp").forward(request, response);
        } else if ("getByCatalog".equals(action)) {
            String catalog = request.getParameter("catalog");
            request.setAttribute("recipies", recipeController.getByCatalog(catalog));
            request.getRequestDispatcher("/recipies.jsp").forward(request, response);
        } else if ("addToCatalog".equals(action)) {
            int catalogId = Integer.parseInt(request.getParameter("catalogId"));
            int recipeId = Integer.parseInt(request.getParameter("recipeId"));
            recipeController.addToCatalog(catalogId, recipeId);
            response.sendRedirect("recipies");
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("User");
            int userId = user.getId();
            Recipe recipe = new Recipe(
                    request.getParameter("name"),
                    request.getParameter("description"),
                    request.getParameter("cookAlgorithm"));
            recipe.setRating(0);
            recipe.setUserId(userId);
            String catalogs = request.getParameter("catalogs");
            recipeController.create(recipe, userId);
            response.sendRedirect("recipies");

        }

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
