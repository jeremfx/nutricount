package userinterface.web;

import core.application.service.Core;
import core.domain.food.AlimentBasique;
import core.domain.food.Famille;
import core.domain.food.IdentifiantRecette;
import userinterface.web.addingredient.AddIngredientForm;
import userinterface.web.technical.HtmlFragment;

import java.util.*;
import java.util.stream.Collectors;

public class SearchResult implements HtmlFragment {

    private final String searchTerm;
    private final Core core;

    private final Optional<IdentifiantRecette> currentRecipe;

    public SearchResult(Core core, String searchTerm) {
        this.searchTerm = Objects.requireNonNullElse(searchTerm, "");
        this.core = core;
        this.currentRecipe = Optional.empty();
    }

    public SearchResult(Core core, String searchTerm, IdentifiantRecette identifiantRecette) {
        this.searchTerm = Objects.requireNonNullElse(searchTerm, "");
        this.core = core;
        this.currentRecipe = Optional.of(identifiantRecette);
    }

    @Override
    public String render() {
        Map<Famille, List<AlimentBasique>> foodByFoodGroup = getFoods().stream().collect(Collectors.groupingBy(AlimentBasique::famille));
        return renderFoodsByGroup(foodByFoodGroup);
    }

    private List<AlimentBasique> getFoods() {
        if(searchTerm.equals("all")){
            return core.getFoods();
        } else {
            return core.searchFoodByName(searchTerm).stream().sorted(Comparator.comparingInt(f -> f.nom().length())).toList();
        }
    }

    private String renderFoodsByGroup(Map<Famille, List<AlimentBasique>> foodByFoodGroup) {
        return foodByFoodGroup.entrySet().stream().map(this::renderFoodsByGroup).collect(Collectors.joining());
    }

    private String renderFoodsByGroup(Map.Entry<Famille, List<AlimentBasique>> foodsByGroup) {
        return """
                <h3>%s</h3>
                <ul>
                %s
                </ul>
                """.formatted(foodsByGroup.getKey().name(), renderFoods(foodsByGroup.getValue()));
    }

    private String renderFoods(List<AlimentBasique> alimentBasiques) {
        return alimentBasiques.stream().map(this::renderFood).collect(Collectors.joining());
    }

    private String renderFood(AlimentBasique alimentBasique) {
        AddIngredientForm form = new AddIngredientForm(alimentBasique.id(), currentRecipe);
        return "<li>" + alimentBasique.nom() + form.render() + "</li>";
    }
}
