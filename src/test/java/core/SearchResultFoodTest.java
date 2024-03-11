package core;

import core.application.service.Core;
import core.domain.food.Food;
import infrastructure.persistence.ciqual.RecipeRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchResultFoodTest {
    private Core core = new Core(new FoodRepositoryInMemory(), new RecipeRepositoryInMemory());

    @Test
    void should_return_pomme_poire(){
        Food pomme = core.findFoodByName("Pomme").get();
        Food poire = core.findFoodByName("Poire").get();
        List<Food> foods = core.searchFoodByName("Po");
        assertTrue(foods.containsAll(List.of(poire, pomme)));
    }

    @Test
    void should_return_only_pomme(){
        Food pomme = core.findFoodByName("Pomme").get();
        List<Food> foods = core.searchFoodByName("mm");
        assertTrue(foods.contains(pomme));
    }

    @Test
    void should_not_be_case_sensitive(){
        Food pomme = core.findFoodByName("Pomme").get();
        Food poire = core.findFoodByName("Poire").get();
        List<Food> foods = core.searchFoodByName("po");
        assertTrue(foods.containsAll(List.of(poire, pomme)));
    }

    @Test
    void should_be_empty(){
        List<Food> foods = core.searchFoodByName("");
        assertTrue(foods.isEmpty());
    }
}
