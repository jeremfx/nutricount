package userinterface.web.resizeingredient;

import core.domain.food.FoodId;
import core.domain.food.RecipeId;
import core.domain.food.Size;
import userinterface.web.HtmlFragment;

import java.util.List;
import java.util.Map;

public class ResizeIngredientForm implements HtmlFragment {

    private final RecipeId recipeId;
    private final FoodId foodId;
    private final Size size;

    public ResizeIngredientForm(RecipeId recipeId, FoodId foodId, Size size) {
        this.recipeId = recipeId;
        this.foodId = foodId;
        this.size = size;
    }

    public static ResizeIngredientFormModel parseResponse(Map<String, List<String>> form) {
        return new ResizeIngredientFormModel(
                new FoodId(Integer.valueOf(form.get("foodId").get(0))),
                new Size(Integer.valueOf(form.get("newSize").get(0)))
        );
    }


    @Override
    public String render() {
        return """
                <form style="display: inline-block;"  action="%s" method="post">
                    <input type="hidden" name="foodId" value="%s">
                    <input type="number" id="newSize" name="newSize" value="%s">
                    <button type="submit">Ok</button>
                </form>
                """.formatted(renderAction(), foodId, size.size());
    }

    private String renderAction() {
        return "/recipes/" + recipeId + "/resize-ingredient";
    }
}
