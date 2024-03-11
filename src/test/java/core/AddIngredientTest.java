package core;

import core.application.service.Core;
import core.domain.food.*;
import infrastructure.persistence.ciqual.RecipeRepositoryInMemory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddIngredientTest {
    private final Core core = new Core(new FoodRepositoryInMemory(), new RecipeRepositoryInMemory());

    @Test
    void test() {
        RecipeId id = core.createRecipe(new FoodId(1), new Size(100));
        Food food = core.findFoodByName("poire").get();
        core.addIngredient(id, food.id(), new Size(100));
        Recipe recipe = core.getRecipe(id);
        assertEquals(2, recipe.getIngredients().size());
    }
}
