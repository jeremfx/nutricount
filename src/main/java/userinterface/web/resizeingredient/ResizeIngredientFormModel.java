package userinterface.web.resizeingredient;

import core.domain.food.IdentifiantAliment;
import core.domain.food.Poids;

public record ResizeIngredientFormModel(IdentifiantAliment identifiantAliment, Poids newPoids) {
}
