package core;

import core.application.service.Core;
import core.domain.food.FoodId;
import core.domain.food.RecipeId;
import core.domain.food.Size;
import infrastructure.persistence.ciqual.RecipeRepositoryInMemory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResizeIngredientTest {

    private final Core core = new Core(new FoodRepositoryInMemory(), new RecipeRepositoryInMemory());

    @Test
    void test(){
        RecipeId id = core.createRecipe(new FoodId(1), new Size(200));
        core.resizeIngredient(id, new FoodId(1), new Size(300));
        assertEquals(new Size(300), core.getRecipe(id).getIngredients().get(0).size());
    }
}
