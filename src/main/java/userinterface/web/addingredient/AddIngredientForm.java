package userinterface.web.addingredient;

import core.domain.food.FoodId;
import core.domain.food.RecipeId;
import core.domain.food.Size;
import userinterface.web.HtmlFragment;
import userinterface.web.HttpRoutes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AddIngredientForm implements HtmlFragment {

    private final FoodId ingredientToAdd;
    private final Optional<RecipeId> currentRecipe;

    public AddIngredientForm(FoodId ingredientToAdd, Optional<RecipeId> recipeId) {
        this.ingredientToAdd = ingredientToAdd;
        this.currentRecipe = recipeId;
    }

    public static AddIngredientFormModel parseResponse(Map<String, List<String>> form) {
        return new AddIngredientFormModel(new FoodId(Integer.valueOf(form.get("foodId").get(0))), new Size(0));
    }

    @Override
    public String render() {
        return """
                <form style="display: inline-block;"  action="%s" method="post">
                    <input type="hidden" name="foodId" value="%s">
                    <button type="submit">Ajouter</button>
                </form>
                """.formatted(renderAction(), ingredientToAdd.id());
    }
    private String renderAction() {
        return currentRecipe.map(id -> "/recipes/" + id + "/add-ingredient")
                .orElse(HttpRoutes.CREATE_RECIPES.path);
    }

}
