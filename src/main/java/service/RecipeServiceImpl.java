package service;

import model.Recipe;
import repository.RecipeRepository;
import repository.RecipeRepositoryImpl;
import util.exception.NotFoundException;

import java.util.List;

import static util.ValidationUtil.checkNotFound;
import static util.ValidationUtil.checkNotFoundWithId;

public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository repository;

    public RecipeServiceImpl() {
        repository = new RecipeRepositoryImpl();
    }

    @Override
    public Recipe create(Recipe recipe, int userId) {
        return repository.save(recipe, userId);
    }

    @Override
    public Recipe get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Recipe> getByIngredient(String ingredientName) {
        return repository.getByIngredient(ingredientName);
    }

    @Override
    public List<Recipe> getByCatalog(String catalogName) {
        return repository.getByCatalog(catalogName);
    }

    @Override
    public List<Recipe> getByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public List<Recipe> getByUser(String userName) {
        return repository.getByUser(userName);
    }

    @Override
    public List<Recipe> getAll() {
        return repository.getAll();
    }

    @Override
    public Recipe updateRating(Recipe recipe) {
        return checkNotFound(repository.updateRating(recipe), "recipe=" + recipe.getName());
    }

    public void addToCatalog(int catalogId, int recipeId) {
        repository.addToCatalog(catalogId, recipeId);
    }


}

