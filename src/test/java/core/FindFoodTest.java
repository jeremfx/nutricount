package core;

import core.application.service.Core;
import core.domain.food.Food;
import core.domain.food.FoodGroup;
import infrastructure.persistence.ciqual.RecipeRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FindFoodTest {

    private final Core core = new Core(new FoodRepositoryInMemory(), new RecipeRepositoryInMemory());

    @Test
    void should_return_pomme() {
        Food pomme = core.findFoodByName("Pomme").get();
        assertEquals("Pomme", pomme.name());
    }

    @Test
    void should_return_poire() {
        Food poire = core.findFoodByName("Poire").get();
        assertEquals("Poire", poire.name());
    }

    @Test
    void should_return_calories() {
        Food poire = core.findFoodByName("Poire").get();
        assertEquals(100d, poire.calories());
    }

    @Test
    void should_return_nutrients() {
        Food pomme = core.findFoodByName("Pomme").get();
        assertEquals(150d, pomme.calories());
        assertEquals(10d, pomme.protein());
        assertEquals(20d, pomme.carbohydrate());
        assertEquals(50d, pomme.fat());

    }

    @Test
    void should_return_food_group() {
        Food pomme = core.findFoodByName("Pomme").get();
        assertEquals(new FoodGroup("Fruits"), pomme.foodGroup());
    }

    @Test
    void should_not_find_fraise() {
        Optional<Food> fraise = core.findFoodByName("Fraise");
        assertTrue(fraise.isEmpty());
    }
}
