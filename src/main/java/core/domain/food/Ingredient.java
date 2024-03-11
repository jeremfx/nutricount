package core.domain.food;

import java.util.Objects;

public record Ingredient(Food food, Size size) {
    public Ingredient {
        Objects.requireNonNull(food);
        Objects.requireNonNull(size);
    }

    public Double calories(){
        return food.calories() * size.size() / 100;
    }

    public Double proteines() {
        return food.protein() * size.size() / 100;
    }

    public Double carbs() {
        return food.carbohydrate() * size.size() / 100;
    }

    public Double fats() {
        return food.fat() * size.size() / 100;
    }
}
