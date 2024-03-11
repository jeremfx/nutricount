package core.application.repository;

import core.domain.food.Recipe;
import core.domain.food.RecipeId;

import java.util.List;


public interface RecipeRepository {
    void saveRecipe(Recipe recipe);

    Recipe getRecipe(RecipeId id);

    List<Recipe> getRecipes();
}
