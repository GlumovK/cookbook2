package web;

import model.Recipe;
import service.RecipeService;
import service.RecipeServiceImpl;

import java.util.List;

import static util.ValidationUtil.checkNew;

public class RecipeController {

    private final RecipeService service;

    public RecipeController() {
        this.service = new RecipeServiceImpl();
    }

    public List<Recipe> getByUser(String userName) {
        return service.getByUser(userName);
    }

    public List<Recipe> getByIngredient(String ingredientName) {
        return service.getByIngredient(ingredientName);
    }

    public List<Recipe> getByName(String name) {
        return service.getByName(name);
    }

    public List<Recipe> getByCatalog(String catalogName) {
        return service.getByCatalog(catalogName);
    }

    public Recipe create(Recipe recipe, int userId) {
        checkNew(recipe);
        return service.create(recipe, userId);
    }

    public void addVote(int id) {
        Recipe recipe = service.get(id);
        recipe.setRating(recipe.getRating() + 1);
        service.updateRating(recipe);
    }

    public void subtractVote(int id) {
        Recipe recipe = service.get(id);
        recipe.setRating(recipe.getRating() - 1);
        service.updateRating(recipe);
    }

    public List<Recipe> getAll() {
        return service.getAll();
    }

    public void addToCatalog(int catalogId, int recipeId) {

        service.addToCatalog(catalogId, recipeId);
    }


}
