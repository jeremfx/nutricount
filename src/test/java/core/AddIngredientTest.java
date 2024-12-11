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
        IdentifiantRecette id = core.createRecipe(new IdentifiantAliment(1), new Poids(100));
        AlimentBasique alimentBasique = core.findFoodByName("poire").get();
        core.addIngredient(id, alimentBasique.id(), new Poids(100));
        Recette recette = core.getRecipe(id);
        assertEquals(2, recette.getIngredients().size());
    }
}
