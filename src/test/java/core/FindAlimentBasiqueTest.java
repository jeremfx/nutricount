package core;

import core.application.service.Core;
import core.domain.food.AlimentBasique;
import core.domain.food.Famille;
import infrastructure.persistence.ciqual.RecipeRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FindAlimentBasiqueTest {

    private final Core core = new Core(new FoodRepositoryInMemory(), new RecipeRepositoryInMemory());

    @Test
    void should_return_pomme() {
        AlimentBasique pomme = core.findFoodByName("Pomme").get();
        assertEquals("Pomme", pomme.nom());
    }

    @Test
    void should_return_poire() {
        AlimentBasique poire = core.findFoodByName("Poire").get();
        assertEquals("Poire", poire.nom());
    }

    @Test
    void should_return_calories() {
        AlimentBasique poire = core.findFoodByName("Poire").get();
        assertEquals(poire.glucides()*4 + poire.proteines()*4 + poire.lipides()*9, poire.calories());
    }

    @Test
    void should_return_nutrients() {
        AlimentBasique pomme = core.findFoodByName("Pomme").get();
        assertEquals(10d, pomme.proteines());
        assertEquals(20d, pomme.glucides());
        assertEquals(50d, pomme.lipides());

    }

    @Test
    void should_return_food_group() {
        AlimentBasique pomme = core.findFoodByName("Pomme").get();
        assertEquals(new Famille("Fruits"), pomme.famille());
    }

    @Test
    void should_not_find_fraise() {
        Optional<AlimentBasique> fraise = core.findFoodByName("Fraise");
        assertTrue(fraise.isEmpty());
    }
}
