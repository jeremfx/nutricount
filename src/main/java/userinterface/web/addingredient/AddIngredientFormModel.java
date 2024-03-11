package userinterface.web.addingredient;

import core.domain.food.FoodId;
import core.domain.food.Size;

public record AddIngredientFormModel(FoodId id, Size size) {
}
