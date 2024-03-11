package core.application.repository;

import core.domain.food.Food;
import core.domain.food.FoodId;

import java.util.List;
import java.util.Optional;

public interface FoodRepository {
    Food getFood(FoodId id);
    Optional<Food> findFoodByName(String foodName);

    List<Food> searchFoodByName(String searchTerm);

    List<Food> getFoods();
}
