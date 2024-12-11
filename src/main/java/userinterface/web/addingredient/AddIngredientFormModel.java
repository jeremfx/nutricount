package userinterface.web.addingredient;

import core.domain.food.IdentifiantAliment;
import core.domain.food.Poids;

public record AddIngredientFormModel(IdentifiantAliment identifiantAliment, Poids poids) {
}
