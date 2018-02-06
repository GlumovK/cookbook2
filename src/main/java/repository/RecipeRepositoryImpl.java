package repository;


import model.Ingredient;
import model.Recipe;
import model.User;
import sql.SqlHelper;
import util.exception.NotExistStorageException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeRepositoryImpl implements RecipeRepository {
    public SqlHelper sqlHelper;

    public static final String ALL = "SELECT * FROM recipes LEFT JOIN users ON recipes.user_id = users.id";
    public static final String BY_INGREDIENT = "SELECT recipes.id, recipes.name, recipes.description, recipes.cookalgorithm, recipes.user_id, recipes.rating, users.id, users.name\n" +
            "FROM   recipes, users, ingredients, ingredient_to_recipe\n" +
            "WHERE recipes.user_id = users.id AND recipes.id = ingredient_to_recipe.recipe_id\n" +
            "AND   ingredients.id = ingredient_to_recipe.ingredient_id AND ingredients.name = ";
    public static final String BY_CATALOG = "SELECT recipes.id, recipes.name, recipes.description, recipes.cookalgorithm, recipes.user_id, recipes.rating, users.id, users.name\n" +
            "FROM   recipes, users, catalogs, catalog_to_recipe\n" +
            "WHERE recipes.user_id = users.id AND recipes.id = catalog_to_recipe.recipe_id\n" +
            "AND   catalogs.id = catalog_to_recipe.catalog_id AND catalogs.name =  ";
    public static final String BY_USER = "SELECT * FROM recipes LEFT JOIN users ON recipes.user_id = users.id where users.name = ";
    public static final String BY_NAME = "SELECT * FROM recipes LEFT JOIN users ON recipes.user_id = users.id\n" +
            "where recipes.name = ";

    public RecipeRepositoryImpl() {
        try {
            sqlHelper = Storage.getInstance().sqlHelper;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Recipe save(Recipe recipe, int userId) {

        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO recipes (name, description, cookAlgorithm, rating, user_id  ) VALUES (?,?,?,?,?)")) {
                        ps.setString(1, recipe.getName());
                        ps.setString(2, recipe.getDescription());
                        ps.setString(3, recipe.getCookAlgorithm());
                        ps.setInt(4, recipe.getRating());
                        ps.setInt(5, recipe.getUserId());
                        ps.executeUpdate();
                    }

            return recipe;
                }

        );
        return recipe;
    }

    @Override
    public Recipe updateRating(Recipe recipe) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE recipes SET rating = ? WHERE id = ?")) {
                ps.setInt(1, recipe.getRating());
                ps.setInt(2, recipe.getId());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(recipe.getId().toString());
                }
            }
            return recipe;
        });
        return recipe;
    }

    @Override
    public Recipe get(int id) {
        return sqlHelper.transactionalExecute(conn -> {
            Recipe recipe = new Recipe();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM recipes WHERE id =?")) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int recipeId = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String cookAlgorithm = rs.getString("cookAlgorithm");
                    int rating = rs.getInt("rating");
                    int userId = rs.getInt("user_id");
                    recipe.setId(recipeId);
                    recipe.setName(name);
                    recipe.setDescription(description);
                    recipe.setCookAlgorithm(cookAlgorithm);
                    recipe.setRating(rating);
                    recipe.setUserId(userId);
                }
            }
            return recipe;
        });
    }


    @Override
    public List<Recipe> getByName(String name) {
        return getAllAbstract(BY_NAME + "'" + name + "'");
    }

    @Override
    public List<Recipe> getByIngredient(String ingredientName) {
        return getAllAbstract(BY_INGREDIENT + "'" + ingredientName + "'");
    }

    @Override
    public List<Recipe> getByCatalog(String catalogName) {
        return getAllAbstract(BY_CATALOG + "'" + catalogName + "'");
    }

    @Override
    public List<Recipe> getByUser(String userName) {
        return getAllAbstract(BY_USER + "'" + userName + "'");
    }

    public List<Recipe> getAll() {
        return getAllAbstract(ALL);
    }


    public List<Recipe> getAllAbstract(String querySql) {
        return sqlHelper.transactionalExecute(conn -> {
            List<Recipe> recipes = new ArrayList<>();
            try (PreparedStatement ps = conn.prepareStatement(querySql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String cookAlgorithm = rs.getString("cookAlgorithm");
                    int rating = rs.getInt("rating");
                    int userId = rs.getInt(7);
                    String userName = rs.getString(8);
                    User user = new User(userId, userName);
                    Recipe recipe = new Recipe(id, name, description, cookAlgorithm, rating, user);
                    recipes.add(recipe);
                }
            }
            //заполняем поле Set<Ingredient> ingredients у рецепта
            try (PreparedStatement ps = conn.prepareStatement("SELECT ingredients.name " +
                    "FROM   ingredients, ingredient_to_recipe " +
                    "WHERE  ingredients.id = ingredient_to_recipe.ingredient_id " +
                    "AND ingredient_to_recipe.recipe_id = ?")) {


                for (Recipe recipe : recipes) {
                    Set<Ingredient> ingredientSet = new HashSet<>();
                    int i = recipe.getId();
                    ps.setInt(1, i);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        String name = rs.getString(1);
                        Ingredient ingredient = new Ingredient(name);
                        ingredientSet.add(ingredient);
                    }
                    recipe.setIngredients(ingredientSet);
                    i++;
                }
            }
            return recipes;
        });
    }

    @Override
    public void addToCatalog(int catalogId, int recipeId) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO catalog_to_recipe (catalog_id, recipe_id) " +
                            "VALUES (?,?) ON CONFLICT (catalog_id, recipe_id) DO NOTHING")) {
                        ps.setInt(1, catalogId);
                        ps.setInt(2,recipeId);
                        ps.execute();
                    }
                    return null;
                }
        );
    }


}
