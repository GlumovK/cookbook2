package service;

import repository.IngredientRepository;
import repository.IngredientRepositoryImpl;

public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;

    public IngredientServiceImpl() {
        repository = new IngredientRepositoryImpl();
    }

    @Override
    public void create(String name) {
        repository.save(name);
    }

    @Override
    public void addIngredientToRecipe(int ingredientId, int recipeId) {
        repository.addIngredientToRecipe(ingredientId, recipeId);
    }
}