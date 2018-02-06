package repository;

public interface IngredientRepository {

    void save(String name);

    void addIngredientToRecipe(int ingredientId, int recipeId);
}
