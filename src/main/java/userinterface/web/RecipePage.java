package userinterface.web;

import core.domain.food.Ingredient;
import core.domain.food.Recipe;
import userinterface.web.addingredient.AddIngredientForm;
import userinterface.web.resizeingredient.ResizeIngredientForm;

import java.util.List;
import java.util.stream.Collectors;

public class RecipePage implements HtmlFragment {

    private final Recipe recipe;
    private final SearchResult searchResult;

    public RecipePage(SearchResult searchResult, Recipe recipe) {
        this.recipe = recipe;
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
                <h3><a href="/recipes/%s">Recipe n° %s</a></h3>
                <ol>
                %s
                </ol>
                %s
                """.formatted(recipe.id, recipe.id, renderIngredients(recipe.getIngredients()), renderMacros(recipe));
    }

    private Object renderMacros(Recipe recipe) {
        return """
               <p> Kcal : %s Protéines : %s Glucides : %s  Lipides : %s
               """.formatted(Math.round(recipe.calories()), Math.round(recipe.proteines()), Math.round(recipe.carbs()), Math.round(recipe.fats()));

    }

    private String renderIngredients(List<Ingredient> ingredients) {
        return ingredients.stream().map(this::renderIngredient).collect(Collectors.joining());
    }

    private String renderIngredient(Ingredient ingredient) {
        return "<ul>" + ingredient.food().name() + " " + ingredient.size().size() + "g " + renderAddIngredientForm(ingredient) + "</ul>";
    }

    private String renderAddIngredientForm(Ingredient ingredient) {
        return new ResizeIngredientForm(recipe.id, ingredient.food().id(), ingredient.size()).render();
    }

}
