package core;

import core.application.service.Core;
import core.domain.food.IdentifiantAliment;
import core.domain.food.IdentifiantRecette;
import core.domain.food.Poids;
import infrastructure.persistence.ciqual.RecipeRepositoryInMemory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResizeIngredientTest {

    private final Core core = new Core(new FoodRepositoryInMemory(), new RecipeRepositoryInMemory());

    @Test
    void test(){
        IdentifiantRecette id = core.createRecipe(new IdentifiantAliment(1), new Poids(200));
        core.resizeIngredient(id, new IdentifiantAliment(1), new Poids(300));
        assertEquals(new Poids(300), core.getRecipe(id).getIngredients().get(0).poids());
    }
}
