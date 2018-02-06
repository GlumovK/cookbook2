package repository;

import sql.SqlHelper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IngredientRepositoryImpl implements IngredientRepository {
    public SqlHelper sqlHelper;

    public IngredientRepositoryImpl() {
        try {
            sqlHelper = Storage.getInstance().sqlHelper;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(String name)  {
            sqlHelper.transactionalExecute(conn -> {
                        try (PreparedStatement ps = conn.prepareStatement(
                                "INSERT INTO ingredients (name) VALUES (?) ON CONFLICT (name) DO NOTHING")) {
                            ps.setString(1,name);
                            ps.executeUpdate();
                        }
                        return null;
                    }
            );
        }

    @Override
    public void addIngredientToRecipe(int ingredientId, int recipeId) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO ingredient_to_recipe (ingredient_id, recipe_id) " +
                            "VALUES (?,?) ON CONFLICT (ingredient_id, recipe_id) DO NOTHING")) {
                        ps.setInt(1, ingredientId);
                        ps.setInt(2,recipeId);
                        ps.execute();
                    }
                    return null;
                }
        );
    }
}
