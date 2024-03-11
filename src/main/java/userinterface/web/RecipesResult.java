package userinterface.web;

import core.domain.food.Ingredient;
import core.domain.food.Recipe;
import core.domain.food.RecipeId;

import java.util.List;
import java.util.stream.Collectors;

public class RecipesResult implements HtmlFragment{
    private final List<Recipe> recipes;
    public RecipesResult(List<Recipe> recipes) {
        this.recipes = recipes;
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
        if(recipes.isEmpty()){
            return "Pas encore de recettes !";
        }
        return recipes.stream().map(this::renderRecipe).collect(Collectors.joining(System.lineSeparator()));
    }

    private String renderRecipe(Recipe recipe) {
        return """
                <li>
                    <h3><a href="/recipes/%s">Recipe n° %s</a></h3>
                    <ul>
                        %s
                    </ul>
                </li>
                """.formatted(recipe.id, recipe.id, renderIngredients(recipe.getIngredients()));
    }

    private String renderIngredients(List<Ingredient> ingredients) {
        return ingredients.stream().map(this::renderIngredient).collect(Collectors.joining(System.lineSeparator()));
    }

    private String renderIngredient(Ingredient ingredient) {
        return """
                <li>
                    %s %sg
                </li>
                """.formatted(ingredient.food().name(), ingredient.size());
    }
}
