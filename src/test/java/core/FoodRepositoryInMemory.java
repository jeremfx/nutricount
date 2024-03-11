package core;

import core.application.repository.FoodRepository;
import core.domain.food.Food;
import core.domain.food.FoodGroup;
import core.domain.food.FoodId;
import core.domain.food.Macros;

import java.util.List;
import java.util.Optional;

public class FoodRepositoryInMemory implements FoodRepository {

    private final List<Food> foods = List.of(
            new Food(new FoodId(1),  "Pomme", new FoodGroup("Fruits"), new Macros(150d, 10d, 20d, 50d)),
            new Food(new FoodId(2), "Poire", new FoodGroup("Fruits"), new Macros(100d, 25d, 35d, 40d))
    );

    @Override
    public Food getFood(FoodId id) {
        return foods.stream().filter(f -> f.id().equals(id))
                .findFirst().get();
    }

    public Optional<Food> findFoodByName(String foodName) {
        return foods.stream().filter(f -> f.name().equalsIgnoreCase(foodName))
                .findFirst();
    }

    public List<Food> searchFoodByName(String searchTerm) {
        if(searchTerm == null || searchTerm.isEmpty()){
            return List.of();
        }
        return foods.stream().filter(f -> f.name().toLowerCase().contains(searchTerm.toLowerCase())).toList();
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }
}
