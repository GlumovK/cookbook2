package service;

public interface IngredientService {
    public void create(String name);

    void addIngredientToRecipe(int ingredientId, int recipeId);
}
