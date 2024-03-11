package infrastructure.persistence.ciqual;

import core.application.repository.RecipeRepository;
import core.domain.food.Recipe;
import core.domain.food.RecipeId;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeRepositoryInMemory implements RecipeRepository {
    private final Set<Recipe> recipes = new HashSet<>();

    @Override
    public void saveRecipe(Recipe recipe) {
        if(recipes.contains(recipe)){
            recipes.remove(recipe);
        }
        recipes.add(recipe);
    }

    @Override
    public Recipe getRecipe(RecipeId id) {
        return recipes.stream().filter(r -> r.id.equals(id)).findFirst().orElseThrow();
    }

    @Override
    public List<Recipe> getRecipes() {
        return recipes.stream().toList();
    }
}
