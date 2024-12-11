package core.application.repository;

import core.domain.food.AlimentBasique;
import core.domain.food.IdentifiantAliment;

import java.util.List;
import java.util.Optional;

public interface FoodRepository {
    AlimentBasique getFood(IdentifiantAliment id);
    Optional<AlimentBasique> findFoodByName(String foodName);

    List<AlimentBasique> searchFoodByName(String searchTerm);

    List<AlimentBasique> getFoods();
}
