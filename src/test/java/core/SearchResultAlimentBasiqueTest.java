package core;

import core.application.service.Core;
import core.domain.food.AlimentBasique;
import infrastructure.persistence.ciqual.RecipeRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchResultAlimentBasiqueTest {
    private Core core = new Core(new FoodRepositoryInMemory(), new RecipeRepositoryInMemory());

    @Test
    void should_return_pomme_poire(){
        AlimentBasique pomme = core.findFoodByName("Pomme").get();
        AlimentBasique poire = core.findFoodByName("Poire").get();
        List<AlimentBasique> alimentBasiques = core.searchFoodByName("Po");
        assertTrue(alimentBasiques.containsAll(List.of(poire, pomme)));
    }

    @Test
    void should_return_only_pomme(){
        AlimentBasique pomme = core.findFoodByName("Pomme").get();
        List<AlimentBasique> alimentBasiques = core.searchFoodByName("mm");
        assertTrue(alimentBasiques.contains(pomme));
    }

    @Test
    void should_not_be_case_sensitive(){
        AlimentBasique pomme = core.findFoodByName("Pomme").get();
        AlimentBasique poire = core.findFoodByName("Poire").get();
        List<AlimentBasique> alimentBasiques = core.searchFoodByName("po");
        assertTrue(alimentBasiques.containsAll(List.of(poire, pomme)));
    }

    @Test
    void should_be_empty(){
        List<AlimentBasique> alimentBasiques = core.searchFoodByName("");
        assertTrue(alimentBasiques.isEmpty());
    }
}
