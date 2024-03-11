package core;

import core.application.service.Core;
import core.domain.food.*;
import infrastructure.persistence.ciqual.RecipeRepositoryInMemory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateRecipeTest {
    private final Core core = new Core(new FoodRepositoryInMemory(), new RecipeRepositoryInMemory());

    @Test
    void test() {
        Food pomme = new Food(new FoodId(1), "Pomme", new FoodGroup("Fruits"), new Macros(150d, 10d, 20d, 50d));
        Ingredient pomme100 = new Ingredient(pomme, new Size(100));
        RecipeId recipeId = core.createRecipe(new FoodId(1), new Size(100));
        assertNotNull(recipeId);
    }
}
