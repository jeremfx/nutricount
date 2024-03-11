package userinterface.web.resizeingredient;

import core.domain.food.FoodId;
import core.domain.food.Size;

public record ResizeIngredientFormModel(FoodId foodId, Size newSize) {
}
