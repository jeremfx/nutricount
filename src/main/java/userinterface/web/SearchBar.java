package userinterface.web;

import core.domain.food.IdentifiantRecette;
import userinterface.web.technical.HtmlFragment;

import java.util.Optional;

public class SearchBar implements HtmlFragment {

    private final Optional<IdentifiantRecette> currentRecipe;

    public SearchBar(Optional<IdentifiantRecette> currentRecipe) {
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
        return currentRecipe.map(id -> HttpRoutes.LIRE_RECETTE.path.replace("{id}", id.toString())).orElse("/");
    }
}
