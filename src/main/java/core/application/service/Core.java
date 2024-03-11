package core.application.service;

import core.application.repository.FoodRepository;
import core.application.repository.RecipeRepository;
import core.domain.food.*;

import java.util.List;
import java.util.Optional;

public class Core {

    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;

    public Core(FoodRepository foodRepository, RecipeRepository recipeRepository) {
        this.foodRepository = foodRepository;
        this.recipeRepository = recipeRepository;
    }

    public Optional<Food> findFoodByName(String foodName) {
        return foodRepository.findFoodByName(foodName);
    }

    public List<Food> searchFoodByName(String searchTerm) {
        return foodRepository.searchFoodByName(searchTerm);
    }
    public List<Food> getFoods() {
        return foodRepository.getFoods();
    }

    public RecipeId createRecipe(FoodId foodId,  Size size) {
        Recipe recipe = new Recipe(List.of(new Ingredient(foodRepository.getFood(foodId), size)));
        recipeRepository.saveRecipe(recipe);
        return recipe.id;
    }

    public Recipe getRecipe(RecipeId id) {
        return recipeRepository.getRecipe(id);
    }

    public void addIngredient(RecipeId id, FoodId foodId,  Size size){
        Recipe recipe = recipeRepository.getRecipe(id);
        Recipe updatedRecipe = recipe.addIngredient(new Ingredient(foodRepository.getFood(foodId), size));
        recipeRepository.saveRecipe(updatedRecipe);
    }

    public List<Recipe> getRecipes() {
        return recipeRepository.getRecipes();
    }

    public void resizeIngredient(RecipeId id, FoodId foodId, Size newSize){
        Recipe recipe = recipeRepository.getRecipe(id);
        Recipe updatedRecipe = recipe.resizeIngredient(foodId, newSize);
        recipeRepository.saveRecipe(updatedRecipe);
    }
}
