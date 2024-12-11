package core.domain.food;

public record Macros(Double calories, Double protein, Double carbohydrate,
                     Double fat) {
    public Macros(Double protein, Double carbohydrate, Double fat) {
        this(calculateKcal(protein, carbohydrate, fat), protein, carbohydrate, fat);
    }

    private static Double calculateKcal(Double protein, Double carbohydrate, Double fat) {
        return protein * 4 + carbohydrate * 4 + fat * 9;
    }

    public Double apportCaloriqueEnProteines() {
        return (protein * 4 / calories) * 100;
    }

    public Double apportCaloriqueEnGlucides() {
        return (carbohydrate * 4 / calories) * 100;
    }

    public Double apportCaloriqueEnLipides() {
        return (fat * 9 / calories) * 100;
    }
}
