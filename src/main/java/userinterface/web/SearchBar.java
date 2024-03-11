package userinterface.web;

import core.domain.food.RecipeId;

import java.util.Optional;

public class SearchBar implements HtmlFragment {

    private final Optional<RecipeId> currentRecipe;

    public SearchBar(Optional<RecipeId> currentRecipe) {
        this.currentRecipe = currentRecipe;
    }


    @Override
    public String render() {
        return """
                <form action="%s" method="get">
                    <label for="name">Ingredients :</label>
                    <input type="text" id="search-term" name="search-term">
                    <button type="submit">Search</button>
                </form>
                """.formatted(renderAction());
    }

    private String renderAction() {
        return currentRecipe.map(id -> "/recipes/" + id.id().toString()).orElse("/");
    }
}
