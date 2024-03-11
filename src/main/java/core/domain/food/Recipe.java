package core.domain.food;

import java.util.*;

public class Recipe {
    public final RecipeId id;
    private final List<Ingredient> ingredients;

    public Recipe(List<Ingredient> ingredients) {
        id = new RecipeId(UUID.randomUUID());
        this.ingredients = Collections.unmodifiableList(ingredients);
    }

    protected Recipe(RecipeId id, List<Ingredient> ingredients) {
        this.id = id;
        this.ingredients = Collections.unmodifiableList(ingredients);
    }

    public Recipe copy(List<Ingredient> ingredients) {
        return new Recipe(id, ingredients);
    }

    public double calories() {
        return ingredients.stream().map(Ingredient::calories).reduce(0d, Double::sum);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Recipe addIngredient(Ingredient ingredient) {
        List<Ingredient> newIngredients = new ArrayList<>(ingredients);
        newIngredients.add(ingredient);
        return copy(newIngredients);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        return Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public Recipe resizeIngredient(FoodId foodId, Size newSize) {
        if (!ingredients.stream().map(i -> i.food().id()).toList().contains(foodId)) {
            throw new NoSuchElementException("Can't resize ingredient because it's missing it's not in the recipe");
        }
        List<Ingredient> updatedIngredients = ingredients.stream().map(i -> {
            if (i.food().id().equals(foodId)) {
                return new Ingredient(i.food(), newSize);
            } else {
                return i;
            }
        }).toList();
        return copy(updatedIngredients);
    }

    public Double proteines() {
        return ingredients.stream().map(Ingredient::proteines).reduce(0d, Double::sum);
    }

    public Double carbs() {
        return ingredients.stream().map(Ingredient::carbs).reduce(0d, Double::sum);
    }

    public Double fats() {
        return ingredients.stream().map(Ingredient::fats).reduce(0d, Double::sum);
    }
}
