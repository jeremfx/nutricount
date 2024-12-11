package userinterface.web;

import core.domain.food.Ingredient;
import core.domain.food.Recette;
import userinterface.web.technical.HtmlFragment;

import java.util.List;
import java.util.stream.Collectors;

public class RecipesResult implements HtmlFragment {
    private final List<Recette> recettes;
    public RecipesResult(List<Recette> recettes) {
        this.recettes = recettes;
    }

    @Override
    public String render() {
        return """
                <ul>
                    %s
                </ul>
                """.formatted(renderRecipes());
    }

    private String renderRecipes() {
        if(recettes.isEmpty()){
            return "Pas encore de recettes !";
        }
        return recettes.stream().map(this::renderRecipe).collect(Collectors.joining(System.lineSeparator()));
    }

    private String renderRecipe(Recette recette) {
        return """
                <li>
                    <h3><a href="/recettes/%s">%s</a></h3>
                    <ul>
                        %s
                    </ul>
                </li>
                """.formatted(recette.id, recette.nom, renderIngredients(recette.getIngredients()));
    }

    private String renderIngredients(List<Ingredient> ingredients) {
        return ingredients.stream().map(this::renderIngredient).collect(Collectors.joining(System.lineSeparator()));
    }

    private String renderIngredient(Ingredient ingredient) {
        return """
                <li>
                    %s %sg
                </li>
                """.formatted(ingredient.alimentBasique().nom(), ingredient.poids());
    }
}
