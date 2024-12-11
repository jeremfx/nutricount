package userinterface.web.addingredient;

import core.domain.food.IdentifiantAliment;
import core.domain.food.IdentifiantRecette;
import core.domain.food.Poids;
import userinterface.web.technical.HtmlFragment;
import userinterface.web.HttpRoutes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AddIngredientForm implements HtmlFragment {

    private final IdentifiantAliment ingredientToAdd;
    private final Optional<IdentifiantRecette> currentRecipe;

    public AddIngredientForm(IdentifiantAliment ingredientToAdd, Optional<IdentifiantRecette> recipeId) {
        this.ingredientToAdd = ingredientToAdd;
        this.currentRecipe = recipeId;
    }

    public static AddIngredientFormModel parseResponse(Map<String, List<String>> form) {
        return new AddIngredientFormModel(new IdentifiantAliment(Integer.valueOf(form.get("identifiantAliment").get(0))), new Poids(0));
    }

    @Override
    public String render() {
        return """
                <form style="display: inline-block;"  action="%s" method="post">
                    <input type="hidden" name="identifiantAliment" value="%s">
                    <button type="submit">Ajouter</button>
                </form>
                """.formatted(renderAction(), ingredientToAdd.id());
    }
    private String renderAction() {
        return currentRecipe.map(id -> HttpRoutes.AJOUTER_INGREDIENT.path.replace("{id}", id.id().toString()))
                .orElse(HttpRoutes.CREER_RECETTE.path);
    }

}
