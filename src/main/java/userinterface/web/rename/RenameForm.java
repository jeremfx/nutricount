package userinterface.web.rename;

import core.domain.food.IdentifiantRecette;
import userinterface.web.technical.HtmlFragment;
import userinterface.web.HttpRoutes;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RenameForm implements HtmlFragment {
    private final IdentifiantRecette identifiantRecette;
    private final String recipeName;

    public RenameForm(IdentifiantRecette identifiantRecette, String recipeName) {
        this.identifiantRecette = identifiantRecette;
        this.recipeName = recipeName;
    }

    public static RenameFormModel parseResponse(Map<String, List<String>> form) {
        return new RenameFormModel(new IdentifiantRecette(UUID.fromString(form.get("identifiantRecette").get(0))), form.get("recipeName").get(0));
    }

    @Override
    public String render() {
        return """
                <form action="%s" method="post">
                    <input type="hidden" name="identifiantRecette" value="%s">
                    <input type="text" name="recipeName" value="%s">
                </form>
                """.formatted(renderAction(), identifiantRecette, recipeName);
    }

    private String renderAction() {
        return HttpRoutes.RENOMMER_RECETTE.path.replace("{id}", identifiantRecette.toString());
    }
}
