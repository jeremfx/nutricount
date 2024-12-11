package core;

import core.application.repository.FoodRepository;
import core.domain.food.AlimentBasique;
import core.domain.food.Famille;
import core.domain.food.IdentifiantAliment;
import core.domain.food.Macros;

import java.util.List;
import java.util.Optional;

public class FoodRepositoryInMemory implements FoodRepository {

    private final List<AlimentBasique> alimentBasiques = List.of(
            new AlimentBasique(new IdentifiantAliment(1),  "Pomme", new Famille("Fruits"), new Macros(10d, 20d, 50d)),
            new AlimentBasique(new IdentifiantAliment(2), "Poire", new Famille("Fruits"), new Macros(25d, 35d, 40d))
    );

    @Override
    public AlimentBasique getFood(IdentifiantAliment id) {
        return alimentBasiques.stream().filter(f -> f.id().equals(id))
                .findFirst().get();
    }

    public Optional<AlimentBasique> findFoodByName(String foodName) {
        return alimentBasiques.stream().filter(f -> f.nom().equalsIgnoreCase(foodName))
                .findFirst();
    }

    public List<AlimentBasique> searchFoodByName(String searchTerm) {
        if(searchTerm == null || searchTerm.isEmpty()){
            return List.of();
        }
        return alimentBasiques.stream().filter(f -> f.nom().toLowerCase().contains(searchTerm.toLowerCase())).toList();
    }

    @Override
    public List<AlimentBasique> getFoods() {
        return alimentBasiques;
    }
}
