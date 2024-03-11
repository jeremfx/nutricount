package userinterface.web;

import core.application.service.Core;
import core.domain.food.Food;
import core.domain.food.FoodGroup;
import core.domain.food.RecipeId;
import userinterface.web.addingredient.AddIngredientForm;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchResult implements HtmlFragment {

    private final String searchTerm;
    private final Core core;

    private final Optional<RecipeId> currentRecipe;

    public SearchResult(Core core, String searchTerm) {
        this.searchTerm = Objects.requireNonNullElse(searchTerm, "");
        this.core = core;
        this.currentRecipe = Optional.empty();
    }

    public SearchResult(Core core, String searchTerm, RecipeId recipeId) {
        this.searchTerm = Objects.requireNonNullElse(searchTerm, "");
        this.core = core;
        this.currentRecipe = Optional.of(recipeId);
    }

    @Override
    public String render() {
        Map<FoodGroup, List<Food>> foodByFoodGroup = getFoods().stream().collect(Collectors.groupingBy(Food::foodGroup));
        return renderFoodsByGroup(foodByFoodGroup);
    }

    private List<Food> getFoods() {
        if(searchTerm.equals("all")){
            return core.getFoods();
        } else {
            return core.searchFoodByName(searchTerm);
        }
    }

    private String renderFoodsByGroup(Map<FoodGroup, List<Food>> foodByFoodGroup) {
        return foodByFoodGroup.entrySet().stream().map(this::renderFoodsByGroup).collect(Collectors.joining());
    }

    private String renderFoodsByGroup(Map.Entry<FoodGroup, List<Food>> foodsByGroup) {
        return """
                <h3>%s</h3>
                <ul>
                %s
                </ul>
                """.formatted(foodsByGroup.getKey().name(), renderFoods(foodsByGroup.getValue()));
    }

    private String renderFoods(List<Food> foods) {
        return foods.stream().map(this::renderFood).collect(Collectors.joining());
    }

    private String renderFood(Food food) {
        AddIngredientForm form = new AddIngredientForm(food.id(), currentRecipe);
        return "<li>" + food.name() + form.render() + "</li>";
    }
}
