package infrastructure.persistence.ciqual;

import core.application.repository.RecipeRepository;
import core.domain.food.IdentifiantRecette;
import core.domain.food.Recette;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeRepositoryInMemory implements RecipeRepository {
    private final Set<Recette> recettes = new HashSet<>();

    @Override
    public void saveRecipe(Recette recette) {
        if(recettes.contains(recette)){
            recettes.remove(recette);
        }
        recettes.add(recette);
    }

    @Override
    public Recette getRecipe(IdentifiantRecette id) {
        return recettes.stream().filter(r -> r.id.equals(id)).findFirst().orElseThrow();
    }

    @Override
    public List<Recette> getRecipes() {
        return recettes.stream().toList();
    }
}
