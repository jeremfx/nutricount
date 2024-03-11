package core;

import core.domain.food.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeTest {

    @Test
    void should_return_ingredient_calories() {
        Food pomme = new Food(new FoodId(1), "Pomme", new FoodGroup("Fruits"), new Macros(100d, 10d, 15d, 30d));
        Recipe recipe = new Recipe(List.of(
                new Ingredient(pomme, new Size(150))
        ));
        assertEquals(150d, recipe.calories());
    }

    @Test
    void should_return_total_ingredients_calories() {
        Food pomme = new Food(new FoodId(1), "Pomme", new FoodGroup("Fruits"),
                new Macros(100d, 10d, 15d, 30d));
        Food poire = new Food(new FoodId(1), "Pomme", new FoodGroup("Fruits"),
                new Macros(30d, 10d, 15d, 30d));
        Recipe recipe = new Recipe(List.of(
                new Ingredient(pomme, new Size(150)),
                new Ingredient(poire, new Size(200))
        ));
        assertEquals(150d + 60d, recipe.calories());
    }
}
