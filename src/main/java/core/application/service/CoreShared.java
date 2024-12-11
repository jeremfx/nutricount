package core.application.service;

import core.domain.food.*;

import java.util.List;
import java.util.Optional;

public interface CoreShared {

    // Queries
    List<Recette> getRecipes();
    Optional<AlimentBasique> findFoodByName(String foodName);
    List<AlimentBasique> searchFoodByName(String searchTerm);
    List<AlimentBasique> getFoods();
    IdentifiantRecette createRecipe(IdentifiantAliment identifiantAliment, Poids poids);
    Recette getRecipe(IdentifiantRecette id);

    // Commands
    void addIngredient(IdentifiantRecette id, IdentifiantAliment identifiantAliment, Poids poids);
    void resizeIngredient(IdentifiantRecette id, IdentifiantAliment identifiantAliment, Poids newPoids);
    void renameRecipe(IdentifiantRecette id, String newName);
}
