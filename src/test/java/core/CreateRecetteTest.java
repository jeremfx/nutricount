package core;

import core.application.service.Core;
import core.domain.food.*;
import infrastructure.persistence.ciqual.RecipeRepositoryInMemory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateRecetteTest {
    private final Core core = new Core(new FoodRepositoryInMemory(), new RecipeRepositoryInMemory());

    @Test
    void test() {
        AlimentBasique pomme = new AlimentBasique(new IdentifiantAliment(1), "Pomme", new Famille("Fruits"), new Macros(150d, 10d, 20d, 50d));
        Ingredient pomme100 = new Ingredient(pomme, new Poids(100));
        IdentifiantRecette identifiantRecette = core.createRecipe(new IdentifiantAliment(1), new Poids(100));
        assertNotNull(identifiantRecette);
    }
}
