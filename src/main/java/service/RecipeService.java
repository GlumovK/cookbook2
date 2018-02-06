package service;

import model.Recipe;
import util.exception.NotFoundException;

import java.util.List;

public interface RecipeService {

    Recipe create(Recipe recipe, int userId);

    Recipe get(int id) throws NotFoundException;

    List<Recipe> getByIngredient(String ingredientName);

    List<Recipe> getByCatalog(String catalogName);

    List<Recipe> getByUser(String userName);

    List<Recipe> getByName(String name);

    List<Recipe> getAll();

    Recipe updateRating(Recipe recipe);

     void addToCatalog(int catalogId, int recipeId);


}
