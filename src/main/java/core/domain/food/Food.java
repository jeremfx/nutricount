package core.domain.food;

public record Food(FoodId id, String name, FoodGroup foodGroup, Macros macros) {
    public Double calories() {
        return macros().calories();
    }

    public Double protein() {
        return macros().protein();
    }
    public Double carbohydrate() {
        return macros().carbohydrate();
    }

    public Double fat() {
        return macros().fat();
    }
}
