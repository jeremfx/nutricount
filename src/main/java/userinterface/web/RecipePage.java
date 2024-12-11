package userinterface.web;

import core.domain.food.Ingredient;
import core.domain.food.Recette;
import userinterface.web.rename.RenameForm;
import userinterface.web.resizeingredient.ResizeIngredientForm;
import userinterface.web.technical.HtmlFragment;

import java.util.List;
import java.util.stream.Collectors;

public class RecipePage implements HtmlFragment {

    private final Recette recette;
    private final SearchResult searchResult;

    public RecipePage(SearchResult searchResult, Recette recette) {
        this.recette = recette;
        this.searchResult = searchResult;
    }

    @Override
    public String render() {
        return """
               %s
               %s
               """.formatted(searchResult.render(), renderRecipe());
    }

    private String renderRecipe() {
        return """
                %s
                <ol>
                %s
                </ol>
                %s
                """.formatted(renderName(), renderIngredients(recette.getIngredients()), renderMacros(recette));
    }

    private String renderName() {
        return "<h3>" + new RenameForm(recette.id, recette.nom).render() + "</h3>";
    }

    private Object renderMacros(Recette recette) {
        return """
               <p> Kcal : %s Protéines : %s (%s%%) Glucides : %s (%s%%)  Lipides : %s (%s%%)
               """.formatted(Math.round(recette.calories()),
                Math.round(recette.proteines()),
                Math.round(recette.apportCaloriqueEnProteines()),
                Math.round(recette.glucides()),
                Math.round(recette.apportCaloriqueEnGlucides()),
                Math.round(recette.lipides()),
                Math.round(recette.apportCaloriqueEnLipides()));

    }

    private String renderIngredients(List<Ingredient> ingredients) {
        return ingredients.stream().map(this::renderIngredient).collect(Collectors.joining());
    }

    private String renderIngredient(Ingredient ingredient) {
        return "<li>" + ingredient.alimentBasique().nom() + " " + ingredient.poids().size() + "g " + renderAddIngredientForm(ingredient) + "</ul>";
    }

    private String renderAddIngredientForm(Ingredient ingredient) {
        return new ResizeIngredientForm(recette.id, ingredient.alimentBasique().id(), ingredient.poids()).render();
    }

}
