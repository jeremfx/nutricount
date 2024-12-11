package core.domain.food;

import java.util.Objects;

public record Ingredient(AlimentBasique alimentBasique, Poids poids) {
    public Ingredient {
        Objects.requireNonNull(alimentBasique);
        Objects.requireNonNull(poids);
    }

    public Double calories(){
        return alimentBasique.calories() * poids.size() / 100;
    }

    public Double proteines() {
        return alimentBasique.proteines() * poids.size() / 100;
    }

    public Double carbs() {
        return alimentBasique.glucides() * poids.size() / 100;
    }

    public Double fats() {
        return alimentBasique.lipides() * poids.size() / 100;
    }
}
