package web;

import service.IngredientService;
import service.IngredientServiceImpl;

public class IngredientController {

    private final  IngredientService service;

    public IngredientController() {
        this.service = new IngredientServiceImpl();
    }

    public void create(String name) {
         service.create(name);
    }

    public void addIngredientToRecipe(int ingredientId, int recipeId){
        service.addIngredientToRecipe(ingredientId, recipeId);
    }
}
