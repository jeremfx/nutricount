package core;

import core.application.service.Core;
import core.domain.food.IdentifiantAliment;
import core.domain.food.IdentifiantRecette;
import core.domain.food.Poids;
import core.domain.food.Recette;
import infrastructure.persistence.ciqual.RecipeRepositoryInMemory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetRecipeTest {
    private final Core core = new Core(new FoodRepositoryInMemory(), new RecipeRepositoryInMemory());

    @Test
    void test(){
        IdentifiantRecette id = core.createRecipe(new IdentifiantAliment(1), new Poids(150));
        Recette r = core.getRecipe(id);
        assertEquals(r.apportCaloriqueEnGlucides() + r.apportCaloriqueEnProteines() + r.apportCaloriqueEnLipides(), 100);
    }
}
