package repository;

import model.Recipe;

import java.util.List;

public interface RecipeRepository {

    Recipe save(Recipe recipe, int userId);

    Recipe get(int id);

    List<Recipe> getByName(String name);

    List<Recipe> getByIngredient(String ingredientName);

    List<Recipe> getByCatalog(String catalogName);

    List<Recipe> getByUser(String userName);

    List<Recipe> getAll();

    Recipe updateRating(Recipe recipe);

    void addToCatalog(int catalogId, int recipeId);

    List<Recipe> getAllAbstract(String querySql);


}
