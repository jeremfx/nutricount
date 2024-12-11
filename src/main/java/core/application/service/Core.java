package core.application.service;

import core.application.repository.FoodRepository;
import core.application.repository.RecipeRepository;
import core.domain.food.*;

import java.util.List;
import java.util.Optional;

public class Core implements CoreShared {

    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;

    public Core(FoodRepository foodRepository, RecipeRepository recipeRepository) {
        this.foodRepository = foodRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Optional<AlimentBasique> findFoodByName(String foodName) {
        return foodRepository.findFoodByName(foodName);
    }

    @Override
    public List<AlimentBasique> searchFoodByName(String searchTerm) {
        return foodRepository.searchFoodByName(searchTerm);
    }
    @Override
    public List<AlimentBasique> getFoods() {
        return foodRepository.getFoods();
    }

    @Override
    public IdentifiantRecette createRecipe(IdentifiantAliment identifiantAliment, Poids poids) {
        Recette recette = new Recette(List.of(new Ingredient(foodRepository.getFood(identifiantAliment), poids)));
        recipeRepository.saveRecipe(recette);
        return recette.id;
    }

    @Override
    public Recette getRecipe(IdentifiantRecette id) {
        return recipeRepository.getRecipe(id);
    }

    @Override
    public void addIngredient(IdentifiantRecette id, IdentifiantAliment identifiantAliment, Poids poids){
        Recette recette = recipeRepository.getRecipe(id);
        Recette updatedRecette = recette.ajouterIngredient(new Ingredient(foodRepository.getFood(identifiantAliment), poids));
        recipeRepository.saveRecipe(updatedRecette);
    }

    @Override
    public List<Recette> getRecipes() {
        return recipeRepository.getRecipes();
    }

    @Override
    public void resizeIngredient(IdentifiantRecette id, IdentifiantAliment identifiantAliment, Poids newPoids){
        Recette recette = recipeRepository.getRecipe(id);
        Recette updatedRecette = recette.reajusterIngredient(identifiantAliment, newPoids);
        recipeRepository.saveRecipe(updatedRecette);
    }

    @Override
    public void renameRecipe(IdentifiantRecette id, String newName) {
        Recette recette = recipeRepository.getRecipe(id);
        Recette updatedRecette = recette.renommer(newName);
        recipeRepository.saveRecipe(updatedRecette);
    }
}
