package userinterface.web.resizeingredient;

import core.domain.food.IdentifiantAliment;
import core.domain.food.IdentifiantRecette;
import core.domain.food.Poids;
import userinterface.web.technical.HtmlFragment;
import userinterface.web.HttpRoutes;

import java.util.List;
import java.util.Map;

public class ResizeIngredientForm implements HtmlFragment {

    private final IdentifiantRecette identifiantRecette;
    private final IdentifiantAliment identifiantAliment;
    private final Poids poids;

    public ResizeIngredientForm(IdentifiantRecette identifiantRecette, IdentifiantAliment identifiantAliment, Poids poids) {
        this.identifiantRecette = identifiantRecette;
        this.identifiantAliment = identifiantAliment;
        this.poids = poids;
    }

    public static ResizeIngredientFormModel parseResponse(Map<String, List<String>> form) {
        return new ResizeIngredientFormModel(
                new IdentifiantAliment(Integer.valueOf(form.get("identifiantAliment").get(0))),
                new Poids(Integer.valueOf(form.get("newPoids").get(0)))
        );
    }


    @Override
    public String render() {
        return """
                <form style="display: inline-block;"  action="%s" method="post">
                    <input type="hidden" name="identifiantAliment" value="%s">
                    <input type="number" id="newPoids" name="newPoids" value="%s">
                    <button type="submit">Ok</button>
                </form>
                """.formatted(renderAction(), identifiantAliment, poids.size());
    }

    private String renderAction() {
        return HttpRoutes.REAJUSTER_INGREDIENT.path.replace("{id}", identifiantRecette.toString());
    }
}
