package core.application.repository;

import core.domain.food.Recette;
import core.domain.food.IdentifiantRecette;

import java.util.List;


public interface RecipeRepository {
    void saveRecipe(Recette recette);

    Recette getRecipe(IdentifiantRecette id);

    List<Recette> getRecipes();
}
